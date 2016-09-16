package crittercism.android;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class ab extends SSLSocketFactory {
    private SSLSocketFactory f279a;
    private C1171e f280b;
    private C1161d f281c;

    public ab(SSLSocketFactory sSLSocketFactory, C1171e c1171e, C1161d c1161d) {
        this.f279a = sSLSocketFactory;
        this.f280b = c1171e;
        this.f281c = c1161d;
    }

    public final SSLSocketFactory m261a() {
        return this.f279a;
    }

    public final String[] getDefaultCipherSuites() {
        return this.f279a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.f279a.getSupportedCipherSuites();
    }

    private Socket m260a(Socket socket) {
        if (socket == null) {
            return socket;
        }
        try {
            if (!(socket instanceof SSLSocket)) {
                return socket;
            }
            return new aa((SSLSocket) socket, this.f280b, this.f281c);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return socket;
        }
    }

    public final Socket createSocket(Socket s, String host, int port, boolean autoClose) {
        return m260a(this.f279a.createSocket(s, host, port, autoClose));
    }

    public final Socket createSocket(String host, int port) {
        return m260a(this.f279a.createSocket(host, port));
    }

    public final Socket createSocket(String host, int port, InetAddress localHost, int localPort) {
        return m260a(this.f279a.createSocket(host, port, localHost, localPort));
    }

    public final Socket createSocket(InetAddress host, int port) {
        return m260a(this.f279a.createSocket(host, port));
    }

    public final Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) {
        return m260a(this.f279a.createSocket(address, port, localAddress, localPort));
    }

    public final Socket createSocket() {
        return m260a(this.f279a.createSocket());
    }
}
