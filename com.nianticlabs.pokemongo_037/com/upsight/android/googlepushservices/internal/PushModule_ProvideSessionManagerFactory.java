package com.upsight.android.googlepushservices.internal;

import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.session.SessionManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class PushModule_ProvideSessionManagerFactory implements Factory<SessionManager> {
    static final /* synthetic */ boolean $assertionsDisabled;
    private final PushModule module;
    private final Provider<UpsightContext> upsightProvider;

    static {
        $assertionsDisabled = !PushModule_ProvideSessionManagerFactory.class.desiredAssertionStatus();
    }

    public PushModule_ProvideSessionManagerFactory(PushModule module, Provider<UpsightContext> upsightProvider) {
        if ($assertionsDisabled || module != null) {
            this.module = module;
            if ($assertionsDisabled || upsightProvider != null) {
                this.upsightProvider = upsightProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public SessionManager get() {
        return (SessionManager) Preconditions.checkNotNull(this.module.provideSessionManager((UpsightContext) this.upsightProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SessionManager> create(PushModule module, Provider<UpsightContext> upsightProvider) {
        return new PushModule_ProvideSessionManagerFactory(module, upsightProvider);
    }
}
