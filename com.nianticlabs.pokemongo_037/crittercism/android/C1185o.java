package crittercism.android;

import com.voxelbusters.nativeplugins.defines.Keys.Scheme;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/* renamed from: crittercism.android.o */
public final class C1185o extends C1183m {
    private static final String[] f842f;

    static {
        f842f = new String[]{"libcore.net.http.HttpURLConnectionImpl", "org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnectionImpl", "org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnection"};
    }

    public C1185o(C1171e c1171e, C1161d c1161d) {
        super(c1171e, c1161d, f842f);
    }

    protected final URLConnection openConnection(URL u) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) super.openConnection(u);
        try {
            return new C1189r(httpURLConnection, this.f836c, this.f837d);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return httpURLConnection;
        }
    }

    protected final URLConnection openConnection(URL u, Proxy proxy) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) super.openConnection(u, proxy);
        try {
            return new C1189r(httpURLConnection, this.f836c, this.f837d);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return httpURLConnection;
        }
    }

    protected final int getDefaultPort() {
        return 80;
    }

    protected final String m831a() {
        return Scheme.HTTP;
    }
}
