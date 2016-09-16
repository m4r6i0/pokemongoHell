package com.upsight.android;

import com.upsight.android.googlepushservices.UpsightGooglePushServices.OnRegisterListener;
import com.upsight.android.googlepushservices.UpsightGooglePushServicesApi;
import com.upsight.android.googlepushservices.UpsightGooglePushServicesComponent;
import com.upsight.android.googlepushservices.internal.DaggerGooglePushServicesComponent;
import com.upsight.android.googlepushservices.internal.PushConfigManager;
import com.upsight.android.googlepushservices.internal.PushConfigManager.Config;
import com.upsight.android.googlepushservices.internal.PushModule;
import java.io.IOException;
import javax.inject.Inject;
import rx.functions.Action1;

public class UpsightGooglePushServicesExtension extends UpsightExtension<UpsightGooglePushServicesComponent, UpsightGooglePushServicesApi> {
    public static final String EXTENSION_NAME = "com.upsight.extension.googlepushservices";
    private static final String LOG_TAG;
    @Inject
    PushConfigManager mPushConfigManager;
    @Inject
    UpsightGooglePushServicesApi mUpsightPush;

    /* renamed from: com.upsight.android.UpsightGooglePushServicesExtension.1 */
    class C08921 implements Action1<Config> {
        final /* synthetic */ UpsightContext val$upsight;

        /* renamed from: com.upsight.android.UpsightGooglePushServicesExtension.1.1 */
        class C08911 implements OnRegisterListener {
            C08911() {
            }

            public void onSuccess(String registrationId) {
                C08921.this.val$upsight.getLogger().m205d(UpsightGooglePushServicesExtension.LOG_TAG, "Auto-registered for push notifications with registrationId=" + registrationId, new Object[0]);
            }

            public void onFailure(UpsightException e) {
                C08921.this.val$upsight.getLogger().m207e(UpsightGooglePushServicesExtension.LOG_TAG, "Failed to auto-register for push notifications", e);
            }
        }

        C08921(UpsightContext upsightContext) {
            this.val$upsight = upsightContext;
        }

        public void call(Config config) {
            if (config.autoRegister) {
                UpsightGooglePushServicesExtension.this.mUpsightPush.register(new C08911());
            } else {
                this.val$upsight.getLogger().m205d(UpsightGooglePushServicesExtension.LOG_TAG, "Skipping auto-registration of push notifications", new Object[0]);
            }
        }
    }

    static {
        LOG_TAG = UpsightGooglePushServicesExtension.class.getSimpleName();
    }

    UpsightGooglePushServicesExtension() {
    }

    protected UpsightGooglePushServicesComponent onResolve(UpsightContext upsight) {
        return DaggerGooglePushServicesComponent.builder().pushModule(new PushModule(upsight)).build();
    }

    public UpsightGooglePushServicesApi getApi() {
        return this.mUpsightPush;
    }

    protected void onPostCreate(UpsightContext upsight) {
        try {
            this.mPushConfigManager.fetchCurrentConfigObservable().subscribeOn(upsight.getCoreComponent().subscribeOnScheduler()).observeOn(upsight.getCoreComponent().observeOnScheduler()).subscribe(new C08921(upsight));
        } catch (IOException e) {
            upsight.getLogger().m207e(LOG_TAG, "Failed to fetch push configurations", e);
        }
    }
}
