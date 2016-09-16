package crittercism.android;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cs implements cw {
    private Map f683a;

    /* renamed from: crittercism.android.cs.a */
    static class C1157a {
        boolean f681a;
        int f682b;

        public C1157a() {
            this((byte) 0);
        }

        private C1157a(byte b) {
            this.f681a = false;
            this.f682b = 0;
            this.f681a = false;
            this.f682b = 0;
        }
    }

    /* renamed from: crittercism.android.cs.b */
    public static class C1158b implements cx {
        public final /* synthetic */ cw m699a(au auVar) {
            return new cs();
        }
    }

    public final /* synthetic */ cw m703a(bs bsVar) {
        Object obj = null;
        for (bq bqVar : bsVar.m580c()) {
            Object obj2;
            Object obj3;
            if (bqVar instanceof ca) {
                JSONObject jSONObject = (JSONObject) bqVar.m559a();
                if (jSONObject == null) {
                    obj2 = null;
                } else {
                    Map hashMap = new HashMap(jSONObject.length());
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        hashMap.put(str, jSONObject.opt(str));
                    }
                    Map map = hashMap;
                }
            } else {
                obj2 = null;
            }
            if (obj2 != null) {
                C1157a c1157a = (C1157a) this.f683a.get(obj2);
                if (c1157a == null) {
                    c1157a = new C1157a();
                    this.f683a.put(obj2, c1157a);
                }
                r0.f682b++;
                obj3 = obj2;
            } else {
                obj3 = obj;
            }
            obj = obj3;
        }
        if (obj != null) {
            ((C1157a) this.f683a.get(obj)).f681a = true;
        }
        return this;
    }

    public cs() {
        this.f683a = new HashMap();
    }

    private JSONArray m702a() {
        JSONArray jSONArray = new JSONArray();
        for (Entry entry : this.f683a.entrySet()) {
            JSONObject jSONObject = new JSONObject((Map) entry.getKey());
            C1157a c1157a = (C1157a) entry.getValue();
            try {
                jSONArray.put(new JSONObject().put("appLoads", jSONObject).put("count", c1157a.f682b).put("current", c1157a.f681a));
            } catch (JSONException e) {
            }
        }
        return jSONArray;
    }

    public final void m704a(OutputStream outputStream) {
        outputStream.write(m702a().toString().getBytes("UTF8"));
    }

    public final String toString() {
        String str = null;
        try {
            str = m702a().toString(4);
        } catch (JSONException e) {
            dx.m773a();
        }
        return str;
    }
}
