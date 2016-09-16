package crittercism.android;

import crittercism.android.bs.C1124a;
import crittercism.android.bz.C1151a;
import crittercism.android.ca.C1154a;

public enum br {
    APP_LOADS("app_loads_2", 10, Integer.MAX_VALUE, new C1124a(0), new C1154a(), null),
    HAND_EXCS("exceptions", 5, 50, new C1124a(0), new C1154a(), "exceptions"),
    INTERNAL_EXCS("internal_excs", 3, 3, new C1124a(0), new C1154a(), "exceptions"),
    NDK_CRASHES("ndk_crashes", 5, Integer.MAX_VALUE, new C1124a(0), new C1154a(), "crashes"),
    SDK_CRASHES("sdk_crashes", 5, Integer.MAX_VALUE, new C1124a(0), new C1154a(), "crashes"),
    CURR_BCS("current_bcs", 50, Integer.MAX_VALUE, new C1124a(1), new C1151a(), null),
    NW_BCS("network_bcs", 10, Integer.MAX_VALUE, new C1124a(0), new C1151a(), null),
    PREV_BCS("previous_bcs", 50, Integer.MAX_VALUE, new C1124a(0), new C1151a(), null),
    STARTED_TXNS("started_txns", 50, Integer.MAX_VALUE, new C1124a(0), new C1151a(), null),
    FINISHED_TXNS("finished_txns", Integer.MAX_VALUE, Integer.MAX_VALUE, new C1124a(0), new C1151a(), null),
    SYSTEM_BCS("system_bcs", 100, Integer.MAX_VALUE, new C1124a(0), new C1151a(), null);
    
    private String f517l;
    private int f518m;
    private int f519n;
    private C1124a f520o;
    private cj f521p;
    private String f522q;

    private br(String str, int i, int i2, C1124a c1124a, cj cjVar, String str2) {
        this.f517l = str;
        this.f518m = i;
        this.f519n = i2;
        this.f520o = c1124a;
        this.f521p = cjVar;
        this.f522q = str2;
    }

    public final String m560a() {
        return this.f517l;
    }

    public final int m561b() {
        return this.f518m;
    }

    public final C1124a m562c() {
        return this.f520o;
    }

    public final cj m563d() {
        return this.f521p;
    }

    public final int m564e() {
        return this.f519n;
    }

    public final String m565f() {
        return this.f522q;
    }
}
