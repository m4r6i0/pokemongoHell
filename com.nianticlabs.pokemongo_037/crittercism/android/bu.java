package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class bu implements bv {
    private Map f533a;

    public final /* synthetic */ Object m584b() {
        return m583a();
    }

    public bu() {
        this.f533a = new HashMap();
    }

    public final bu m582a(bw bwVar) {
        if (bwVar.m581b() != null) {
            this.f533a.put(bwVar.m585a(), bwVar.m581b());
        }
        return this;
    }

    public final JSONObject m583a() {
        return new JSONObject(this.f533a);
    }
}
