package crittercism.android;

import android.content.Context;
import com.upsight.mediation.vast.VASTPlayer;
import org.json.JSONObject;

public class da implements cy {
    private bs f684a;
    private bs f685b;

    /* renamed from: crittercism.android.da.a */
    public static class C1162a implements cz {
        public final /* synthetic */ cy m719a(bs bsVar, bs bsVar2, String str, Context context, au auVar) {
            return new da(bsVar, bsVar2);
        }
    }

    public da(bs bsVar, bs bsVar2) {
        this.f684a = bsVar;
        this.f685b = bsVar2;
    }

    public void m708a(boolean z, int i, JSONObject jSONObject) {
        Object obj = (z || (i >= 200 && i < VASTPlayer.ERROR_GENERAL_WRAPPER)) ? 1 : null;
        if (obj != null) {
            this.f684a.m574a();
        } else {
            this.f684a.m575a(this.f685b);
        }
    }
}
