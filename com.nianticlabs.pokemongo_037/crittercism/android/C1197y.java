package crittercism.android;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Provider.Service;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;

/* renamed from: crittercism.android.y */
public final class C1197y extends Service {
    public static final String[] f880a;
    private C1171e f881b;
    private C1161d f882c;
    private Service f883d;

    static {
        f880a = new String[]{"Default", "SSL", "TLSv1.1", "TLSv1.2", "SSLv3", "TLSv1", "TLS"};
    }

    private C1197y(Service service, C1171e c1171e, C1161d c1161d) {
        super(service.getProvider(), service.getType(), service.getAlgorithm(), service.getClassName(), null, null);
        this.f881b = c1171e;
        this.f882c = c1161d;
        this.f883d = service;
    }

    private static C1197y m871a(Service service, C1171e c1171e, C1161d c1161d) {
        C1197y c1197y = new C1197y(service, c1171e, c1161d);
        try {
            Field[] fields = Service.class.getFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                fields[i].set(c1197y, fields[i].get(service));
            }
            return c1197y;
        } catch (Exception e) {
            return null;
        }
    }

    private static Provider m872a() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            if (instance != null) {
                return instance.getProvider();
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static boolean m873a(C1171e c1171e, C1161d c1161d) {
        int i = 0;
        if (!C1198z.m878a()) {
            return false;
        }
        Provider a = C1197y.m872a();
        if (a == null) {
            return false;
        }
        boolean z = false;
        while (i < f880a.length) {
            Service service = a.getService("SSLContext", f880a[i]);
            if (!(service == null || (service instanceof C1197y))) {
                C1197y a2 = C1197y.m871a(service, c1171e, c1161d);
                if (a2 != null) {
                    z |= a2.m874b();
                }
            }
            i++;
        }
        return z;
    }

    private boolean m874b() {
        Provider provider = getProvider();
        if (provider == null) {
            return false;
        }
        try {
            Method declaredMethod = Provider.class.getDeclaredMethod("putService", new Class[]{Service.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(provider, new Object[]{this});
            String str = "SSLContext.DummySSLAlgorithm";
            provider.put(str, getClassName());
            provider.remove(getType() + "." + getAlgorithm());
            provider.remove(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public final Object newInstance(Object constructorParameter) {
        Object newInstance = super.newInstance(constructorParameter);
        try {
            if (!(newInstance instanceof SSLContextSpi)) {
                return newInstance;
            }
            C1198z a = C1198z.m875a((SSLContextSpi) newInstance, this.f881b, this.f882c);
            if (a != null) {
                return a;
            }
            return newInstance;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return newInstance;
        }
    }
}
