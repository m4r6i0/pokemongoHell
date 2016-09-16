package crittercism.android;

import android.os.Build.VERSION;
import crittercism.android.C1194v.C1193a;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: crittercism.android.i */
public final class C1178i {
    public static final C1193a f812a;
    public static C1177b f813b;
    private static final List f814c;
    private ad f815d;
    private ab f816e;
    private ab f817f;
    private C1194v f818g;
    private C1171e f819h;
    private C1161d f820i;
    private C1177b f821j;
    private C1193a f822k;

    /* renamed from: crittercism.android.i.a */
    static class C1176a implements Runnable {
        private boolean f805a;
        private boolean f806b;
        private C1178i f807c;

        public C1176a(C1178i c1178i) {
            this.f806b = false;
            this.f807c = c1178i;
            this.f805a = true;
        }

        public final boolean m807a() {
            return this.f806b;
        }

        public final void run() {
            if (this.f805a) {
                this.f806b = this.f807c.m818c();
            } else {
                this.f807c.m817b();
            }
        }
    }

    /* renamed from: crittercism.android.i.b */
    public enum C1177b {
        SOCKET_MONITOR,
        STREAM_MONITOR,
        NONE
    }

    static {
        f812a = C1193a.HTTPS_ONLY;
        f813b = C1177b.NONE;
        f814c = new LinkedList();
        try {
            if (!((URLStreamHandler) C1179j.m819a(C1179j.m820a(URL.class, URLStreamHandler.class), new URL("https://www.google.com"))).getClass().getName().contains("okhttp") || VERSION.SDK_INT < 19) {
                f813b = C1177b.STREAM_MONITOR;
            } else {
                f813b = C1177b.SOCKET_MONITOR;
            }
        } catch (Exception e) {
            f813b = C1177b.NONE;
        }
    }

    public C1178i(C1171e c1171e, C1161d c1161d) {
        this.f821j = f813b;
        this.f822k = f812a;
        this.f819h = c1171e;
        this.f820i = c1161d;
    }

    public final boolean m816a() {
        if (ac.m266c()) {
            try {
                boolean e;
                boolean a;
                ac.m268e();
                int h = m815h() | 0;
                if (VERSION.SDK_INT >= 19) {
                    e = h | m812e();
                } else {
                    e = h | m818c();
                }
                if (VERSION.SDK_INT >= 17) {
                    a = e | C1197y.m873a(this.f819h, this.f820i);
                } else {
                    a = e;
                }
                if (this.f821j == C1177b.SOCKET_MONITOR) {
                    SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
                    if (defaultSSLSocketFactory instanceof ab) {
                        this.f816e = (ab) defaultSSLSocketFactory;
                    } else {
                        this.f816e = new ab(defaultSSLSocketFactory, this.f819h, this.f820i);
                        HttpsURLConnection.setDefaultSSLSocketFactory(this.f816e);
                    }
                    return a | 1;
                } else if (this.f821j == C1177b.STREAM_MONITOR) {
                    return a | m813f();
                } else {
                    return a;
                }
            } catch (Throwable e2) {
                dx.m776a(e2.toString(), e2);
                return false;
            }
        }
        C1178i.m808a("Unable to install OPTMZ", ac.m267d());
        return false;
    }

    private boolean m812e() {
        Object c1176a = new C1176a(this);
        Thread thread = new Thread(c1176a);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
        }
        return c1176a.m807a();
    }

    private boolean m813f() {
        boolean z = false;
        try {
            this.f818g = new C1194v(this.f822k, this.f819h, this.f820i);
            z = this.f818g.m845b();
        } catch (ClassNotFoundException e) {
        }
        return z;
    }

    public final void m817b() {
        try {
            SSLSocketFactory g = C1178i.m814g();
            if (g instanceof ab) {
                C1178i.m809a(((ab) g).m261a());
            }
            this.f817f = null;
        } catch (Throwable e) {
            C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e);
        } catch (Throwable e2) {
            C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e2);
        } catch (Throwable e22) {
            C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e22);
        }
    }

    public final boolean m818c() {
        try {
            SSLSocketFactory g = C1178i.m814g();
            if (g == null) {
                C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", new NullPointerException("Delegate factory was null"));
                return false;
            } else if (g instanceof ab) {
                return false;
            } else {
                SSLSocketFactory abVar = new ab(g, this.f819h, this.f820i);
                try {
                    C1178i.m809a(abVar);
                    this.f817f = abVar;
                    return true;
                } catch (Throwable e) {
                    C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e);
                    return false;
                } catch (Throwable e2) {
                    C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e2);
                    return false;
                } catch (Throwable e22) {
                    C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e22);
                    return false;
                }
            }
        } catch (Throwable e222) {
            C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e222);
            return false;
        } catch (Throwable e2222) {
            C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e2222);
            return false;
        } catch (Throwable e22222) {
            C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e22222);
            return false;
        } catch (Throwable e222222) {
            C1178i.m808a("Unable to install OPTIMZ for SSL HttpClient connections", e222222);
            return false;
        }
    }

    private static SSLSocketFactory m814g() {
        return (SSLSocketFactory) C1179j.m820a(org.apache.http.conn.ssl.SSLSocketFactory.class, SSLSocketFactory.class).get(org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory());
    }

    private static void m809a(SSLSocketFactory sSLSocketFactory) {
        C1179j.m820a(org.apache.http.conn.ssl.SSLSocketFactory.class, SSLSocketFactory.class).set(org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory(), sSLSocketFactory);
    }

    private boolean m815h() {
        Class cls = null;
        try {
            ad adVar;
            SocketImplFactory socketImplFactory = (SocketImplFactory) C1179j.m819a(C1179j.m820a(Socket.class, SocketImplFactory.class), null);
            if (socketImplFactory == null) {
                try {
                    SocketImpl socketImpl = (SocketImpl) C1179j.m819a(C1179j.m820a(Socket.class, SocketImpl.class), new Socket());
                    if (socketImpl == null) {
                        throw new cl("SocketImpl was null");
                    }
                    cls = socketImpl.getClass();
                } catch (Throwable e) {
                    C1178i.m808a("Unable to install OPTIMZ for http connections", e);
                    return false;
                }
            } else if (socketImplFactory instanceof ad) {
                return true;
            }
            if (socketImplFactory != null) {
                try {
                    SocketImplFactory adVar2 = new ad(socketImplFactory, this.f819h, this.f820i);
                    C1178i.m810a(adVar2);
                    adVar = adVar2;
                } catch (Throwable e2) {
                    C1178i.m808a("Unable to install OPTIMZ for http connections", e2);
                    return false;
                } catch (Throwable e22) {
                    C1178i.m808a("Unable to install OPTIMZ for http connections", e22);
                    return false;
                }
            } else if (cls != null) {
                adVar = new ad(cls, this.f819h, this.f820i);
                Socket.setSocketImplFactory(adVar);
            } else {
                C1178i.m808a("Unable to install OPTIMZ for http connections", new NullPointerException("Null SocketImpl"));
                return false;
            }
            this.f815d = adVar;
            return true;
        } catch (Throwable e222) {
            C1178i.m808a("Unable to install OPTIMZ for http connections", e222);
            return false;
        }
    }

    private static boolean m810a(SocketImplFactory socketImplFactory) {
        try {
            Field a = C1179j.m820a(Socket.class, SocketImplFactory.class);
            try {
                a.setAccessible(true);
                a.set(null, socketImplFactory);
                return true;
            } catch (Throwable e) {
                C1178i.m808a("Unable to install OPTIMZ for http connections", e);
                return true;
            } catch (Throwable e2) {
                C1178i.m808a("Unable to install OPTIMZ for http connections", e2);
                return false;
            } catch (Throwable e22) {
                C1178i.m808a("Unable to install OPTIMZ for http connections", e22);
                return false;
            }
        } catch (Throwable e222) {
            C1178i.m808a("Unable to install OPTIMZ for http connections", e222);
            return false;
        }
    }

    private static void m808a(String str, Throwable th) {
        synchronized (f814c) {
            f814c.add(th);
        }
        dx.m782c(str);
    }

    public static void m811d() {
        synchronized (f814c) {
            for (Throwable a : f814c) {
                dx.m777a(a);
            }
            f814c.clear();
        }
    }
}
