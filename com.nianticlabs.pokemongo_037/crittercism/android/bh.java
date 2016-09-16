package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import com.upsight.mediation.ads.adapters.NetworkWrapper;
import org.json.JSONException;
import org.json.JSONObject;

public final class bh {
    public boolean f450a;
    public int f451b;
    public int f452c;
    public JSONObject f453d;

    public static bh m525a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.crittercism.txn.config", 0);
        bh bhVar = new bh();
        bhVar.f450a = sharedPreferences.getBoolean("enabled", false);
        bhVar.f451b = sharedPreferences.getInt("interval", 10);
        bhVar.f452c = sharedPreferences.getInt("defaultTimeout", 3600000);
        String string = sharedPreferences.getString("transactions", null);
        bhVar.f453d = new JSONObject();
        if (string != null) {
            try {
                bhVar.f453d = new JSONObject(string);
            } catch (JSONException e) {
            }
        }
        return bhVar;
    }

    bh() {
        this.f450a = false;
        this.f451b = 10;
        this.f452c = 3600000;
        this.f453d = new JSONObject();
    }

    public bh(JSONObject jSONObject) {
        this.f450a = false;
        this.f451b = 10;
        this.f452c = 3600000;
        this.f453d = new JSONObject();
        this.f450a = jSONObject.optBoolean("enabled", false);
        this.f451b = jSONObject.optInt("interval", 10);
        this.f452c = jSONObject.optInt("defaultTimeout", 3600000);
        this.f453d = jSONObject.optJSONObject("transactions");
        if (this.f453d == null) {
            this.f453d = new JSONObject();
        }
    }

    public final long m526a(String str) {
        JSONObject optJSONObject = this.f453d.optJSONObject(str);
        if (optJSONObject != null) {
            return optJSONObject.optLong(NetworkWrapper.TIMEOUT, (long) this.f452c);
        }
        return (long) this.f452c;
    }
}
