package com.upsight.android.internal;

import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ContextModule_ProvideBusFactory implements Factory<Bus> {
    static final /* synthetic */ boolean $assertionsDisabled;
    private final ContextModule module;

    static {
        $assertionsDisabled = !ContextModule_ProvideBusFactory.class.desiredAssertionStatus();
    }

    public ContextModule_ProvideBusFactory(ContextModule module) {
        if ($assertionsDisabled || module != null) {
            this.module = module;
            return;
        }
        throw new AssertionError();
    }

    public Bus get() {
        return (Bus) Preconditions.checkNotNull(this.module.provideBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Bus> create(ContextModule module) {
        return new ContextModule_ProvideBusFactory(module);
    }
}
