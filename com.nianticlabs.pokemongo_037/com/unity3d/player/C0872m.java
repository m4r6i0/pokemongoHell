package com.unity3d.player;

import android.util.Log;

/* renamed from: com.unity3d.player.m */
final class C0872m {
    protected static boolean f185a;

    static {
        f185a = false;
    }

    protected static void Log(int i, String str) {
        if (!f185a) {
            if (i == 6) {
                Log.e("Unity", str);
            }
            if (i == 5) {
                Log.w("Unity", str);
            }
        }
    }
}
