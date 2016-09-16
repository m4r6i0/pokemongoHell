package com.upsight.android.analytics.internal.configuration;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.C0893R;
import com.upsight.android.analytics.configuration.UpsightConfiguration;
import com.upsight.android.analytics.dispatcher.EndpointResponse;
import com.upsight.android.analytics.event.config.UpsightConfigExpiredEvent;
import com.upsight.android.analytics.internal.DispatcherService.DestroyEvent;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightDataStoreListener;
import com.upsight.android.persistence.UpsightSubscription;
import com.upsight.android.persistence.annotation.Created;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;

public final class ConfigurationManager {
    public static final String CONFIGURATION_RESPONSE_SUBTYPE = "upsight.configuration";
    public static final String CONFIGURATION_SUBTYPE = "upsight.configuration.configurationManager";
    private static final String LOG_TAG = "Configurator";
    private final Bus mBus;
    private final ManagerConfigParser mConfigParser;
    private Config mCurrentConfig;
    private final UpsightDataStore mDataStore;
    private UpsightSubscription mDataStoreSubscription;
    private boolean mIsLaunched;
    private boolean mIsOutOfSync;
    private final UpsightLogger mLogger;
    private final ConfigurationResponseParser mResponseParser;
    private Action0 mSyncAction;
    private final UpsightContext mUpsight;
    private final Worker mWorker;
    private Subscription mWorkerSubscription;

    /* renamed from: com.upsight.android.analytics.internal.configuration.ConfigurationManager.1 */
    class C09041 implements UpsightDataStoreListener<Set<UpsightConfiguration>> {
        C09041() {
        }

        public void onSuccess(Set<UpsightConfiguration> result) {
            if (ConfigurationManager.this.mCurrentConfig == null) {
                boolean hasApplied = false;
                if (result.size() > 0) {
                    for (UpsightConfiguration config : result) {
                        if (config.getScope().equals(ConfigurationManager.CONFIGURATION_SUBTYPE)) {
                            ConfigurationManager.this.mLogger.m205d(ConfigurationManager.LOG_TAG, "Apply local configurations", new Object[0]);
                            hasApplied = ConfigurationManager.this.applyConfiguration(config.getConfiguration());
                        }
                    }
                }
                if (!hasApplied) {
                    ConfigurationManager.this.applyDefaultConfiguration();
                }
            }
        }

        public void onFailure(UpsightException exception) {
            ConfigurationManager.this.mLogger.m207e(ConfigurationManager.LOG_TAG, "Could not fetch existing configs from datastore", exception);
            if (ConfigurationManager.this.mCurrentConfig == null) {
                ConfigurationManager.this.applyDefaultConfiguration();
            }
        }
    }

    /* renamed from: com.upsight.android.analytics.internal.configuration.ConfigurationManager.2 */
    class C09052 implements Action0 {
        C09052() {
        }

        public void call() {
            ConfigurationManager.this.mLogger.m205d(ConfigurationManager.LOG_TAG, "Record config.expired", new Object[0]);
            UpsightConfigExpiredEvent.createBuilder().record(ConfigurationManager.this.mUpsight);
        }
    }

    /* renamed from: com.upsight.android.analytics.internal.configuration.ConfigurationManager.3 */
    class C09063 implements UpsightDataStoreListener<Set<UpsightConfiguration>> {
        C09063() {
        }

        public void onSuccess(Set<UpsightConfiguration> result) {
            for (UpsightConfiguration configuration : result) {
                ConfigurationManager.this.mDataStore.remove(configuration);
            }
        }

        public void onFailure(UpsightException exception) {
        }
    }

    public static final class Config {
        public final long requestInterval;

        Config(long requestInterval) {
            this.requestInterval = requestInterval;
        }

        public boolean isValid() {
            return this.requestInterval > 0;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (((Config) o).requestInterval != this.requestInterval) {
                return false;
            }
            return true;
        }
    }

    ConfigurationManager(UpsightContext upsight, UpsightDataStore dataStore, ConfigurationResponseParser responseParser, ManagerConfigParser managerConfigParser, Scheduler scheduler, Bus bus, UpsightLogger logger) {
        this.mIsLaunched = false;
        this.mSyncAction = new C09052();
        this.mUpsight = upsight;
        this.mDataStore = dataStore;
        this.mResponseParser = responseParser;
        this.mConfigParser = managerConfigParser;
        this.mBus = bus;
        this.mLogger = logger;
        this.mWorker = scheduler.createWorker();
    }

    private void fetchCurrentConfig() {
        this.mDataStore.fetch(UpsightConfiguration.class, new C09041());
    }

    private void applyDefaultConfiguration() {
        try {
            String config = IOUtils.toString(this.mUpsight.getResources().openRawResource(C0893R.raw.configurator_config));
            this.mLogger.m205d(LOG_TAG, "Apply default configurations", new Object[0]);
            applyConfiguration(config);
        } catch (IOException e) {
            this.mLogger.m207e(LOG_TAG, "Could not read default config", e);
        }
    }

    private synchronized boolean applyConfiguration(String jsonConfiguration) {
        boolean z;
        try {
            Config config = this.mConfigParser.parse(jsonConfiguration);
            if (config == null || !config.isValid()) {
                this.mLogger.m213w(LOG_TAG, "Incoming config is invalid", new Object[0]);
                z = false;
            } else if (config.equals(this.mCurrentConfig)) {
                this.mLogger.m213w(LOG_TAG, "Current config is equals to incoming config, rejecting", new Object[0]);
                z = true;
            } else {
                if (!(this.mWorkerSubscription == null || this.mWorkerSubscription.isUnsubscribed())) {
                    this.mLogger.m205d(LOG_TAG, "Stop config.expired recording scheduler", new Object[0]);
                    this.mWorkerSubscription.unsubscribe();
                }
                long initialDelay = this.mIsOutOfSync ? 0 : config.requestInterval;
                this.mLogger.m205d(LOG_TAG, "Schedule recording of config.expired every " + config.requestInterval + " ms, mIsOutOfSync=" + this.mIsOutOfSync, new Object[0]);
                this.mWorkerSubscription = this.mWorker.schedulePeriodically(this.mSyncAction, initialDelay, config.requestInterval, TimeUnit.MILLISECONDS);
                this.mIsOutOfSync = false;
                this.mCurrentConfig = config;
                z = true;
            }
        } catch (IOException e) {
            this.mLogger.m207e(LOG_TAG, "Could not parse incoming configuration", e);
            z = false;
        }
        return z;
    }

    @Created
    public void onEndpointResponse(EndpointResponse response) {
        if (CONFIGURATION_RESPONSE_SUBTYPE.equals(response.getType())) {
            try {
                Collection<UpsightConfiguration> configs = this.mResponseParser.parse(response.getContent());
                this.mDataStore.fetch(UpsightConfiguration.class, new C09063());
                for (UpsightConfiguration config : configs) {
                    if (config.getScope().equals(CONFIGURATION_SUBTYPE)) {
                        this.mLogger.m205d(LOG_TAG, "Apply received configurations", new Object[0]);
                        if (applyConfiguration(config.getConfiguration())) {
                            this.mDataStore.store(config);
                        }
                    } else {
                        this.mDataStore.store(config);
                    }
                }
            } catch (IOException e) {
                this.mLogger.m207e(LOG_TAG, "Could not parse incoming configurations", e);
            }
        }
    }

    @Subscribe
    public void handle(DestroyEvent event) {
        terminate();
    }

    public synchronized void launch() {
        if (!this.mIsLaunched) {
            this.mIsLaunched = true;
            this.mIsOutOfSync = true;
            this.mCurrentConfig = null;
            this.mDataStoreSubscription = this.mDataStore.subscribe(this);
            this.mWorkerSubscription = null;
            this.mBus.register(this);
            fetchCurrentConfig();
        }
    }

    public synchronized void terminate() {
        this.mBus.unregister(this);
        if (this.mDataStoreSubscription != null) {
            this.mDataStoreSubscription.unsubscribe();
            this.mDataStoreSubscription = null;
        }
        if (this.mWorkerSubscription != null) {
            this.mLogger.m205d(LOG_TAG, "Stop config.expired recording scheduler", new Object[0]);
            this.mWorkerSubscription.unsubscribe();
            this.mWorkerSubscription = null;
        }
        this.mCurrentConfig = null;
        this.mIsLaunched = false;
    }
}
