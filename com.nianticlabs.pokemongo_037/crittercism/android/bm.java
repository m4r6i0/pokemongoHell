package crittercism.android;

import crittercism.android.bx.C1135k;
import java.io.OutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public final class bm implements ch {
    private JSONObject f496a;
    private String f497b;

    public bm(au auVar) {
        this.f497b = cg.f620a.m688a();
        try {
            this.f496a = new JSONObject().put("appID", auVar.m359a()).put("deviceID", auVar.m361c()).put("crPlatform", "android").put("crVersion", auVar.m362d()).put("deviceModel", auVar.m368j()).put("osName", "android").put("osVersion", auVar.m369k()).put("carrier", auVar.m364f()).put("mobileCountryCode", auVar.m365g()).put("mobileNetworkCode", auVar.m366h()).put("appVersion", auVar.m360b()).put("locale", new C1135k().f544a);
        } catch (JSONException e) {
        }
    }

    public final void m553a(OutputStream outputStream) {
        outputStream.write(this.f496a.toString().getBytes());
    }

    public final String m554e() {
        return this.f497b;
    }
}
