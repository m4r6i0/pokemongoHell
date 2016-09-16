package crittercism.android;

import java.net.SocketImpl;
import java.net.SocketImplFactory;

public final class ad implements SocketImplFactory {
    private Class f295a;
    private SocketImplFactory f296b;
    private C1171e f297c;
    private C1161d f298d;

    public ad(Class cls, C1171e c1171e, C1161d c1161d) {
        this.f297c = c1171e;
        this.f298d = c1161d;
        this.f295a = cls;
        Class cls2 = this.f295a;
        if (cls2 == null) {
            throw new cl("Class was null");
        }
        try {
            cls2.newInstance();
        } catch (Throwable th) {
            cl clVar = new cl("Unable to create new instance", th);
        }
    }

    public ad(SocketImplFactory socketImplFactory, C1171e c1171e, C1161d c1161d) {
        this.f297c = c1171e;
        this.f298d = c1161d;
        this.f296b = socketImplFactory;
        SocketImplFactory socketImplFactory2 = this.f296b;
        if (socketImplFactory2 == null) {
            throw new cl("Factory was null");
        }
        try {
            if (socketImplFactory2.createSocketImpl() == null) {
                throw new cl("Factory does not work");
            }
        } catch (Throwable th) {
            cl clVar = new cl("Factory does not work", th);
        }
    }

    public final SocketImpl createSocketImpl() {
        SocketImpl socketImpl = null;
        if (this.f296b != null) {
            socketImpl = this.f296b.createSocketImpl();
        } else {
            Class cls = this.f295a;
            try {
                socketImpl = (SocketImpl) this.f295a.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            }
        }
        if (socketImpl != null) {
            return new ac(this.f297c, this.f298d, socketImpl);
        }
        return socketImpl;
    }
}
