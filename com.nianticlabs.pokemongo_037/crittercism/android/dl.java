package crittercism.android;

import com.voxelbusters.nativeplugins.defines.Keys;
import java.util.HashMap;
import java.util.Map;

public final class dl extends di {
    public Map f736a;
    private dw f737b;
    private au f738c;
    private boolean f739d;
    private boolean f740e;
    private boolean f741f;
    private boolean f742g;

    public dl(au auVar) {
        this.f736a = new HashMap();
        this.f739d = false;
        this.f740e = false;
        this.f741f = false;
        this.f742g = false;
        this.f738c = auVar;
        this.f737b = auVar.m370l();
    }

    public final void m740b() {
        this.f739d = true;
    }

    public final void m741c() {
        this.f740e = true;
    }

    public final void m742d() {
        this.f741f = true;
    }

    public final void m743e() {
        this.f742g = true;
    }

    private synchronized void m738a(String str, Object obj) {
        this.f736a.put(str, obj);
    }

    public final void m739a() {
        boolean z = false;
        boolean b = this.f737b.m772b();
        if (this.f739d) {
            m738a("optOutStatus", Boolean.valueOf(b));
        }
        if (!b) {
            if (this.f740e) {
                m738a("crashedOnLastLoad", Boolean.valueOf(dq.f746a));
            }
            if (this.f741f) {
                m738a("userUUID", this.f738c.m361c());
            }
            if (this.f742g) {
                dt dtVar = az.m400A().f355A;
                if (dtVar != null) {
                    String str = "shouldShowRateAppAlert";
                    if (dtVar.f752a.getBoolean("rateMyAppEnabled", false) && !dtVar.f752a.getBoolean("hasRatedApp", false)) {
                        int a = dtVar.m758a();
                        int i = dtVar.f752a.getInt("rateAfterNumLoads", 5);
                        if (a >= i) {
                            if ((a - i) % dtVar.f752a.getInt("remindAfterNumLoads", 5) == 0) {
                                z = true;
                            }
                        }
                    }
                    m738a(str, Boolean.valueOf(z));
                    m738a(Keys.MESSAGE, dtVar.m760b());
                    m738a(Keys.TITLE, dtVar.m761c());
                }
            }
        }
    }
}
