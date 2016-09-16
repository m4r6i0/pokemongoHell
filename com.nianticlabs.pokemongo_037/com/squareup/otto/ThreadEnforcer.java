package com.squareup.otto;

import android.os.Looper;

public interface ThreadEnforcer {
    public static final ThreadEnforcer ANY;
    public static final ThreadEnforcer MAIN;

    /* renamed from: com.squareup.otto.ThreadEnforcer.1 */
    static class C08311 implements ThreadEnforcer {
        C08311() {
        }

        public void enforce(Bus bus) {
        }
    }

    /* renamed from: com.squareup.otto.ThreadEnforcer.2 */
    static class C08322 implements ThreadEnforcer {
        C08322() {
        }

        public void enforce(Bus bus) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("Event bus " + bus + " accessed from non-main thread " + Looper.myLooper());
            }
        }
    }

    void enforce(Bus bus);

    static {
        ANY = new C08311();
        MAIN = new C08322();
    }
}
