package crittercism.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Permission;
import java.util.Map;

/* renamed from: crittercism.android.r */
public final class C1189r extends HttpURLConnection {
    private C1171e f845a;
    private HttpURLConnection f846b;
    private C1153c f847c;
    private C1161d f848d;
    private boolean f849e;
    private boolean f850f;

    public C1189r(HttpURLConnection httpURLConnection, C1171e c1171e, C1161d c1161d) {
        super(httpURLConnection.getURL());
        this.f849e = false;
        this.f850f = false;
        this.f846b = httpURLConnection;
        this.f845a = c1171e;
        this.f848d = c1161d;
        this.f847c = new C1153c(httpURLConnection.getURL());
    }

    private void m833a() {
        try {
            if (!this.f850f) {
                this.f850f = true;
                this.f847c.f581f = this.f846b.getRequestMethod();
                this.f847c.m665b();
                this.f847c.f585j = this.f848d.m717a();
                if (bc.m475b()) {
                    this.f847c.m659a(bc.m473a());
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private void m834a(Throwable th) {
        try {
            if (!this.f849e) {
                this.f849e = true;
                this.f847c.m668c();
                this.f847c.m663a(th);
                this.f845a.m785a(this.f847c);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th2) {
            dx.m777a(th);
        }
    }

    private void m835b() {
        Object obj = null;
        try {
            if (!this.f849e) {
                this.f849e = true;
                this.f847c.m668c();
                if (this.f846b.getHeaderFields() != null) {
                    C1187p c1187p = new C1187p(this.f846b.getHeaderFields());
                    int b = c1187p.m830b("Content-Length");
                    if (b != -1) {
                        this.f847c.m666b((long) b);
                        obj = 1;
                    }
                    long a = c1187p.m829a("X-Android-Sent-Millis");
                    long a2 = c1187p.m829a("X-Android-Received-Millis");
                    if (!(a == Long.MAX_VALUE || a2 == Long.MAX_VALUE)) {
                        this.f847c.m673e(a);
                        this.f847c.m675f(a2);
                    }
                }
                try {
                    this.f847c.f580e = this.f846b.getResponseCode();
                } catch (IOException e) {
                }
                if (obj != null) {
                    this.f845a.m785a(this.f847c);
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final void connect() {
        this.f846b.connect();
    }

    public final boolean getAllowUserInteraction() {
        return this.f846b.getAllowUserInteraction();
    }

    public final Object getContent() {
        m833a();
        try {
            Object content = this.f846b.getContent();
            m835b();
            return content;
        } catch (Throwable e) {
            m834a(e);
            throw e;
        }
    }

    public final Object getContent(Class[] types) {
        m833a();
        try {
            Object content = this.f846b.getContent(types);
            m835b();
            return content;
        } catch (Throwable e) {
            m834a(e);
            throw e;
        }
    }

    public final String getContentEncoding() {
        m833a();
        String contentEncoding = this.f846b.getContentEncoding();
        m835b();
        return contentEncoding;
    }

    public final int getContentLength() {
        return this.f846b.getContentLength();
    }

    public final String getContentType() {
        m833a();
        String contentType = this.f846b.getContentType();
        m835b();
        return contentType;
    }

    public final long getDate() {
        return this.f846b.getDate();
    }

    public final boolean getDefaultUseCaches() {
        return this.f846b.getDefaultUseCaches();
    }

    public final boolean getDoInput() {
        return this.f846b.getDoInput();
    }

    public final boolean getDoOutput() {
        return this.f846b.getDoOutput();
    }

    public final long getExpiration() {
        return this.f846b.getExpiration();
    }

    public final String getHeaderField(int pos) {
        m833a();
        String headerField = this.f846b.getHeaderField(pos);
        m835b();
        return headerField;
    }

    public final Map getHeaderFields() {
        m833a();
        Map headerFields = this.f846b.getHeaderFields();
        m835b();
        return headerFields;
    }

    public final Map getRequestProperties() {
        return this.f846b.getRequestProperties();
    }

    public final void addRequestProperty(String field, String newValue) {
        this.f846b.addRequestProperty(field, newValue);
    }

    public final String getHeaderField(String key) {
        m833a();
        String headerField = this.f846b.getHeaderField(key);
        m835b();
        return headerField;
    }

    public final long getHeaderFieldDate(String field, long defaultValue) {
        m833a();
        long headerFieldDate = this.f846b.getHeaderFieldDate(field, defaultValue);
        m835b();
        return headerFieldDate;
    }

    public final int getHeaderFieldInt(String field, int defaultValue) {
        m833a();
        int headerFieldInt = this.f846b.getHeaderFieldInt(field, defaultValue);
        m835b();
        return headerFieldInt;
    }

    public final String getHeaderFieldKey(int posn) {
        m833a();
        String headerFieldKey = this.f846b.getHeaderFieldKey(posn);
        m835b();
        return headerFieldKey;
    }

    public final long getIfModifiedSince() {
        return this.f846b.getIfModifiedSince();
    }

    public final InputStream getInputStream() {
        m833a();
        try {
            InputStream inputStream = this.f846b.getInputStream();
            m835b();
            if (inputStream != null) {
                try {
                    return new C1191t(inputStream, this.f845a, this.f847c);
                } catch (ThreadDeath e) {
                    throw e;
                } catch (Throwable th) {
                    dx.m777a(th);
                }
            }
            return inputStream;
        } catch (Throwable th2) {
            m834a(th2);
            throw th2;
        }
    }

    public final long getLastModified() {
        return this.f846b.getLastModified();
    }

    public final OutputStream getOutputStream() {
        OutputStream outputStream = this.f846b.getOutputStream();
        if (outputStream != null) {
            try {
                return new C1192u(outputStream, this.f847c);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                dx.m777a(th);
            }
        }
        return outputStream;
    }

    public final Permission getPermission() {
        return this.f846b.getPermission();
    }

    public final String getRequestProperty(String field) {
        return this.f846b.getRequestProperty(field);
    }

    public final URL getURL() {
        return this.f846b.getURL();
    }

    public final boolean getUseCaches() {
        return this.f846b.getUseCaches();
    }

    public final void setAllowUserInteraction(boolean newValue) {
        this.f846b.setAllowUserInteraction(newValue);
    }

    public final void setDefaultUseCaches(boolean newValue) {
        this.f846b.setDefaultUseCaches(newValue);
    }

    public final void setDoInput(boolean newValue) {
        this.f846b.setDoInput(newValue);
    }

    public final void setDoOutput(boolean newValue) {
        this.f846b.setDoOutput(newValue);
    }

    public final void setIfModifiedSince(long newValue) {
        this.f846b.setIfModifiedSince(newValue);
    }

    public final void setRequestProperty(String field, String newValue) {
        this.f846b.setRequestProperty(field, newValue);
    }

    public final void setUseCaches(boolean newValue) {
        this.f846b.setUseCaches(newValue);
    }

    public final void setConnectTimeout(int timeoutMillis) {
        this.f846b.setConnectTimeout(timeoutMillis);
    }

    public final int getConnectTimeout() {
        return this.f846b.getConnectTimeout();
    }

    public final void setReadTimeout(int timeoutMillis) {
        this.f846b.setReadTimeout(timeoutMillis);
    }

    public final int getReadTimeout() {
        return this.f846b.getReadTimeout();
    }

    public final String toString() {
        return this.f846b.toString();
    }

    public final void disconnect() {
        this.f846b.disconnect();
        try {
            if (this.f849e && !this.f847c.f577b) {
                this.f845a.m785a(this.f847c);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final boolean usingProxy() {
        return this.f846b.usingProxy();
    }

    public final InputStream getErrorStream() {
        m833a();
        InputStream errorStream = this.f846b.getErrorStream();
        m835b();
        if (errorStream != null) {
            try {
                return new C1191t(errorStream, this.f845a, this.f847c);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                dx.m777a(th);
            }
        }
        return errorStream;
    }

    public final String getRequestMethod() {
        return this.f846b.getRequestMethod();
    }

    public final int getResponseCode() {
        m833a();
        try {
            int responseCode = this.f846b.getResponseCode();
            m835b();
            return responseCode;
        } catch (Throwable e) {
            m834a(e);
            throw e;
        }
    }

    public final String getResponseMessage() {
        m833a();
        try {
            String responseMessage = this.f846b.getResponseMessage();
            m835b();
            return responseMessage;
        } catch (Throwable e) {
            m834a(e);
            throw e;
        }
    }

    public final void setRequestMethod(String method) {
        this.f846b.setRequestMethod(method);
    }

    public final boolean getInstanceFollowRedirects() {
        return this.f846b.getInstanceFollowRedirects();
    }

    public final void setInstanceFollowRedirects(boolean followRedirects) {
        this.f846b.setInstanceFollowRedirects(followRedirects);
    }

    public final void setFixedLengthStreamingMode(int contentLength) {
        this.f846b.setFixedLengthStreamingMode(contentLength);
    }

    public final void setChunkedStreamingMode(int chunkLength) {
        this.f846b.setChunkedStreamingMode(chunkLength);
    }

    public final boolean equals(Object o) {
        return this.f846b.equals(o);
    }

    public final int hashCode() {
        return this.f846b.hashCode();
    }
}
