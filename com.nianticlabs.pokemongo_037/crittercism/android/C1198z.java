package crittercism.android;

import java.lang.reflect.Method;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* renamed from: crittercism.android.z */
public final class C1198z extends SSLContextSpi {
    private static Method[] f884a;
    private static boolean f885b;
    private SSLContextSpi f886c;
    private C1171e f887d;
    private C1161d f888e;

    static {
        f884a = new Method[7];
        f885b = false;
        try {
            f884a[0] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", new Class[0]);
            f884a[1] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", new Class[]{String.class, Integer.TYPE});
            f884a[2] = SSLContextSpi.class.getDeclaredMethod("engineGetClientSessionContext", new Class[0]);
            f884a[3] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSessionContext", new Class[0]);
            f884a[4] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSocketFactory", new Class[0]);
            f884a[5] = SSLContextSpi.class.getDeclaredMethod("engineGetSocketFactory", new Class[0]);
            f884a[6] = SSLContextSpi.class.getDeclaredMethod("engineInit", new Class[]{KeyManager[].class, TrustManager[].class, SecureRandom.class});
            C1179j.m821a(f884a);
            C1198z c1198z = new C1198z(new C1198z(), null, null);
            c1198z.engineCreateSSLEngine();
            c1198z.engineCreateSSLEngine(null, 0);
            c1198z.engineGetClientSessionContext();
            c1198z.engineGetServerSessionContext();
            c1198z.engineGetServerSocketFactory();
            c1198z.engineGetSocketFactory();
            c1198z.engineInit(null, null, null);
            f885b = true;
        } catch (Throwable th) {
            dx.m781c();
            f885b = false;
        }
    }

    private C1198z(SSLContextSpi sSLContextSpi, C1171e c1171e, C1161d c1161d) {
        this.f886c = sSLContextSpi;
        this.f887d = c1171e;
        this.f888e = c1161d;
    }

    public static C1198z m875a(SSLContextSpi sSLContextSpi, C1171e c1171e, C1161d c1161d) {
        if (f885b) {
            return new C1198z(sSLContextSpi, c1171e, c1161d);
        }
        return null;
    }

    private C1198z() {
    }

    public static boolean m878a() {
        return f885b;
    }

    private Object m876a(int i, Object... objArr) {
        Throwable e;
        if (this.f886c == null) {
            return null;
        }
        try {
            return f884a[i].invoke(this.f886c, objArr);
        } catch (Throwable e2) {
            throw new ck(e2);
        } catch (Throwable e22) {
            throw new ck(e22);
        } catch (Throwable e222) {
            Throwable th = e222;
            e222 = th.getTargetException();
            if (e222 == null) {
                throw new ck(th);
            } else if (e222 instanceof Exception) {
                throw ((Exception) e222);
            } else if (e222 instanceof Error) {
                throw ((Error) e222);
            } else {
                throw new ck(th);
            }
        } catch (Throwable e2222) {
            throw new ck(e2222);
        }
    }

    private Object m879b(int i, Object... objArr) {
        try {
            return m876a(i, objArr);
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            throw new ck(e2);
        }
    }

    private Object m877a(Object... objArr) {
        try {
            return m876a(6, objArr);
        } catch (RuntimeException e) {
            throw e;
        } catch (KeyManagementException e2) {
            throw e2;
        } catch (Throwable e3) {
            throw new ck(e3);
        }
    }

    protected final SSLEngine engineCreateSSLEngine() {
        return (SSLEngine) m879b(0, new Object[0]);
    }

    protected final SSLEngine engineCreateSSLEngine(String host, int port) {
        return (SSLEngine) m879b(1, host, Integer.valueOf(port));
    }

    protected final SSLSessionContext engineGetClientSessionContext() {
        return (SSLSessionContext) m879b(2, new Object[0]);
    }

    protected final SSLSessionContext engineGetServerSessionContext() {
        return (SSLSessionContext) m879b(3, new Object[0]);
    }

    protected final SSLServerSocketFactory engineGetServerSocketFactory() {
        return (SSLServerSocketFactory) m879b(4, new Object[0]);
    }

    protected final SSLSocketFactory engineGetSocketFactory() {
        SSLSocketFactory sSLSocketFactory = (SSLSocketFactory) m879b(5, new Object[0]);
        if (sSLSocketFactory == null) {
            return sSLSocketFactory;
        }
        try {
            return new ab(sSLSocketFactory, this.f887d, this.f888e);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return sSLSocketFactory;
        }
    }

    protected final void engineInit(KeyManager[] km, TrustManager[] tm, SecureRandom sr) {
        m877a(km, tm, sr);
    }

    public final boolean equals(Object o) {
        SSLContextSpi sSLContextSpi = this.f886c;
        return this.f886c.equals(o);
    }

    public final int hashCode() {
        SSLContextSpi sSLContextSpi = this.f886c;
        return this.f886c.hashCode();
    }

    public final String toString() {
        SSLContextSpi sSLContextSpi = this.f886c;
        return this.f886c.toString();
    }
}
