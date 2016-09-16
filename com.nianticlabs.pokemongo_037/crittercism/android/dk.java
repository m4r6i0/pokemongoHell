package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import org.json.JSONException;
import org.json.JSONObject;

public final class dk extends di {
    private ax f733a;
    private final boolean f734b;
    private Context f735c;

    public dk(Context context, ax axVar, boolean z) {
        this.f733a = axVar;
        this.f734b = z;
        this.f735c = context;
    }

    public final void m737a() {
        new StringBuilder("Setting opt out status to ").append(this.f734b).append(".  This will take effect in the next user session.");
        dx.m778b();
        boolean z = this.f734b;
        ax axVar = this.f733a;
        String a = cq.OPT_OUT_STATUS_SETTING.m693a();
        String b = cq.OPT_OUT_STATUS_SETTING.m694b();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("optOutStatus", z).put("optOutStatusSet", true);
        } catch (JSONException e) {
        }
        axVar.m387a(a, b, jSONObject.toString());
        if (this.f734b) {
            Editor edit = this.f735c.getSharedPreferences("com.crittercism.optmz.config", 0).edit();
            edit.clear();
            edit.commit();
            C1175h.m806b(this.f735c);
        }
    }
}
