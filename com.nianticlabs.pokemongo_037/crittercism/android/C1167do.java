package crittercism.android;

/* renamed from: crittercism.android.do */
public final class C1167do {

    /* renamed from: crittercism.android.do.a */
    public static class C1164a extends dm {
        private String f743a;

        /* renamed from: crittercism.android.do.a.a */
        public static class C1163a implements dn {
            public final /* synthetic */ dm m746a(String str) {
                if (str != null) {
                    return new C1164a((byte) 0);
                }
                throw new NullPointerException("packageName cannot be null");
            }
        }

        private C1164a(String str) {
            this.f743a = str;
        }

        public final String m747a() {
            return "http://www.amazon.com/gp/mas/dl/android?p=" + this.f743a;
        }
    }

    /* renamed from: crittercism.android.do.b */
    public static class C1166b extends dm {
        private String f744a;

        /* renamed from: crittercism.android.do.b.a */
        public static class C1165a implements dn {
            public final /* synthetic */ dm m748a(String str) {
                if (str != null) {
                    return new C1166b((byte) 0);
                }
                throw new NullPointerException("packageName cannot be null");
            }
        }

        private C1166b(String str) {
            this.f744a = str;
        }

        public final String m749a() {
            return "market://details?id=" + this.f744a;
        }
    }
}
