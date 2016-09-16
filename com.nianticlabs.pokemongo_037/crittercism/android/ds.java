package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;

public final class ds {
    private boolean f750a;
    private boolean f751b;

    /* renamed from: crittercism.android.ds.a */
    public static class C1168a {
        public static ds m756a(ax axVar) {
            JSONObject jSONObject;
            boolean optBoolean;
            JSONObject jSONObject2 = null;
            String a = axVar.m385a(cq.OPT_OUT_STATUS_SETTING.m693a(), cq.OPT_OUT_STATUS_SETTING.m694b());
            if (a != null) {
                try {
                    jSONObject = new JSONObject(a);
                } catch (JSONException e) {
                    dx.m778b();
                }
            } else {
                jSONObject = null;
            }
            jSONObject2 = jSONObject;
            if (jSONObject2 != null) {
                optBoolean = jSONObject2.optBoolean("optOutStatusSet", false);
            } else {
                optBoolean = false;
            }
            if (optBoolean) {
                optBoolean = jSONObject2.optBoolean("optOutStatus", false);
            } else {
                optBoolean = axVar.m389c(cq.OLD_OPT_OUT_STATUS_SETTING.m693a(), cq.OLD_OPT_OUT_STATUS_SETTING.m694b());
            }
            return new ds(optBoolean);
        }
    }

    public ds(boolean z) {
        this.f750a = z;
        this.f751b = true;
    }

    public final synchronized boolean m757a() {
        return this.f750a;
    }
}
