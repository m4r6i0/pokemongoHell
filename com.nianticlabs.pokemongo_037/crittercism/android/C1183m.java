package crittercism.android;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/* renamed from: crittercism.android.m */
public abstract class C1183m extends URLStreamHandler {
    public static final String[] f834a;
    public static final String[] f835b;
    C1171e f836c;
    C1161d f837d;
    boolean f838e;
    private Constructor f839f;
    private Constructor f840g;

    protected abstract String m827a();

    protected abstract int getDefaultPort();

    static {
        f834a = new String[]{"java.net.URL", "int", "java.net.Proxy"};
        f835b = new String[]{"java.net.URL", "int"};
    }

    public C1183m(C1171e c1171e, C1161d c1161d, String[] strArr) {
        this(c1171e, c1161d, strArr, f834a, f835b);
    }

    private C1183m(C1171e c1171e, C1161d c1161d, String[] strArr, String[] strArr2, String[] strArr3) {
        this.f839f = null;
        this.f840g = null;
        this.f836c = c1171e;
        this.f837d = c1161d;
        this.f838e = true;
        int i = 0;
        while (i < strArr.length) {
            try {
                this.f839f = C1182l.m824a(strArr[i], strArr3);
                this.f840g = C1182l.m824a(strArr[i], strArr2);
                this.f839f.setAccessible(true);
                this.f840g.setAccessible(true);
                break;
            } catch (ClassNotFoundException e) {
                this.f839f = null;
                this.f839f = null;
                i++;
            }
        }
        if (this.f839f == null || this.f840g == null) {
            throw new ClassNotFoundException("Couldn't find suitable connection implementations");
        } else if (!m826b()) {
            throw new ClassNotFoundException("Unable to open test connections");
        }
    }

    private boolean m826b() {
        this.f838e = false;
        try {
            openConnection(new URL("http://www.google.com"));
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            this.f838e = true;
        }
    }

    protected URLConnection openConnection(URL u) {
        return m825a(u, null);
    }

    protected URLConnection openConnection(URL url, Proxy proxy) {
        if (url != null && proxy != null) {
            return m825a(url, proxy);
        }
        throw new IllegalArgumentException("url == null || proxy == null");
    }

    private URLConnection m825a(URL url, Proxy proxy) {
        IOException iOException;
        URLConnection uRLConnection = null;
        String str = "Unable to setup network statistics on a " + m827a() + " connection due to ";
        try {
            ea eaVar = ea.GENERIC_HANDLER_DO_OPEN_CONNECTION_FAULT;
            if (proxy == null) {
                uRLConnection = (URLConnection) this.f839f.newInstance(new Object[]{url, Integer.valueOf(getDefaultPort())});
                iOException = null;
            } else {
                uRLConnection = (URLConnection) this.f840g.newInstance(new Object[]{url, Integer.valueOf(getDefaultPort()), proxy});
                iOException = null;
            }
        } catch (IllegalArgumentException e) {
            new StringBuilder().append(str).append("bad arguments");
            dx.m778b();
            iOException = new IOException(e.getMessage());
        } catch (InstantiationException e2) {
            new StringBuilder().append(str).append("an instantiation problem");
            dx.m778b();
            iOException = new IOException(e2.getMessage());
        } catch (IllegalAccessException e3) {
            new StringBuilder().append(str).append("security restrictions");
            dx.m778b();
            iOException = new IOException(e3.getMessage());
        } catch (InvocationTargetException e4) {
            new StringBuilder().append(str).append("an invocation problem");
            dx.m778b();
            iOException = new IOException(e4.getMessage());
        }
        if (iOException != null) {
            if (this.f838e) {
                boolean c;
                this.f838e = false;
                C1194v a = C1194v.m841a();
                if (a != null) {
                    c = a.m846c();
                } else {
                    c = false;
                }
                dx.m779b("Stopping network statistics monitoring");
                if (c) {
                    return new URL(url.toExternalForm()).openConnection();
                }
            }
            throw iOException;
        }
        return uRLConnection;
    }
}
