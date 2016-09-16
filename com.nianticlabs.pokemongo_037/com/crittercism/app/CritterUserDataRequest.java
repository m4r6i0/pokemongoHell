package com.crittercism.app;

import crittercism.android.az;
import crittercism.android.dg;
import crittercism.android.dl;
import crittercism.android.dx;
import crittercism.android.dy;
import java.util.HashMap;
import java.util.Map;

public class CritterUserDataRequest {
    private final CritterCallback f10a;
    private az f11b;
    private Map f12c;
    private dl f13d;

    /* renamed from: com.crittercism.app.CritterUserDataRequest.1 */
    class C01371 implements Runnable {
        final /* synthetic */ CritterUserDataRequest f9a;

        C01371(CritterUserDataRequest critterUserDataRequest) {
            this.f9a = critterUserDataRequest;
        }

        public final void run() {
            this.f9a.f13d.run();
            this.f9a.f12c = this.f9a.f13d.f736a;
            this.f9a.f10a.onCritterDataReceived(new CritterUserData(this.f9a.f12c, this.f9a.f11b.f369f.m772b()));
        }
    }

    public CritterUserDataRequest(CritterCallback cb) {
        this.f10a = cb;
        this.f11b = az.m400A();
        this.f12c = new HashMap();
        this.f13d = new dl(this.f11b);
    }

    public CritterUserDataRequest requestRateMyAppInfo() {
        this.f13d.m743e();
        return this;
    }

    public CritterUserDataRequest requestDidCrashOnLastLoad() {
        this.f13d.m741c();
        return this;
    }

    public CritterUserDataRequest requestUserUUID() {
        this.f13d.m742d();
        return this;
    }

    public CritterUserDataRequest requestOptOutStatus() {
        this.f13d.m740b();
        return this;
    }

    public synchronized void makeRequest() {
        dg dgVar = this.f11b.f380q;
        if (dgVar == null) {
            dx.m776a("Must initialize Crittercism before calling " + getClass().getName() + ".makeRequest()", new IllegalStateException());
        } else {
            Runnable c01371 = new C01371(this);
            if (!dgVar.m733a(c01371)) {
                new dy(c01371).start();
            }
        }
    }
}
