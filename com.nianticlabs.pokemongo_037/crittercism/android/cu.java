package crittercism.android;

import com.crittercism.app.CrittercismConfig;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cu implements cw {
    public Map f692a;

    /* renamed from: crittercism.android.cu.a */
    public static class C1160a implements cx {
        public final /* synthetic */ cw m710a(au auVar) {
            return new cu(auVar);
        }
    }

    public final /* synthetic */ cw m713a(bs bsVar) {
        String str = bsVar.f525b;
        this.f692a.put(bsVar.f525b, new bo(bsVar).f503a);
        return this;
    }

    public cu(au auVar) {
        this.f692a = new HashMap();
        this.f692a.put("app_id", auVar.m359a());
        this.f692a.put("hashed_device_id", auVar.m361c());
        this.f692a.put("library_version", CrittercismConfig.API_VERSION);
    }

    public final cu m711a(String str, String str2) {
        this.f692a.put(str, str2);
        return this;
    }

    public final cu m712a(String str, JSONArray jSONArray) {
        this.f692a.put(str, jSONArray);
        return this;
    }

    public final void m714a(OutputStream outputStream) {
        dx.m778b();
        outputStream.write(new JSONObject(this.f692a).toString().getBytes("UTF8"));
    }

    public final String toString() {
        String str = null;
        try {
            str = new JSONObject(this.f692a).toString(4);
        } catch (JSONException e) {
            dx.m773a();
        }
        return str;
    }
}
