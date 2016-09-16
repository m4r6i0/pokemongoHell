package crittercism.android;

import com.crittercism.integrations.PluginException;
import com.voxelbusters.nativeplugins.defines.Keys;
import com.voxelbusters.nativeplugins.defines.Keys.GameServices;
import com.voxelbusters.nativeplugins.defines.Keys.Twitter;
import crittercism.android.bx.C1125a;
import crittercism.android.bx.C1126b;
import crittercism.android.bx.C1127c;
import crittercism.android.bx.C1128d;
import crittercism.android.bx.C1129e;
import crittercism.android.bx.C1130f;
import crittercism.android.bx.C1132h;
import crittercism.android.bx.C1133i;
import crittercism.android.bx.C1134j;
import crittercism.android.bx.C1135k;
import crittercism.android.bx.C1136l;
import crittercism.android.bx.C1137m;
import crittercism.android.bx.C1138n;
import crittercism.android.bx.C1139o;
import crittercism.android.bx.C1140p;
import crittercism.android.bx.C1141q;
import crittercism.android.bx.C1142r;
import crittercism.android.bx.C1143s;
import crittercism.android.bx.C1144t;
import crittercism.android.bx.C1145u;
import crittercism.android.bx.C1146v;
import crittercism.android.bx.C1147w;
import crittercism.android.bx.C1148x;
import crittercism.android.bx.C1149y;
import crittercism.android.bx.C1150z;
import crittercism.android.bx.aa;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spacemadness.com.lunarconsole.BuildConfig;

public final class bk implements ch {
    public long f473a;
    public JSONArray f474b;
    public String f475c;
    public String f476d;
    public JSONArray f477e;
    public String f478f;
    public JSONObject f479g;
    private JSONObject f480h;
    private JSONArray f481i;
    private JSONArray f482j;
    private String f483k;
    private JSONArray f484l;
    private String f485m;
    private int f486n;
    private boolean f487o;
    private String f488p;

    public bk(Throwable th, long j) {
        int i = 0;
        this.f476d = BuildConfig.FLAVOR;
        this.f486n = -1;
        this.f487o = false;
        this.f487o = th instanceof PluginException;
        this.f488p = cg.f620a.m688a();
        this.f478f = "uhe";
        bu buVar = new bu();
        buVar.m582a(new C1125a()).m582a(new C1127c()).m582a(new C1126b()).m582a(new C1128d()).m582a(new C1129e()).m582a(new C1130f()).m582a(new C1139o()).m582a(new C1140p()).m582a(new C1133i()).m582a(new C1134j()).m582a(new C1132h()).m582a(new C1150z()).m582a(new aa()).m582a(new C1135k()).m582a(new C1136l()).m582a(new C1138n()).m582a(new C1137m()).m582a(new C1141q()).m582a(new C1142r()).m582a(new C1143s()).m582a(new C1144t()).m582a(new C1145u()).m582a(new C1146v()).m582a(new C1147w()).m582a(new C1148x()).m582a(new C1149y());
        this.f479g = buVar.m583a();
        this.f480h = new JSONObject();
        this.f473a = j;
        this.f475c = m540a(th);
        if (th.getMessage() != null) {
            this.f476d = th.getMessage();
        }
        if (!this.f487o) {
            this.f486n = m542c(th);
        }
        this.f483k = "android";
        this.f485m = ed.f788a.m796a();
        this.f484l = new JSONArray();
        String[] b = m541b(th);
        int length = b.length;
        while (i < length) {
            this.f484l.put(b[i]);
            i++;
        }
    }

    public final void m546a(String str, bs bsVar) {
        try {
            this.f480h.put(str, new bo(bsVar).f503a);
        } catch (JSONException e) {
        }
    }

    public final void m544a(bs bsVar) {
        this.f481i = new bo(bsVar).f503a;
    }

    public final void m547a(List list) {
        this.f482j = new JSONArray();
        for (bg j : list) {
            try {
                this.f482j.put(j.m523j());
            } catch (Throwable e) {
                dx.m777a(e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m540a(java.lang.Throwable r3) {
        /*
        r2 = this;
        r0 = r2.f487o;
        if (r0 == 0) goto L_0x000c;
    L_0x0004:
        r3 = (com.crittercism.integrations.PluginException) r3;
        r0 = r3.getExceptionName();
    L_0x000a:
        return r0;
    L_0x000b:
        r3 = r0;
    L_0x000c:
        r0 = r3.getClass();
        r1 = r0.getName();
        r0 = r3.getCause();
        if (r0 == 0) goto L_0x001c;
    L_0x001a:
        if (r0 != r3) goto L_0x000b;
    L_0x001c:
        r0 = r1;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: crittercism.android.bk.a(java.lang.Throwable):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String[] m541b(java.lang.Throwable r3) {
        /*
        r1 = new java.io.StringWriter;
        r1.<init>();
        r2 = new java.io.PrintWriter;
        r2.<init>(r1);
    L_0x000a:
        r3.printStackTrace(r2);
        r0 = r3.getCause();
        if (r0 == 0) goto L_0x0015;
    L_0x0013:
        if (r0 != r3) goto L_0x0020;
    L_0x0015:
        r0 = r1.toString();
        r1 = "\n";
        r0 = r0.split(r1);
        return r0;
    L_0x0020:
        r3 = r0;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: crittercism.android.bk.b(java.lang.Throwable):java.lang.String[]");
    }

    private static int m542c(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int i = 0;
        while (i < stackTrace.length) {
            try {
                Object obj;
                Class cls = Class.forName(stackTrace[i].getClassName());
                for (ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader(); systemClassLoader != null; systemClassLoader = systemClassLoader.getParent()) {
                    if (cls.getClassLoader() == systemClassLoader) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    return i + 1;
                }
                i++;
            } catch (ClassNotFoundException e) {
            }
        }
        return -1;
    }

    public final void m543a() {
        this.f477e = new JSONArray();
        for (Entry entry : Thread.getAllStackTraces().entrySet()) {
            Map hashMap = new HashMap();
            Thread thread = (Thread) entry.getKey();
            if (thread.getId() != this.f473a) {
                hashMap.put(Twitter.NAME, thread.getName());
                hashMap.put(TriggerIfContentAvailable.ID, Long.valueOf(thread.getId()));
                hashMap.put(GameServices.STATE, thread.getState().name());
                hashMap.put("stacktrace", new JSONArray(Arrays.asList((Object[]) entry.getValue())));
                this.f477e.put(new JSONObject(hashMap));
            }
        }
    }

    public final JSONObject m548b() {
        Map hashMap = new HashMap();
        hashMap.put("app_state", this.f479g);
        hashMap.put("breadcrumbs", this.f480h);
        hashMap.put("current_thread_id", Long.valueOf(this.f473a));
        if (this.f481i != null) {
            hashMap.put("endpoints", this.f481i);
        }
        if (this.f474b != null) {
            hashMap.put("systemBreadcrumbs", this.f474b);
        }
        if (this.f482j != null && this.f482j.length() > 0) {
            hashMap.put("transactions", this.f482j);
        }
        hashMap.put("exception_name", this.f475c);
        hashMap.put("exception_reason", this.f476d);
        hashMap.put("platform", this.f483k);
        if (this.f477e != null) {
            hashMap.put("threads", this.f477e);
        }
        hashMap.put("ts", this.f485m);
        String str = Keys.TYPE;
        Object obj = this.f478f;
        if (this.f473a != 1) {
            obj = obj + "-bg";
        }
        hashMap.put(str, obj);
        hashMap.put("unsymbolized_stacktrace", this.f484l);
        if (!this.f487o) {
            hashMap.put("suspect_line_index", Integer.valueOf(this.f486n));
        }
        return new JSONObject(hashMap);
    }

    public final void m545a(OutputStream outputStream) {
        outputStream.write(m548b().toString().getBytes());
    }

    public final String m549e() {
        return this.f488p;
    }
}
