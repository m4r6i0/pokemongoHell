package crittercism.android;

import android.location.Location;
import crittercism.android.C1181k.C1180a;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import spacemadness.com.lunarconsole.BuildConfig;

/* renamed from: crittercism.android.c */
public final class C1153c extends bp {
    public long f576a;
    public boolean f577b;
    C1152a f578c;
    public long f579d;
    public int f580e;
    public String f581f;
    public cn f582g;
    public C1181k f583h;
    public String f584i;
    public C1111b f585j;
    private long f586k;
    private boolean f587l;
    private boolean f588m;
    private String f589n;
    private long f590o;
    private boolean f591p;
    private boolean f592q;
    private double[] f593r;

    /* renamed from: crittercism.android.c.a */
    public enum C1152a {
        NOT_LOGGED_YET("Not logged"),
        INPUT_STREAM_READ("InputStream.read()"),
        INPUT_STREAM_CLOSE("InputStream.close()"),
        SOCKET_CLOSE("Socket.close()"),
        LEGACY_JAVANET("Legacy java.net"),
        HTTP_CONTENT_LENGTH_PARSER("parse()"),
        INPUT_STREAM_FINISHED("finishedMessage()"),
        PARSING_INPUT_STREAM_LOG_ERROR("logError()"),
        SOCKET_IMPL_CONNECT("MonitoredSocketImpl.connect()"),
        SSL_SOCKET_START_HANDSHAKE("MonitoredSSLSocket.startHandshake"),
        UNIT_TEST("Unit test"),
        LOG_ENDPOINT("logEndpoint");
        
        private String f575m;

        private C1152a(String str) {
            this.f575m = str;
        }

        public final String toString() {
            return this.f575m;
        }
    }

    public C1153c() {
        this.f576a = Long.MAX_VALUE;
        this.f586k = Long.MAX_VALUE;
        this.f587l = false;
        this.f588m = false;
        this.f577b = false;
        this.f578c = C1152a.NOT_LOGGED_YET;
        this.f590o = 0;
        this.f579d = 0;
        this.f591p = false;
        this.f592q = false;
        this.f580e = 0;
        this.f581f = BuildConfig.FLAVOR;
        this.f582g = new cn(null);
        this.f583h = new C1181k();
        this.f585j = C1111b.MOBILE;
        this.f589n = cg.f620a.m688a();
    }

    public C1153c(String str) {
        this.f576a = Long.MAX_VALUE;
        this.f586k = Long.MAX_VALUE;
        this.f587l = false;
        this.f588m = false;
        this.f577b = false;
        this.f578c = C1152a.NOT_LOGGED_YET;
        this.f590o = 0;
        this.f579d = 0;
        this.f591p = false;
        this.f592q = false;
        this.f580e = 0;
        this.f581f = BuildConfig.FLAVOR;
        this.f582g = new cn(null);
        this.f583h = new C1181k();
        this.f585j = C1111b.MOBILE;
        this.f589n = cg.f620a.m688a();
        if (str != null) {
            this.f584i = str;
        }
    }

    public C1153c(URL url) {
        this.f576a = Long.MAX_VALUE;
        this.f586k = Long.MAX_VALUE;
        this.f587l = false;
        this.f588m = false;
        this.f577b = false;
        this.f578c = C1152a.NOT_LOGGED_YET;
        this.f590o = 0;
        this.f579d = 0;
        this.f591p = false;
        this.f592q = false;
        this.f580e = 0;
        this.f581f = BuildConfig.FLAVOR;
        this.f582g = new cn(null);
        this.f583h = new C1181k();
        this.f585j = C1111b.MOBILE;
        this.f589n = cg.f620a.m688a();
        if (url != null) {
            this.f584i = url.toExternalForm();
        }
    }

    public final void m658a(long j) {
        if (!this.f591p) {
            this.f590o += j;
        }
    }

    public final void m666b(long j) {
        this.f591p = true;
        this.f590o = j;
    }

    public final void m669c(long j) {
        if (!this.f592q) {
            this.f579d += j;
        }
    }

    public final void m671d(long j) {
        this.f592q = true;
        this.f579d = j;
    }

    public final String m656a() {
        boolean z = true;
        String str = this.f584i;
        if (str == null) {
            C1181k c1181k = this.f583h;
            str = c1181k.f829b != null ? c1181k.f829b : c1181k.f828a != null ? c1181k.f828a.getHostName() : "unknown-host";
            String stringBuilder;
            if (c1181k.f833f) {
                int i = c1181k.f832e;
                if (i > 0) {
                    stringBuilder = new StringBuilder(UpsightEndpoint.SIGNED_MESSAGE_SEPARATOR).append(i).toString();
                    if (!str.endsWith(stringBuilder)) {
                        str = str + stringBuilder;
                    }
                }
            } else {
                stringBuilder = c1181k.f830c;
                String str2 = BuildConfig.FLAVOR;
                if (stringBuilder == null || !(stringBuilder.regionMatches(true, 0, "http:", 0, 5) || stringBuilder.regionMatches(true, 0, "https:", 0, 6))) {
                    z = false;
                }
                if (z) {
                    str = stringBuilder;
                } else {
                    String str3 = c1181k.f831d != null ? str2 + c1181k.f831d.f826c + UpsightEndpoint.SIGNED_MESSAGE_SEPARATOR : str2;
                    if (stringBuilder.startsWith("//")) {
                        str = str3 + stringBuilder;
                    } else {
                        String str4 = str3 + "//";
                        if (stringBuilder.startsWith(str)) {
                            str = str4 + stringBuilder;
                        } else {
                            str3 = BuildConfig.FLAVOR;
                            if (c1181k.f832e > 0 && (c1181k.f831d == null || c1181k.f831d.f827d != c1181k.f832e)) {
                                String stringBuilder2 = new StringBuilder(UpsightEndpoint.SIGNED_MESSAGE_SEPARATOR).append(c1181k.f832e).toString();
                                if (!str.endsWith(stringBuilder2)) {
                                    str3 = stringBuilder2;
                                }
                            }
                            str = str4 + str + str3 + stringBuilder;
                        }
                    }
                }
            }
            this.f584i = str;
        }
        return str;
    }

    public final void m662a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.f584i = str;
    }

    private long m655g() {
        if (this.f576a == Long.MAX_VALUE || this.f586k == Long.MAX_VALUE) {
            return Long.MAX_VALUE;
        }
        return this.f586k - this.f576a;
    }

    public final void m673e(long j) {
        this.f576a = j;
        this.f587l = true;
    }

    public final void m665b() {
        if (!this.f587l && this.f576a == Long.MAX_VALUE) {
            this.f576a = System.currentTimeMillis();
        }
    }

    public final void m675f(long j) {
        this.f586k = j;
        this.f588m = true;
    }

    public final void m668c() {
        if (!this.f588m && this.f586k == Long.MAX_VALUE) {
            this.f586k = System.currentTimeMillis();
        }
    }

    public final void m659a(Location location) {
        this.f593r = new double[]{location.getLatitude(), location.getLongitude()};
    }

    public final String toString() {
        String str = (((((((((((((((BuildConfig.FLAVOR + "URI            : " + this.f584i + IOUtils.LINE_SEPARATOR_UNIX) + "URI Builder    : " + this.f583h.toString() + IOUtils.LINE_SEPARATOR_UNIX) + IOUtils.LINE_SEPARATOR_UNIX) + "Logged by      : " + this.f578c.toString() + IOUtils.LINE_SEPARATOR_UNIX) + "Error type:         : " + this.f582g.f655a + IOUtils.LINE_SEPARATOR_UNIX) + "Error code:         : " + this.f582g.f656b + IOUtils.LINE_SEPARATOR_UNIX) + IOUtils.LINE_SEPARATOR_UNIX) + "Response time  : " + m655g() + IOUtils.LINE_SEPARATOR_UNIX) + "Start time     : " + this.f576a + IOUtils.LINE_SEPARATOR_UNIX) + "End time       : " + this.f586k + IOUtils.LINE_SEPARATOR_UNIX) + IOUtils.LINE_SEPARATOR_UNIX) + "Bytes out    : " + this.f579d + IOUtils.LINE_SEPARATOR_UNIX) + "Bytes in     : " + this.f590o + IOUtils.LINE_SEPARATOR_UNIX) + IOUtils.LINE_SEPARATOR_UNIX) + "Response code  : " + this.f580e + IOUtils.LINE_SEPARATOR_UNIX) + "Request method : " + this.f581f + IOUtils.LINE_SEPARATOR_UNIX;
        if (this.f593r != null) {
            return str + "Location       : " + Arrays.toString(this.f593r) + IOUtils.LINE_SEPARATOR_UNIX;
        }
        return str;
    }

    public final JSONArray m670d() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(this.f581f);
            jSONArray.put(m656a());
            jSONArray.put(ed.f788a.m797a(new Date(this.f576a)));
            jSONArray.put(m655g());
            jSONArray.put(this.f585j.m452a());
            jSONArray.put(this.f590o);
            jSONArray.put(this.f579d);
            jSONArray.put(this.f580e);
            jSONArray.put(this.f582g.f655a);
            jSONArray.put(this.f582g.f656b);
            if (this.f593r == null) {
                return jSONArray;
            }
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(this.f593r[0]);
            jSONArray2.put(this.f593r[1]);
            jSONArray.put(jSONArray2);
            return jSONArray;
        } catch (Exception e) {
            Exception exception = e;
            System.out.println("Failed to create statsArray");
            exception.printStackTrace();
            return null;
        }
    }

    public final void m663a(Throwable th) {
        this.f582g = new cn(th);
    }

    public final void m664a(InetAddress inetAddress) {
        this.f584i = null;
        this.f583h.f828a = inetAddress;
    }

    public final void m667b(String str) {
        this.f584i = null;
        this.f583h.f829b = str;
    }

    public final void m660a(C1180a c1180a) {
        this.f583h.f831d = c1180a;
    }

    public final void m657a(int i) {
        C1181k c1181k = this.f583h;
        if (i > 0) {
            c1181k.f832e = i;
        }
    }

    public final void m661a(OutputStream outputStream) {
        outputStream.write(m670d().toString().getBytes());
    }

    public final String m672e() {
        return this.f589n;
    }

    public final void m674f() {
        this.f583h.f833f = true;
    }
}
