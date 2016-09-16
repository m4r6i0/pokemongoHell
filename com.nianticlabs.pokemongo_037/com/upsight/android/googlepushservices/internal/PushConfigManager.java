package com.upsight.android.googlepushservices.internal;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.configuration.UpsightConfiguration;
import com.upsight.android.googlepushservices.C0931R;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import rx.Observable;
import rx.functions.Func1;

public class PushConfigManager {
    private static final String LOG_TAG;
    public static final String PUSH_CONFIGURATION_SUBTYPE = "upsight.configuration.push";
    private UpsightDataStore mDataStore;
    private Gson mGson;
    private UpsightLogger mLogger;
    private UpsightContext mUpsight;

    /* renamed from: com.upsight.android.googlepushservices.internal.PushConfigManager.1 */
    class C09421 implements Func1<Config, Boolean> {
        C09421() {
        }

        public Boolean call(Config config) {
            boolean z = config != null && config.isValid();
            return Boolean.valueOf(z);
        }
    }

    /* renamed from: com.upsight.android.googlepushservices.internal.PushConfigManager.2 */
    class C09432 implements Func1<UpsightConfiguration, Config> {
        C09432() {
        }

        public Config call(UpsightConfiguration upsightConfiguration) {
            return PushConfigManager.this.parseConfiguration(upsightConfiguration.getConfiguration());
        }
    }

    /* renamed from: com.upsight.android.googlepushservices.internal.PushConfigManager.3 */
    class C09443 implements Func1<UpsightConfiguration, Boolean> {
        C09443() {
        }

        public Boolean call(UpsightConfiguration upsightConfiguration) {
            return Boolean.valueOf(PushConfigManager.PUSH_CONFIGURATION_SUBTYPE.equals(upsightConfiguration.getScope()));
        }
    }

    public static final class Config {
        @SerializedName("auto_register")
        @Expose
        public boolean autoRegister;
        @SerializedName("push_token_ttl")
        @Expose
        public long pushTokenTtl;

        private boolean isValid() {
            return this.pushTokenTtl >= 0;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Config that = (Config) o;
            if (that.pushTokenTtl == this.pushTokenTtl && that.autoRegister == this.autoRegister) {
                return true;
            }
            return false;
        }
    }

    static {
        LOG_TAG = PushConfigManager.class.getSimpleName();
    }

    PushConfigManager(UpsightContext upsight) {
        this.mUpsight = upsight;
        this.mDataStore = this.mUpsight.getDataStore();
        this.mLogger = this.mUpsight.getLogger();
        this.mGson = upsight.getCoreComponent().gson();
    }

    public Observable<Config> fetchCurrentConfigObservable() throws IOException {
        return this.mDataStore.fetchObservable(UpsightConfiguration.class).filter(new C09443()).map(new C09432()).filter(new C09421()).firstOrDefault(parseConfiguration(IOUtils.toString(this.mUpsight.getResources().openRawResource(C0931R.raw.push_config))));
    }

    private Config parseConfiguration(String jsonConfig) {
        Config config = null;
        try {
            return (Config) this.mGson.fromJson(jsonConfig, Config.class);
        } catch (JsonSyntaxException e) {
            this.mLogger.m207e(LOG_TAG, "Could not parse incoming config", e);
            return config;
        }
    }
}
