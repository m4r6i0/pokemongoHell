package crittercism.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: crittercism.android.s */
public final class C1190s extends HttpsURLConnection {
    private C1171e f851a;
    private HttpsURLConnection f852b;
    private C1153c f853c;
    private C1161d f854d;
    private boolean f855e;
    private boolean f856f;

    public C1190s(HttpsURLConnection httpsURLConnection, C1171e c1171e, C1161d c1161d) {
        super(httpsURLConnection.getURL());
        this.f851a = null;
        this.f852b = null;
        this.f853c = null;
        this.f854d = null;
        this.f855e = false;
        this.f856f = false;
        this.f851a = c1171e;
        this.f852b = httpsURLConnection;
        this.f854d = c1161d;
        this.f853c = new C1153c(httpsURLConnection.getURL());
        SSLSocketFactory sSLSocketFactory = this.f852b.getSSLSocketFactory();
        if (sSLSocketFactory instanceof ab) {
            this.f852b.setSSLSocketFactory(((ab) sSLSocketFactory).m261a());
        }
    }

    private void m836a() {
        try {
            if (!this.f856f) {
                this.f856f = true;
                this.f853c.f581f = this.f852b.getRequestMethod();
                this.f853c.m665b();
                this.f853c.f585j = this.f854d.m717a();
                if (bc.m475b()) {
                    this.f853c.m659a(bc.m473a());
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private void m837a(Throwable th) {
        try {
            if (!this.f855e) {
                this.f855e = true;
                this.f853c.m668c();
                this.f853c.m663a(th);
                this.f851a.m785a(this.f853c);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th2) {
            dx.m777a(th2);
        }
    }

    private void m838b() {
        Object obj = null;
        try {
            if (!this.f855e) {
                this.f855e = true;
                this.f853c.m668c();
                if (this.f852b.getHeaderFields() != null) {
                    C1187p c1187p = new C1187p(this.f852b.getHeaderFields());
                    int b = c1187p.m830b("Content-Length");
                    if (b != -1) {
                        this.f853c.m666b((long) b);
                        obj = 1;
                    }
                    long a = c1187p.m829a("X-Android-Sent-Millis");
                    long a2 = c1187p.m829a("X-Android-Received-Millis");
                    if (!(a == Long.MAX_VALUE || a2 == Long.MAX_VALUE)) {
                        this.f853c.m673e(a);
                        this.f853c.m675f(a2);
                    }
                }
                try {
                    this.f853c.f580e = this.f852b.getResponseCode();
                } catch (IOException e) {
                }
                if (obj != null) {
                    this.f851a.m785a(this.f853c);
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final String getCipherSuite() {
        return this.f852b.getCipherSuite();
    }

    public final HostnameVerifier getHostnameVerifier() {
        return this.f852b.getHostnameVerifier();
    }

    public final Certificate[] getLocalCertificates() {
        return this.f852b.getLocalCertificates();
    }

    public final Principal getLocalPrincipal() {
        return this.f852b.getLocalPrincipal();
    }

    public final Principal getPeerPrincipal() {
        return this.f852b.getPeerPrincipal();
    }

    public final SSLSocketFactory getSSLSocketFactory() {
        return this.f852b.getSSLSocketFactory();
    }

    public final Certificate[] getServerCertificates() {
        return this.f852b.getServerCertificates();
    }

    public final void setHostnameVerifier(HostnameVerifier v) {
        this.f852b.setHostnameVerifier(v);
    }

    public final void setSSLSocketFactory(SSLSocketFactory sf) {
        try {
            if (sf instanceof ab) {
                sf = ((ab) sf).m261a();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
        this.f852b.setSSLSocketFactory(sf);
    }

    public final void disconnect() {
        this.f852b.disconnect();
        try {
            if (this.f855e && !this.f853c.f577b) {
                this.f851a.m785a(this.f853c);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final InputStream getErrorStream() {
        m836a();
        InputStream errorStream = this.f852b.getErrorStream();
        m838b();
        if (errorStream != null) {
            try {
                return new C1191t(errorStream, this.f851a, this.f853c);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                dx.m777a(th);
            }
        }
        return errorStream;
    }

    public final long getHeaderFieldDate(String field, long defaultValue) {
        m836a();
        long headerFieldDate = this.f852b.getHeaderFieldDate(field, defaultValue);
        m838b();
        return headerFieldDate;
    }

    public final boolean getInstanceFollowRedirects() {
        return this.f852b.getInstanceFollowRedirects();
    }

    public final Permission getPermission() {
        return this.f852b.getPermission();
    }

    public final String getRequestMethod() {
        return this.f852b.getRequestMethod();
    }

    public final int getResponseCode() {
        m836a();
        try {
            int responseCode = this.f852b.getResponseCode();
            m838b();
            return responseCode;
        } catch (Throwable e) {
            m837a(e);
            throw e;
        }
    }

    public final String getResponseMessage() {
        m836a();
        try {
            String responseMessage = this.f852b.getResponseMessage();
            m838b();
            return responseMessage;
        } catch (Throwable e) {
            m837a(e);
            throw e;
        }
    }

    public final void setChunkedStreamingMode(int chunkLength) {
        this.f852b.setChunkedStreamingMode(chunkLength);
    }

    public final void setFixedLengthStreamingMode(int contentLength) {
        this.f852b.setFixedLengthStreamingMode(contentLength);
    }

    public final void setInstanceFollowRedirects(boolean followRedirects) {
        this.f852b.setInstanceFollowRedirects(followRedirects);
    }

    public final void setRequestMethod(String method) {
        this.f852b.setRequestMethod(method);
    }

    public final boolean usingProxy() {
        return this.f852b.usingProxy();
    }

    public final void addRequestProperty(String field, String newValue) {
        this.f852b.addRequestProperty(field, newValue);
    }

    public final void connect() {
        this.f852b.connect();
    }

    public final boolean getAllowUserInteraction() {
        return this.f852b.getAllowUserInteraction();
    }

    public final int getConnectTimeout() {
        return this.f852b.getConnectTimeout();
    }

    public final Object getContent() {
        m836a();
        try {
            Object content = this.f852b.getContent();
            m838b();
            return content;
        } catch (Throwable e) {
            m837a(e);
            throw e;
        }
    }

    public final Object getContent(Class[] types) {
        m836a();
        try {
            Object content = this.f852b.getContent(types);
            m838b();
            return content;
        } catch (Throwable e) {
            m837a(e);
            throw e;
        }
    }

    public final String getContentEncoding() {
        m836a();
        String contentEncoding = this.f852b.getContentEncoding();
        m838b();
        return contentEncoding;
    }

    public final int getContentLength() {
        return this.f852b.getContentLength();
    }

    public final String getContentType() {
        m836a();
        String contentType = this.f852b.getContentType();
        m838b();
        return contentType;
    }

    public final long getDate() {
        return this.f852b.getDate();
    }

    public final boolean getDefaultUseCaches() {
        return this.f852b.getDefaultUseCaches();
    }

    public final boolean getDoInput() {
        return this.f852b.getDoInput();
    }

    public final boolean getDoOutput() {
        return this.f852b.getDoOutput();
    }

    public final long getExpiration() {
        return this.f852b.getExpiration();
    }

    public final String getHeaderField(int pos) {
        m836a();
        String headerField = this.f852b.getHeaderField(pos);
        m838b();
        return headerField;
    }

    public final String getHeaderField(String key) {
        m836a();
        String headerField = this.f852b.getHeaderField(key);
        m838b();
        return headerField;
    }

    public final int getHeaderFieldInt(String field, int defaultValue) {
        m836a();
        int headerFieldInt = this.f852b.getHeaderFieldInt(field, defaultValue);
        m838b();
        return headerFieldInt;
    }

    public final String getHeaderFieldKey(int posn) {
        m836a();
        String headerFieldKey = this.f852b.getHeaderFieldKey(posn);
        m838b();
        return headerFieldKey;
    }

    public final Map getHeaderFields() {
        m836a();
        Map headerFields = this.f852b.getHeaderFields();
        m838b();
        return headerFields;
    }

    public final long getIfModifiedSince() {
        return this.f852b.getIfModifiedSince();
    }

    public final InputStream getInputStream() {
        m836a();
        try {
            InputStream inputStream = this.f852b.getInputStream();
            m838b();
            if (inputStream != null) {
                try {
                    return new C1191t(inputStream, this.f851a, this.f853c);
                } catch (ThreadDeath e) {
                    throw e;
                } catch (Throwable th) {
                    dx.m777a(th);
                }
            }
            return inputStream;
        } catch (Throwable th2) {
            m837a(th2);
            throw th2;
        }
    }

    public final long getLastModified() {
        return this.f852b.getLastModified();
    }

    public final OutputStream getOutputStream() {
        OutputStream outputStream = this.f852b.getOutputStream();
        if (outputStream != null) {
            try {
                return new C1192u(outputStream, this.f853c);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                dx.m777a(th);
            }
        }
        return outputStream;
    }

    public final int getReadTimeout() {
        return this.f852b.getReadTimeout();
    }

    public final Map getRequestProperties() {
        return this.f852b.getRequestProperties();
    }

    public final String getRequestProperty(String field) {
        return this.f852b.getRequestProperty(field);
    }

    public final URL getURL() {
        return this.f852b.getURL();
    }

    public final boolean getUseCaches() {
        return this.f852b.getUseCaches();
    }

    public final void setAllowUserInteraction(boolean newValue) {
        this.f852b.setAllowUserInteraction(newValue);
    }

    public final void setConnectTimeout(int timeoutMillis) {
        this.f852b.setConnectTimeout(timeoutMillis);
    }

    public final void setDefaultUseCaches(boolean newValue) {
        this.f852b.setDefaultUseCaches(newValue);
    }

    public final void setDoInput(boolean newValue) {
        this.f852b.setDoInput(newValue);
    }

    public final void setDoOutput(boolean newValue) {
        this.f852b.setDoOutput(newValue);
    }

    public final void setIfModifiedSince(long newValue) {
        this.f852b.setIfModifiedSince(newValue);
    }

    public final void setReadTimeout(int timeoutMillis) {
        this.f852b.setReadTimeout(timeoutMillis);
    }

    public final void setRequestProperty(String field, String newValue) {
        this.f852b.setRequestProperty(field, newValue);
    }

    public final void setUseCaches(boolean newValue) {
        this.f852b.setUseCaches(newValue);
    }

    public final String toString() {
        return this.f852b.toString();
    }

    public final boolean equals(Object o) {
        return this.f852b.equals(o);
    }

    public final int hashCode() {
        return this.f852b.hashCode();
    }
}
