package crittercism.android;

import android.os.ConditionVariable;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* renamed from: crittercism.android.g */
public final class C1174g implements C1110f, Runnable {
    private List f791a;
    private URL f792b;
    private long f793c;
    private ConditionVariable f794d;
    private au f795e;
    private ConditionVariable f796f;
    private volatile boolean f797g;
    private final Object f798h;
    private int f799i;
    private volatile long f800j;

    public C1174g(au auVar, URL url) {
        this(auVar, url, (byte) 0);
    }

    private C1174g(au auVar, URL url, byte b) {
        this.f791a = new LinkedList();
        this.f792b = null;
        this.f793c = System.currentTimeMillis();
        this.f794d = new ConditionVariable(false);
        this.f796f = new ConditionVariable(false);
        this.f797g = false;
        this.f798h = new Object();
        this.f799i = 50;
        this.f800j = 10000;
        this.f795e = auVar;
        this.f792b = url;
        this.f799i = 50;
        this.f800j = 10000;
    }

    public final void run() {
        while (!this.f797g) {
            try {
                this.f796f.block();
                this.f794d.block();
                if (!this.f797g) {
                    try {
                        if (m799b() > 0) {
                            Thread.sleep(m799b());
                        }
                    } catch (InterruptedException e) {
                    }
                    this.f793c = System.currentTimeMillis();
                    HttpURLConnection c = m800c();
                    if (c == null) {
                        this.f797g = true;
                        dx.m779b("Disabling APM due to failure instantiating connection");
                        return;
                    }
                    List list;
                    synchronized (this.f798h) {
                        list = this.f791a;
                        this.f791a = new LinkedList();
                        this.f794d.close();
                    }
                    C1097a a = C1097a.m252a(this.f795e, list);
                    if (a == null) {
                        this.f797g = true;
                        dx.m779b("Disabling APM due to failure building request");
                        return;
                    }
                    C1174g.m798a(c, a.f272a);
                } else {
                    return;
                }
            } catch (Exception e2) {
                Log.e("Crittercism", "Exited APM send task due to: \n" + e2);
                return;
            }
        }
    }

    public final void m802a() {
        this.f796f.open();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long m799b() {
        /*
        r8 = this;
        r0 = 0;
        r2 = r8.f800j;
        r4 = java.lang.System.currentTimeMillis();
        r6 = r8.f793c;
        r4 = r4 - r6;
        r6 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r6 <= 0) goto L_0x0017;
    L_0x000f:
        r2 = r2 - r4;
        r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r4 >= 0) goto L_0x0017;
    L_0x0014:
        r2 = r8.f800j;
        return r0;
    L_0x0017:
        r0 = r2;
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: crittercism.android.g.b():long");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.HttpURLConnection m800c() {
        /*
        r8 = this;
        r4 = 0;
        r1 = r8.f792b;	 Catch:{ IOException -> 0x004d, GeneralSecurityException -> 0x0067 }
        r1 = r1.openConnection();	 Catch:{ IOException -> 0x004d, GeneralSecurityException -> 0x0067 }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ IOException -> 0x004d, GeneralSecurityException -> 0x0067 }
        r2 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r1.setConnectTimeout(r2);	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r2 = "User-Agent";
        r3 = "5.0.8";
        r1.setRequestProperty(r2, r3);	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r2 = "Content-Type";
        r3 = "application/json";
        r1.setRequestProperty(r2, r3);	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r2 = 1;
        r1.setDoOutput(r2);	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r2 = "POST";
        r1.setRequestMethod(r2);	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r2 = r1 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        if (r2 == 0) goto L_0x004c;
    L_0x0029:
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r2 = r0;
        r3 = "TLS";
        r3 = javax.net.ssl.SSLContext.getInstance(r3);	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r3.init(r5, r6, r7);	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r3 = r3.getSocketFactory();	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        if (r3 == 0) goto L_0x004c;
    L_0x003f:
        r5 = r3 instanceof crittercism.android.ab;	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        if (r5 == 0) goto L_0x0049;
    L_0x0043:
        r3 = (crittercism.android.ab) r3;	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
        r3 = r3.m261a();	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
    L_0x0049:
        r2.setSSLSocketFactory(r3);	 Catch:{ IOException -> 0x0080, GeneralSecurityException -> 0x0067 }
    L_0x004c:
        return r1;
    L_0x004d:
        r1 = move-exception;
        r2 = r1;
        r1 = r4;
    L_0x0050:
        r3 = new java.lang.StringBuilder;
        r4 = "Failed to instantiate URLConnection to APM server: ";
        r3.<init>(r4);
        r2 = r2.getMessage();
        r2 = r3.append(r2);
        r2 = r2.toString();
        crittercism.android.dx.m779b(r2);
        goto L_0x004c;
    L_0x0067:
        r1 = move-exception;
        r2 = new java.lang.StringBuilder;
        r3 = "Failed to instantiate URLConnection to APM server: ";
        r2.<init>(r3);
        r1 = r1.getMessage();
        r1 = r2.append(r1);
        r1 = r1.toString();
        crittercism.android.dx.m779b(r1);
        r1 = r4;
        goto L_0x004c;
    L_0x0080:
        r2 = move-exception;
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: crittercism.android.g.c():java.net.HttpURLConnection");
    }

    private static boolean m798a(HttpURLConnection httpURLConnection, JSONObject jSONObject) {
        try {
            httpURLConnection.getOutputStream().write(jSONObject.toString().getBytes("UTF8"));
            int responseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            if (responseCode == 202) {
                return true;
            }
            return false;
        } catch (IOException e) {
            new StringBuilder("Request failed for ").append(httpURLConnection.getURL().toExternalForm());
            dx.m773a();
            return false;
        } catch (Exception e2) {
            new StringBuilder("Request failed for ").append(httpURLConnection.getURL().toExternalForm());
            dx.m773a();
            return false;
        }
    }

    private boolean m801d() {
        return !this.f797g && this.f791a.size() < this.f799i;
    }

    public final void m804a(C1153c c1153c) {
        Object obj = null;
        if (m801d()) {
            synchronized (this.f798h) {
                if (m801d()) {
                    this.f791a.add(c1153c);
                    if (!c1153c.m656a().contains(this.f792b.getHost())) {
                        String str = c1153c.f581f;
                        if (str == null || !str.toLowerCase().equals("connect")) {
                            obj = 1;
                        }
                    }
                    if (obj != null) {
                        this.f794d.open();
                    }
                    return;
                }
            }
        }
    }

    public final void m803a(int i, TimeUnit timeUnit) {
        this.f800j = timeUnit.toMillis((long) i);
    }
}
