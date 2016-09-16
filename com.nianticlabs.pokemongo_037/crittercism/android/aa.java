package crittercism.android;

import crittercism.android.C1153c.C1152a;
import crittercism.android.C1181k.C1180a;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Queue;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

public final class aa extends SSLSocket implements ae {
    private SSLSocket f273a;
    private C1171e f274b;
    private C1161d f275c;
    private final Queue f276d;
    private C1195w f277e;
    private C1196x f278f;

    public aa(SSLSocket sSLSocket, C1171e c1171e, C1161d c1161d) {
        this.f276d = new LinkedList();
        if (sSLSocket == null) {
            throw new NullPointerException("delegate was null");
        } else if (c1171e == null) {
            throw new NullPointerException("dispatch was null");
        } else {
            this.f273a = sSLSocket;
            this.f274b = c1171e;
            this.f275c = c1161d;
        }
    }

    public final void addHandshakeCompletedListener(HandshakeCompletedListener listener) {
        this.f273a.addHandshakeCompletedListener(listener);
    }

    public final boolean getEnableSessionCreation() {
        return this.f273a.getEnableSessionCreation();
    }

    public final String[] getEnabledCipherSuites() {
        return this.f273a.getEnabledCipherSuites();
    }

    public final String[] getEnabledProtocols() {
        return this.f273a.getEnabledProtocols();
    }

    public final boolean getNeedClientAuth() {
        return this.f273a.getNeedClientAuth();
    }

    public final SSLSession getSession() {
        return this.f273a.getSession();
    }

    public final String[] getSupportedCipherSuites() {
        return this.f273a.getSupportedCipherSuites();
    }

    public final String[] getSupportedProtocols() {
        return this.f273a.getSupportedProtocols();
    }

    public final boolean getUseClientMode() {
        return this.f273a.getUseClientMode();
    }

    public final boolean getWantClientAuth() {
        return this.f273a.getWantClientAuth();
    }

    public final void removeHandshakeCompletedListener(HandshakeCompletedListener listener) {
        this.f273a.removeHandshakeCompletedListener(listener);
    }

    public final void setEnableSessionCreation(boolean flag) {
        this.f273a.setEnableSessionCreation(flag);
    }

    public final void setEnabledCipherSuites(String[] suites) {
        this.f273a.setEnabledCipherSuites(suites);
    }

    public final void setEnabledProtocols(String[] protocols) {
        this.f273a.setEnabledProtocols(protocols);
    }

    public final void setNeedClientAuth(boolean need) {
        this.f273a.setNeedClientAuth(need);
    }

    public final void setUseClientMode(boolean mode) {
        this.f273a.setUseClientMode(mode);
    }

    public final void setWantClientAuth(boolean want) {
        this.f273a.setWantClientAuth(want);
    }

    public final void startHandshake() {
        try {
            this.f273a.startHandshake();
        } catch (Throwable e) {
            try {
                C1153c a = m256a(true);
                a.m665b();
                a.m668c();
                a.m674f();
                a.m663a(e);
                this.f274b.m786a(a, C1152a.SSL_SOCKET_START_HANDSHAKE);
            } catch (ThreadDeath e2) {
                throw e2;
            } catch (Throwable th) {
                dx.m777a(th);
            }
            throw e;
        }
    }

    public final void bind(SocketAddress localAddr) {
        this.f273a.bind(localAddr);
    }

    public final void close() {
        this.f273a.close();
        try {
            if (this.f278f != null) {
                this.f278f.m870d();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final void connect(SocketAddress remoteAddr, int timeout) {
        this.f273a.connect(remoteAddr, timeout);
    }

    public final void connect(SocketAddress remoteAddr) {
        this.f273a.connect(remoteAddr);
    }

    public final SocketChannel getChannel() {
        return this.f273a.getChannel();
    }

    public final InetAddress getInetAddress() {
        return this.f273a.getInetAddress();
    }

    public final InputStream getInputStream() {
        InputStream inputStream = this.f273a.getInputStream();
        if (inputStream == null) {
            return inputStream;
        }
        try {
            if (this.f278f != null && this.f278f.m866a(inputStream)) {
                return this.f278f;
            }
            this.f278f = new C1196x(this, inputStream, this.f274b);
            return this.f278f;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return inputStream;
        }
    }

    public final boolean getKeepAlive() {
        return this.f273a.getKeepAlive();
    }

    public final InetAddress getLocalAddress() {
        return this.f273a.getLocalAddress();
    }

    public final int getLocalPort() {
        return this.f273a.getLocalPort();
    }

    public final SocketAddress getLocalSocketAddress() {
        return this.f273a.getLocalSocketAddress();
    }

    public final boolean getOOBInline() {
        return this.f273a.getOOBInline();
    }

    public final OutputStream getOutputStream() {
        OutputStream outputStream = this.f273a.getOutputStream();
        if (outputStream == null) {
            return outputStream;
        }
        try {
            if (this.f277e != null && this.f277e.m854a(outputStream)) {
                return this.f277e;
            }
            C1195w c1195w = this.f277e;
            this.f277e = new C1195w(this, outputStream);
            return this.f277e;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return outputStream;
        }
    }

    public final int getPort() {
        return this.f273a.getPort();
    }

    public final int getReceiveBufferSize() {
        return this.f273a.getReceiveBufferSize();
    }

    public final SocketAddress getRemoteSocketAddress() {
        return this.f273a.getRemoteSocketAddress();
    }

    public final boolean getReuseAddress() {
        return this.f273a.getReuseAddress();
    }

    public final int getSendBufferSize() {
        return this.f273a.getSendBufferSize();
    }

    public final int getSoLinger() {
        return this.f273a.getSoLinger();
    }

    public final int getSoTimeout() {
        return this.f273a.getSoTimeout();
    }

    public final boolean getTcpNoDelay() {
        return this.f273a.getTcpNoDelay();
    }

    public final int getTrafficClass() {
        return this.f273a.getTrafficClass();
    }

    public final boolean isBound() {
        return this.f273a.isBound();
    }

    public final boolean isClosed() {
        return this.f273a.isClosed();
    }

    public final boolean isConnected() {
        return this.f273a.isConnected();
    }

    public final boolean isInputShutdown() {
        return this.f273a.isInputShutdown();
    }

    public final boolean isOutputShutdown() {
        return this.f273a.isOutputShutdown();
    }

    public final void sendUrgentData(int value) {
        this.f273a.sendUrgentData(value);
    }

    public final void setKeepAlive(boolean keepAlive) {
        this.f273a.setKeepAlive(keepAlive);
    }

    public final void setOOBInline(boolean oobinline) {
        this.f273a.setOOBInline(oobinline);
    }

    public final void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
        this.f273a.setPerformancePreferences(connectionTime, latency, bandwidth);
    }

    public final void setReceiveBufferSize(int size) {
        this.f273a.setReceiveBufferSize(size);
    }

    public final void setReuseAddress(boolean reuse) {
        this.f273a.setReuseAddress(reuse);
    }

    public final void setSendBufferSize(int size) {
        this.f273a.setSendBufferSize(size);
    }

    public final void setSoLinger(boolean on, int timeout) {
        this.f273a.setSoLinger(on, timeout);
    }

    public final void setSoTimeout(int timeout) {
        this.f273a.setSoTimeout(timeout);
    }

    public final void setTcpNoDelay(boolean on) {
        this.f273a.setTcpNoDelay(on);
    }

    public final void setTrafficClass(int value) {
        this.f273a.setTrafficClass(value);
    }

    public final void shutdownInput() {
        this.f273a.shutdownInput();
    }

    public final void shutdownOutput() {
        this.f273a.shutdownOutput();
    }

    public final String toString() {
        return this.f273a.toString();
    }

    public final boolean equals(Object o) {
        return this.f273a.equals(o);
    }

    public final int hashCode() {
        return this.f273a.hashCode();
    }

    public final C1153c m257a() {
        return m256a(false);
    }

    private C1153c m256a(boolean z) {
        C1153c c1153c = new C1153c();
        InetAddress inetAddress = this.f273a.getInetAddress();
        if (inetAddress != null) {
            c1153c.m664a(inetAddress);
        }
        if (z) {
            c1153c.m657a(getPort());
        }
        c1153c.m660a(C1180a.HTTPS);
        if (this.f275c != null) {
            c1153c.f585j = this.f275c.m717a();
        }
        if (bc.m475b()) {
            c1153c.m659a(bc.m473a());
        }
        return c1153c;
    }

    public final void m258a(C1153c c1153c) {
        if (c1153c != null) {
            synchronized (this.f276d) {
                this.f276d.add(c1153c);
            }
        }
    }

    public final C1153c m259b() {
        C1153c c1153c;
        synchronized (this.f276d) {
            c1153c = (C1153c) this.f276d.poll();
        }
        return c1153c;
    }
}
