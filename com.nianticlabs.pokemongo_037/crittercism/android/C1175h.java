package crittercism.android;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: crittercism.android.h */
public final class C1175h {
    public boolean f801a;
    public boolean f802b;
    public boolean f803c;
    public int f804d;

    public C1175h(Context context) {
        this.f801a = false;
        this.f802b = false;
        this.f803c = false;
        this.f804d = 10;
        if (C1175h.m805a(context).exists()) {
            this.f803c = true;
        }
    }

    public C1175h(JSONObject jSONObject) {
        this.f801a = false;
        this.f802b = false;
        this.f803c = false;
        this.f804d = 10;
        if (jSONObject.has("net")) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("net");
                this.f801a = jSONObject2.optBoolean("enabled", false);
                this.f802b = jSONObject2.optBoolean("persist", false);
                this.f803c = jSONObject2.optBoolean("kill", false);
                this.f804d = jSONObject2.optInt("interval", 10);
            } catch (JSONException e) {
            }
        }
    }

    public static File m805a(Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + "/.crittercism.apm.disabled.");
    }

    public static void m806b(Context context) {
        try {
            C1175h.m805a(context).createNewFile();
        } catch (IOException e) {
            dx.m779b("Unable to kill APM: " + e.getMessage());
        }
    }

    public final int hashCode() {
        int i;
        int i2 = 1231;
        int i3 = ((this.f803c ? 1231 : 1237) + 31) * 31;
        if (this.f801a) {
            i = 1231;
        } else {
            i = 1237;
        }
        i = (i + i3) * 31;
        if (!this.f802b) {
            i2 = 1237;
        }
        return ((i + i2) * 31) + this.f804d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof C1175h)) {
            return false;
        }
        C1175h c1175h = (C1175h) obj;
        if (this.f803c != c1175h.f803c) {
            return false;
        }
        if (this.f801a != c1175h.f801a) {
            return false;
        }
        if (this.f802b != c1175h.f802b) {
            return false;
        }
        if (this.f804d != c1175h.f804d) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return "OptmzConfiguration [\nisSendTaskEnabled=" + this.f801a + "\n, shouldPersist=" + this.f802b + "\n, isKilled=" + this.f803c + "\n, statisticsSendInterval=" + this.f804d + "]";
    }
}
