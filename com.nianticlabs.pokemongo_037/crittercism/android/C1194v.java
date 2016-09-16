package crittercism.android;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: crittercism.android.v */
public final class C1194v implements URLStreamHandlerFactory {
    private static final Object f866a;
    private static C1194v f867b;
    private LinkedList f868c;
    private boolean f869d;
    private boolean f870e;

    /* renamed from: crittercism.android.v.a */
    public enum C1193a {
        HTTP_ONLY,
        HTTPS_ONLY,
        ALL
    }

    static {
        f866a = new Object();
    }

    public static C1194v m841a() {
        return f867b;
    }

    public C1194v(C1193a c1193a, C1171e c1171e, C1161d c1161d) {
        this.f868c = new LinkedList();
        this.f869d = false;
        this.f870e = false;
        if (c1193a == C1193a.ALL || c1193a == C1193a.HTTP_ONLY) {
            this.f868c.add(new C1185o(c1171e, c1161d));
        }
        if (c1193a == C1193a.ALL || c1193a == C1193a.HTTPS_ONLY) {
            this.f868c.add(new C1188q(c1171e, c1161d));
        }
    }

    public final boolean m845b() {
        boolean z = true;
        synchronized (f866a) {
            if (f867b != null) {
                if (f867b != this) {
                    z = false;
                }
                return z;
            }
            if (!(this.f869d || this.f870e)) {
                try {
                    URL.setURLStreamHandlerFactory(this);
                    this.f869d = true;
                    f867b = this;
                } catch (Throwable th) {
                }
            }
            return this.f869d;
        }
    }

    private synchronized boolean m842d() {
        boolean z = false;
        synchronized (this) {
            synchronized (f866a) {
                if (f867b != this) {
                    boolean z2 = this.f869d;
                } else {
                    if (this.f869d && C1194v.m843e()) {
                        this.f869d = false;
                        f867b = null;
                    }
                    z = this.f869d;
                }
            }
        }
        return z;
    }

    public final URLStreamHandler createURLStreamHandler(String protocol) {
        try {
            if (!this.f870e) {
                Iterator it = this.f868c.iterator();
                while (it.hasNext()) {
                    C1183m c1183m = (C1183m) it.next();
                    if (c1183m.m827a().equals(protocol)) {
                        return c1183m;
                    }
                }
            }
            return null;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            this.f870e = true;
            dx.m777a(th);
            return null;
        }
    }

    private static boolean m843e() {
        Field[] declaredFields = URL.class.getDeclaredFields();
        int length = declaredFields.length;
        int i = 0;
        while (i < length) {
            Field field = declaredFields[i];
            if (URLStreamHandlerFactory.class.isAssignableFrom(field.getType())) {
                try {
                    ea eaVar = ea.STREAM_HANDLER_FACTORY_ANNUL_REFLECTION_FAULT;
                    field.setAccessible(true);
                    field.set(null, null);
                    field.setAccessible(false);
                    URL.setURLStreamHandlerFactory(null);
                    return true;
                } catch (IllegalAccessException e) {
                    dx.m781c();
                } catch (SecurityException e2) {
                    dx.m781c();
                } catch (Throwable th) {
                    dx.m781c();
                }
            } else {
                i++;
            }
        }
        return false;
    }

    private static boolean m844f() {
        for (Field field : URL.class.getDeclaredFields()) {
            if (Hashtable.class.isAssignableFrom(field.getType())) {
                ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                Class cls = (Class) parameterizedType.getActualTypeArguments()[0];
                Class cls2 = (Class) parameterizedType.getActualTypeArguments()[1];
                if (String.class.isAssignableFrom(cls) && URLStreamHandler.class.isAssignableFrom(cls2)) {
                    try {
                        ea eaVar = ea.STREAM_HANDLER_FACTORY_CLEAR_STREAM_HANDLERS_FAULT;
                        field.setAccessible(true);
                        Hashtable hashtable = (Hashtable) field.get(null);
                        if (hashtable != null) {
                            hashtable.clear();
                        }
                        field.setAccessible(false);
                        return true;
                    } catch (IllegalArgumentException e) {
                        dx.m781c();
                    } catch (SecurityException e2) {
                        dx.m781c();
                    } catch (IllegalAccessException e3) {
                        dx.m781c();
                    }
                }
            }
        }
        return false;
    }

    public final synchronized boolean m846c() {
        boolean z = false;
        synchronized (this) {
            m842d();
            boolean f;
            if (this.f869d) {
                this.f870e = true;
                f = C1194v.m844f();
            } else {
                f = false;
            }
            if (!this.f869d || r2) {
                z = true;
            }
        }
        return z;
    }
}
