package com.unity3d.player;

import android.os.Build.VERSION;

/* renamed from: com.unity3d.player.q */
public final class C0878q {
    static final boolean f195a;
    static final boolean f196b;
    static final boolean f197c;
    static final boolean f198d;
    static final boolean f199e;
    static final boolean f200f;
    static final boolean f201g;
    static final boolean f202h;
    static final C0859f f203i;
    static final C0855e f204j;
    static final C0862h f205k;
    static final C0861g f206l;
    static final C0863i f207m;

    static {
        C0863i c0863i = null;
        boolean z = true;
        f195a = VERSION.SDK_INT >= 11;
        f196b = VERSION.SDK_INT >= 12;
        f197c = VERSION.SDK_INT >= 14;
        f198d = VERSION.SDK_INT >= 16;
        f199e = VERSION.SDK_INT >= 17;
        f200f = VERSION.SDK_INT >= 19;
        f201g = VERSION.SDK_INT >= 21;
        if (VERSION.SDK_INT < 23) {
            z = false;
        }
        f202h = z;
        f203i = f195a ? new C0860d() : null;
        f204j = f196b ? new C0856c() : null;
        f205k = f198d ? new C0871l() : null;
        f206l = f199e ? new C0869k() : null;
        if (f202h) {
            c0863i = new C0874n();
        }
        f207m = c0863i;
    }
}
