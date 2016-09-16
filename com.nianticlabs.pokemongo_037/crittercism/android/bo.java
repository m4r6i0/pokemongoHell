package crittercism.android;

import org.json.JSONArray;

public final class bo {
    public JSONArray f503a;

    public bo(bs bsVar) {
        this.f503a = new JSONArray();
        for (bq a : bsVar.m580c()) {
            Object a2 = a.m559a();
            if (a2 != null) {
                this.f503a.put(a2);
            }
        }
    }
}
