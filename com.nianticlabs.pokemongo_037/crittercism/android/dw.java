package crittercism.android;

import crittercism.android.ds.C1168a;

public final class dw {
    private ds f755a;
    private du f756b;

    public final synchronized du m770a() {
        return this.f756b;
    }

    public final synchronized boolean m772b() {
        boolean z;
        z = true;
        if (this.f755a != null) {
            z = this.f755a.m757a();
        }
        return z;
    }

    public final synchronized void m771a(ax axVar) {
        this.f755a = C1168a.m756a(axVar);
        if (!this.f755a.m757a()) {
            int b = axVar.m388b(cq.SESSION_ID_SETTING.m693a(), cq.SESSION_ID_SETTING.m694b());
            if (b == 0) {
                b = axVar.m388b(cq.OLD_SESSION_ID_SETTING.m693a(), cq.OLD_SESSION_ID_SETTING.m694b());
            }
            du duVar = new du(b);
            duVar.f753a++;
            this.f756b = duVar;
        }
    }
}
