package crittercism.android;

import com.voxelbusters.nativeplugins.defines.Keys.Scheme;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/* renamed from: crittercism.android.q */
public final class C1188q extends C1183m {
    private static final String[] f844f;

    static {
        f844f = new String[]{"libcore.net.http.HttpsURLConnectionImpl", "org.apache.harmony.luni.internal.net.www.protocol.https.HttpsURLConnectionImpl", "org.apache.harmony.luni.internal.net.www.protocol.https.HttpsURLConnection"};
    }

    public C1188q(C1171e c1171e, C1161d c1161d) {
        super(c1171e, c1161d, f844f);
    }

    protected final URLConnection openConnection(URL u) {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.openConnection(u);
        try {
            return new C1190s(httpsURLConnection, this.f836c, this.f837d);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return httpsURLConnection;
        }
    }

    protected final URLConnection openConnection(URL u, Proxy proxy) {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.openConnection(u, proxy);
        try {
            return new C1190s(httpsURLConnection, this.f836c, this.f837d);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return httpsURLConnection;
        }
    }

    protected final int getDefaultPort() {
        return 443;
    }

    protected final String m832a() {
        return Scheme.HTTPS;
    }
}
