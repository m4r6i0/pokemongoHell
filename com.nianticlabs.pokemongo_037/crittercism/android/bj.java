package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bj extends ci {
    private String f469a;
    private String f470b;
    private C1121a f471c;
    private String f472d;

    /* renamed from: crittercism.android.bj.a */
    public enum C1121a {
        ACTIVATED,
        DEACTIVATED
    }

    public bj(C1121a c1121a, String str) {
        this.f469a = cg.f620a.m688a();
        this.f470b = ed.f788a.m796a();
        this.f471c = c1121a;
        this.f472d = str;
    }

    public final String m539e() {
        return this.f469a;
    }

    public final JSONArray m538a() {
        Map hashMap = new HashMap();
        hashMap.put(SendEvent.EVENT, Integer.valueOf(this.f471c.ordinal()));
        hashMap.put("viewName", this.f472d);
        return new JSONArray().put(this.f470b).put(5).put(new JSONObject(hashMap));
    }
}
