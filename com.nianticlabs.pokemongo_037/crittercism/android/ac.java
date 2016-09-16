package crittercism.android;

import android.support.v4.os.EnvironmentCompat;
import crittercism.android.C1153c.C1152a;
import crittercism.android.C1181k.C1180a;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketImpl;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

public final class ac extends SocketImpl implements ae {
    private static Field f282a;
    private static Field f283b;
    private static Field f284c;
    private static Field f285d;
    private static Method[] f286e;
    private static boolean f287f;
    private static Throwable f288g;
    private final Queue f289h;
    private C1171e f290i;
    private C1161d f291j;
    private SocketImpl f292k;
    private C1195w f293l;
    private C1196x f294m;

    /* renamed from: crittercism.android.ac.1 */
    static class C10981 extends SocketImpl {
        C10981() {
        }

        public final void setOption(int i, Object obj) {
        }

        public final Object getOption(int i) {
            return null;
        }

        protected final void sendUrgentData(int i) {
        }

        protected final void listen(int i) {
        }

        protected final OutputStream getOutputStream() {
            return null;
        }

        protected final InputStream getInputStream() {
            return null;
        }

        protected final void create(boolean z) {
        }

        protected final void connect(SocketAddress socketAddress, int i) {
        }

        protected final void connect(InetAddress inetAddress, int i) {
        }

        protected final void connect(String str, int i) {
        }

        protected final void close() {
        }

        protected final void bind(InetAddress inetAddress, int i) {
        }

        protected final int available() {
            return 0;
        }

        protected final void accept(SocketImpl socketImpl) {
        }

        protected final FileDescriptor getFileDescriptor() {
            return null;
        }

        protected final InetAddress getInetAddress() {
            return null;
        }

        protected final int getLocalPort() {
            return 0;
        }

        protected final int getPort() {
            return 0;
        }

        protected final void setPerformancePreferences(int i, int i2, int i3) {
        }

        protected final void shutdownInput() {
        }

        protected final void shutdownOutput() {
        }

        protected final boolean supportsUrgentData() {
            return false;
        }

        public final String toString() {
            return null;
        }
    }

    /* renamed from: crittercism.android.ac.2 */
    static class C10992 implements Executor {
        C10992() {
        }

        public final void execute(Runnable runnable) {
        }
    }

    static {
        f286e = new Method[20];
        f287f = false;
        f288g = null;
        try {
            Class cls = SocketImpl.class;
            f282a = cls.getDeclaredField("address");
            f283b = cls.getDeclaredField("fd");
            f284c = cls.getDeclaredField("localport");
            f285d = cls.getDeclaredField("port");
            AccessibleObject accessibleObject = f282a;
            AccessibleObject[] accessibleObjectArr = new AccessibleObject[]{f283b, f284c, f285d};
            if (accessibleObject != null) {
                accessibleObject.setAccessible(true);
            }
            if (accessibleObjectArr.length > 0) {
                C1179j.m821a(accessibleObjectArr);
            }
            f286e[0] = cls.getDeclaredMethod("accept", new Class[]{SocketImpl.class});
            f286e[1] = cls.getDeclaredMethod("available", new Class[0]);
            f286e[2] = cls.getDeclaredMethod("bind", new Class[]{InetAddress.class, Integer.TYPE});
            f286e[3] = cls.getDeclaredMethod("close", new Class[0]);
            f286e[4] = cls.getDeclaredMethod("connect", new Class[]{InetAddress.class, Integer.TYPE});
            f286e[5] = cls.getDeclaredMethod("connect", new Class[]{SocketAddress.class, Integer.TYPE});
            f286e[6] = cls.getDeclaredMethod("connect", new Class[]{String.class, Integer.TYPE});
            f286e[7] = cls.getDeclaredMethod("create", new Class[]{Boolean.TYPE});
            f286e[8] = cls.getDeclaredMethod("getFileDescriptor", new Class[0]);
            f286e[9] = cls.getDeclaredMethod("getInetAddress", new Class[0]);
            f286e[10] = cls.getDeclaredMethod("getInputStream", new Class[0]);
            f286e[11] = cls.getDeclaredMethod("getLocalPort", new Class[0]);
            f286e[12] = cls.getDeclaredMethod("getOutputStream", new Class[0]);
            f286e[13] = cls.getDeclaredMethod("getPort", new Class[0]);
            f286e[14] = cls.getDeclaredMethod("listen", new Class[]{Integer.TYPE});
            f286e[15] = cls.getDeclaredMethod("sendUrgentData", new Class[]{Integer.TYPE});
            f286e[16] = cls.getDeclaredMethod("setPerformancePreferences", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE});
            f286e[17] = cls.getDeclaredMethod("shutdownInput", new Class[0]);
            f286e[18] = cls.getDeclaredMethod("shutdownOutput", new Class[0]);
            f286e[19] = cls.getDeclaredMethod("supportsUrgentData", new Class[0]);
            C1179j.m821a(f286e);
            f287f = true;
        } catch (Throwable e) {
            f287f = false;
            f288g = e;
        } catch (Throwable e2) {
            Throwable th = e2;
            f287f = false;
            int i = 0;
            while (i < 20) {
                if (f286e[i] == null) {
                    break;
                }
                i++;
            }
            i = 20;
            f288g = new ck("Bad method: " + i, th);
        } catch (Throwable e22) {
            Throwable th2 = e22;
            f287f = false;
            String str = EnvironmentCompat.MEDIA_UNKNOWN;
            if (f282a == null) {
                str = "address";
            } else if (f283b == null) {
                str = "fd";
            } else if (f284c == null) {
                str = "localport";
            } else if (f285d == null) {
                str = "port";
            }
            f288g = new ck("No such field: " + str, th2);
        } catch (Throwable e222) {
            f287f = false;
            f288g = e222;
        }
    }

    public ac(C1171e c1171e, C1161d c1161d, SocketImpl socketImpl) {
        this.f289h = new LinkedList();
        if (c1171e == null) {
            throw new NullPointerException("dispatch was null");
        } else if (socketImpl == null) {
            throw new NullPointerException("delegate was null");
        } else {
            this.f290i = c1171e;
            this.f291j = c1161d;
            this.f292k = socketImpl;
            m269f();
        }
    }

    public static boolean m266c() {
        return f287f;
    }

    public static Throwable m267d() {
        return f288g;
    }

    private void m269f() {
        try {
            this.address = (InetAddress) f282a.get(this.f292k);
            this.fd = (FileDescriptor) f283b.get(this.f292k);
            this.localport = f284c.getInt(this.f292k);
            this.port = f285d.getInt(this.f292k);
        } catch (Throwable e) {
            throw new ck(e);
        } catch (Throwable e2) {
            throw new ck(e2);
        }
    }

    private Object m263a(int i, Object... objArr) {
        Throwable e;
        try {
            f282a.set(this.f292k, this.address);
            f283b.set(this.f292k, this.fd);
            f284c.setInt(this.f292k, this.localport);
            f285d.setInt(this.f292k, this.port);
            try {
                Object invoke = f286e[i].invoke(this.f292k, objArr);
                m269f();
                return invoke;
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
                    throw new ck(e222);
                }
            } catch (Throwable e2222) {
                throw new ck(e2222);
            } catch (Throwable e22222) {
                throw new ck(e22222);
            } catch (Throwable th2) {
                m269f();
            }
        } catch (Throwable e222222) {
            throw new ck(e222222);
        } catch (Throwable e2222222) {
            throw new ck(e2222222);
        }
    }

    private Object m264b(int i, Object... objArr) {
        try {
            return m263a(i, objArr);
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            throw new ck(e2);
        }
    }

    private Object m265c(int i, Object... objArr) {
        try {
            return m263a(i, objArr);
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Throwable e3) {
            throw new ck(e3);
        }
    }

    public final InputStream getInputStream() {
        InputStream inputStream = (InputStream) m265c(10, new Object[0]);
        if (inputStream == null) {
            return inputStream;
        }
        try {
            if (this.f294m != null && this.f294m.m866a(inputStream)) {
                return this.f294m;
            }
            this.f294m = new C1196x(this, inputStream, this.f290i);
            return this.f294m;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return inputStream;
        }
    }

    public final OutputStream getOutputStream() {
        OutputStream outputStream = (OutputStream) m265c(12, new Object[0]);
        if (outputStream == null) {
            return outputStream;
        }
        try {
            if (this.f293l != null && this.f293l.m854a(outputStream)) {
                return this.f293l;
            }
            this.f293l = new C1195w(this, outputStream);
            return this.f293l;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return outputStream;
        }
    }

    public final void create(boolean stream) {
        m265c(7, Boolean.valueOf(stream));
    }

    public final void connect(String host, int port) {
        try {
            m265c(6, host, Integer.valueOf(port));
        } catch (Throwable e) {
            if (host != null) {
                try {
                    C1153c a = m262a(false);
                    a.m665b();
                    a.m668c();
                    a.m674f();
                    a.m667b(host);
                    a.m657a(port);
                    a.m663a(e);
                    this.f290i.m786a(a, C1152a.SOCKET_IMPL_CONNECT);
                } catch (ThreadDeath e2) {
                    throw e2;
                } catch (Throwable th) {
                    dx.m777a(th);
                }
            }
            throw e;
        }
    }

    public final void connect(InetAddress address, int port) {
        try {
            m265c(4, address, Integer.valueOf(port));
        } catch (Throwable e) {
            if (address != null) {
                try {
                    C1153c a = m262a(false);
                    a.m665b();
                    a.m668c();
                    a.m674f();
                    a.m664a(address);
                    a.m657a(port);
                    a.m663a(e);
                    this.f290i.m786a(a, C1152a.SOCKET_IMPL_CONNECT);
                } catch (ThreadDeath e2) {
                    throw e2;
                } catch (Throwable th) {
                    dx.m777a(th);
                }
            }
            throw e;
        }
    }

    public final void connect(SocketAddress address, int timeout) {
        try {
            m265c(5, address, Integer.valueOf(timeout));
        } catch (Throwable e) {
            if (address != null) {
                try {
                    if (address instanceof InetSocketAddress) {
                        C1153c a = m262a(false);
                        InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                        a.m665b();
                        a.m668c();
                        a.m674f();
                        a.m664a(inetSocketAddress.getAddress());
                        a.m657a(inetSocketAddress.getPort());
                        a.m663a(e);
                        this.f290i.m786a(a, C1152a.SOCKET_IMPL_CONNECT);
                    }
                } catch (ThreadDeath e2) {
                    throw e2;
                } catch (Throwable th) {
                    dx.m777a(th);
                }
            }
            throw e;
        }
    }

    public final void bind(InetAddress host, int port) {
        m265c(2, host, Integer.valueOf(port));
    }

    public final void listen(int backlog) {
        m265c(14, Integer.valueOf(backlog));
    }

    public final void accept(SocketImpl s) {
        m265c(0, s);
    }

    public final int available() {
        Integer num = (Integer) m265c(1, new Object[0]);
        if (num != null) {
            return num.intValue();
        }
        throw new ck("Received a null Integer");
    }

    public final void close() {
        m265c(3, new Object[0]);
        try {
            if (this.f294m != null) {
                this.f294m.m870d();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final void shutdownInput() {
        m265c(17, new Object[0]);
    }

    public final void shutdownOutput() {
        m265c(18, new Object[0]);
    }

    public final FileDescriptor getFileDescriptor() {
        return (FileDescriptor) m264b(8, new Object[0]);
    }

    public final InetAddress getInetAddress() {
        return (InetAddress) m264b(9, new Object[0]);
    }

    public final int getPort() {
        return ((Integer) m264b(13, new Object[0])).intValue();
    }

    public final boolean supportsUrgentData() {
        return ((Boolean) m264b(19, new Object[0])).booleanValue();
    }

    public final void sendUrgentData(int data) {
        m265c(15, Integer.valueOf(data));
    }

    public final int getLocalPort() {
        return ((Integer) m264b(11, new Object[0])).intValue();
    }

    public final String toString() {
        return this.f292k.toString();
    }

    public final void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
        m264b(16, Integer.valueOf(connectionTime), Integer.valueOf(latency), Integer.valueOf(bandwidth));
    }

    public final void setOption(int optID, Object value) {
        this.f292k.setOption(optID, value);
    }

    public final Object getOption(int optID) {
        return this.f292k.getOption(optID);
    }

    private C1153c m262a(boolean z) {
        C1153c c1153c = new C1153c();
        InetAddress inetAddress = getInetAddress();
        if (inetAddress != null) {
            c1153c.m664a(inetAddress);
        }
        int port = getPort();
        if (port > 0) {
            c1153c.m657a(port);
        }
        if (z) {
            c1153c.m660a(C1180a.HTTP);
        }
        if (this.f291j != null) {
            c1153c.f585j = this.f291j.m717a();
        }
        if (bc.m475b()) {
            c1153c.m659a(bc.m473a());
        }
        return c1153c;
    }

    public final C1153c m270a() {
        return m262a(true);
    }

    public final void m271a(C1153c c1153c) {
        synchronized (this.f289h) {
            this.f289h.add(c1153c);
        }
    }

    public final C1153c m272b() {
        C1153c c1153c;
        synchronized (this.f289h) {
            c1153c = (C1153c) this.f289h.poll();
        }
        return c1153c;
    }

    public static void m268e() {
        if (f287f) {
            SocketImpl acVar = new ac(new C1171e(new C10992()), null, new C10981());
            try {
                acVar.setOption(0, new Object());
                acVar.getOption(0);
                acVar.sendUrgentData(0);
                acVar.listen(0);
                acVar.getOutputStream();
                acVar.getInputStream();
                acVar.create(false);
                acVar.connect(null, 0);
                acVar.connect(null, 0);
                acVar.connect(null, 0);
                acVar.close();
                acVar.bind(null, 0);
                acVar.available();
                acVar.accept(acVar);
                acVar.getFileDescriptor();
                acVar.getInetAddress();
                acVar.getLocalPort();
                acVar.getPort();
                acVar.setPerformancePreferences(0, 0, 0);
                acVar.shutdownInput();
                acVar.shutdownOutput();
                acVar.supportsUrgentData();
            } catch (IOException e) {
            } catch (ck e2) {
                throw e2;
            } catch (Throwable th) {
                ck ckVar = new ck(th);
            }
        } else {
            throw new ck(f288g);
        }
    }
}
