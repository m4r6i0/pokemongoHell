package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bl extends ci {
    private String f493a;
    private String f494b;
    private C1122a f495c;

    /* renamed from: crittercism.android.bl.a */
    public enum C1122a {
        FOREGROUND("foregrounded"),
        BACKGROUND("backgrounded");
        
        private String f492c;

        private C1122a(String str) {
            this.f492c = str;
        }

        public final String m550a() {
            return this.f492c;
        }
    }

    public bl(C1122a c1122a) {
        this.f493a = cg.f620a.m688a();
        this.f494b = ed.f788a.m796a();
        this.f495c = c1122a;
    }

    public final String m552e() {
        return this.f493a;
    }

    public final JSONArray m551a() {
        Map hashMap = new HashMap();
        hashMap.put(SendEvent.EVENT, this.f495c.m550a());
        return new JSONArray().put(this.f494b).put(3).put(new JSONObject(hashMap));
    }
}
