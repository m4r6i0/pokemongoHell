package crittercism.android;

import com.voxelbusters.nativeplugins.defines.Keys.Scheme;
import java.net.InetAddress;

/* renamed from: crittercism.android.k */
public final class C1181k {
    InetAddress f828a;
    String f829b;
    public String f830c;
    C1180a f831d;
    int f832e;
    boolean f833f;

    /* renamed from: crittercism.android.k.a */
    public enum C1180a {
        HTTP(Scheme.HTTP, 80),
        HTTPS(Scheme.HTTPS, 443);
        
        private String f826c;
        private int f827d;

        private C1180a(String str, int i) {
            this.f826c = str;
            this.f827d = i;
        }
    }

    public C1181k() {
        this.f830c = "/";
        this.f831d = null;
        this.f832e = -1;
        this.f833f = false;
    }
}
