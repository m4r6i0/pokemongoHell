package crittercism.android;

import android.content.Context;
import android.os.ConditionVariable;
import crittercism.android.bg.C1120a;
import crittercism.android.bx.C1135k;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bi extends di implements bt {
    private long f454a;
    private volatile long f455b;
    private ConditionVariable f456c;
    private ConditionVariable f457d;
    private au f458e;
    private bs f459f;
    private bs f460g;
    private bs f461h;
    private bs f462i;
    private URL f463j;
    private Context f464k;
    private volatile boolean f465l;

    public bi(Context context, au auVar, bs bsVar, bs bsVar2, bs bsVar3, bs bsVar4, URL url) {
        this.f454a = System.currentTimeMillis();
        this.f455b = 10000;
        this.f456c = new ConditionVariable(false);
        this.f457d = new ConditionVariable(false);
        this.f465l = false;
        this.f464k = context;
        this.f459f = bsVar;
        this.f460g = bsVar2;
        this.f461h = bsVar3;
        this.f462i = bsVar4;
        this.f458e = auVar;
        this.f463j = url;
        bs bsVar5 = this.f459f;
        if (this != null) {
            synchronized (bsVar5.f526c) {
                bsVar5.f526c.add(this);
            }
        }
    }

    public final void m531a() {
        while (!this.f465l) {
            this.f456c.block();
            this.f457d.block();
            if (!this.f465l) {
                long currentTimeMillis = this.f455b - (System.currentTimeMillis() - this.f454a);
                if (currentTimeMillis > 0) {
                    try {
                        Thread.sleep(currentTimeMillis);
                    } catch (InterruptedException e) {
                    }
                }
                this.f454a = System.currentTimeMillis();
                bs a = this.f459f.m573a(this.f464k);
                this.f459f.m575a(a);
                JSONArray jSONArray = new bo(a).f503a;
                eb.m790a(a.f524a);
                if (jSONArray.length() > 0 && m529a(jSONArray) != null) {
                    JSONObject a2 = m529a(jSONArray);
                    try {
                        HttpURLConnection a3 = new dc(this.f463j).m721a();
                        OutputStream outputStream = a3.getOutputStream();
                        outputStream.write(a2.toString().getBytes("UTF8"));
                        outputStream.close();
                        a3.getResponseCode();
                        a3.disconnect();
                    } catch (IOException e2) {
                        new StringBuilder("Request failed for ").append(this.f463j);
                        dx.m773a();
                    } catch (Throwable e3) {
                        new StringBuilder("Request failed for ").append(this.f463j);
                        dx.m773a();
                        dx.m777a(e3);
                    }
                }
            } else {
                return;
            }
        }
    }

    public final void m533b() {
        this.f456c.open();
    }

    public final void m534c() {
        bs bsVar = this.f459f;
        this.f457d.open();
    }

    public final void m535d() {
        bs bsVar = this.f459f;
        this.f457d.close();
    }

    public final void m532a(int i, TimeUnit timeUnit) {
        this.f455b = timeUnit.toMillis((long) i);
    }

    private JSONObject m529a(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("appID", this.f458e.m359a());
            jSONObject2.put("deviceID", this.f458e.m361c());
            jSONObject2.put("crPlatform", "android");
            jSONObject2.put("crVersion", this.f458e.m362d());
            jSONObject2.put("deviceModel", this.f458e.m368j());
            jSONObject2.put("osName", "android");
            jSONObject2.put("osVersion", this.f458e.m369k());
            jSONObject2.put("carrier", this.f458e.m364f());
            jSONObject2.put("mobileCountryCode", this.f458e.m365g());
            jSONObject2.put("mobileNetworkCode", this.f458e.m366h());
            jSONObject2.put("appVersion", this.f458e.m360b());
            jSONObject2.put("locale", new C1135k().f544a);
            jSONObject.put("appState", jSONObject2);
            jSONObject.put("transactions", jSONArray);
            if (!m530b(jSONArray)) {
                return jSONObject;
            }
            jSONObject.put("breadcrumbs", new bo(this.f460g).f503a);
            jSONObject.put("endpoints", new bo(this.f461h).f503a);
            jSONObject.put("systemBreadcrumbs", new bo(this.f462i).f503a);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    private static boolean m530b(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray optJSONArray = jSONArray.optJSONArray(i);
            if (optJSONArray != null) {
                try {
                    C1120a k = new bg(optJSONArray).m524k();
                    if (!(k == C1120a.SUCCESS || k == C1120a.INTERRUPTED || k == C1120a.ABORTED)) {
                        return true;
                    }
                } catch (Throwable e) {
                    dx.m777a(e);
                } catch (Throwable e2) {
                    dx.m777a(e2);
                }
            }
        }
        return false;
    }
}
