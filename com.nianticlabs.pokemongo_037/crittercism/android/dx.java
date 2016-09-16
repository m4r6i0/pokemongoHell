package crittercism.android;

import android.util.Log;
import crittercism.android.ec.C11721;

public final class dx {
    public static C1169a f761a;
    private static ec f762b;

    /* renamed from: crittercism.android.dx.a */
    public enum C1169a {
        UNINITIALIZED,
        ON,
        OFF
    }

    static {
        f761a = C1169a.UNINITIALIZED;
    }

    public static void m774a(ec ecVar) {
        f762b = ecVar;
    }

    public static void m773a() {
    }

    public static void m778b() {
    }

    public static void m781c() {
    }

    public static void m775a(String str) {
        Log.i("Crittercism", str);
    }

    public static void m779b(String str) {
        Log.e("Crittercism", str);
    }

    public static void m776a(String str, Throwable th) {
        Log.e("Crittercism", str, th);
    }

    public static void m782c(String str) {
        Log.w("Crittercism", str);
    }

    public static void m780b(String str, Throwable th) {
        Log.w("Crittercism", str, th);
    }

    public static void m777a(Throwable th) {
        if (!(th instanceof cp)) {
            try {
                ec ecVar = f762b;
                if (f762b != null && f761a == C1169a.ON) {
                    ecVar = f762b;
                    Runnable c11721 = new C11721(ecVar, th, Thread.currentThread().getId());
                    if (!ecVar.f785c.m733a(c11721)) {
                        ecVar.f784b.execute(c11721);
                    }
                }
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th2) {
            }
        }
    }
}
