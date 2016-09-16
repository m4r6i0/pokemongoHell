package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;
import java.util.UUID;

public final class dr {
    private SharedPreferences f747a;
    private SharedPreferences f748b;
    private Context f749c;

    public dr(Context context) {
        if (context == null) {
            throw new NullPointerException("context was null");
        }
        this.f749c = context;
        this.f747a = context.getSharedPreferences("com.crittercism.usersettings", 0);
        this.f748b = context.getSharedPreferences("com.crittercism.prefs", 0);
        if (this.f747a == null) {
            throw new NullPointerException("prefs were null");
        } else if (this.f748b == null) {
            throw new NullPointerException("legacy prefs were null");
        }
    }

    private String m754b() {
        String str = null;
        try {
            String string = Secure.getString(this.f749c.getContentResolver(), "android_id");
            if (!(string == null || string.length() <= 0 || string.equals("9774d56d682e549c"))) {
                UUID nameUUIDFromBytes = UUID.nameUUIDFromBytes(string.getBytes("utf8"));
                if (nameUUIDFromBytes != null) {
                    str = nameUUIDFromBytes.toString();
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
        if (str == null || str.length() == 0) {
            try {
                str = UUID.randomUUID().toString();
            } catch (ThreadDeath e2) {
                throw e2;
            } catch (Throwable th2) {
                dx.m777a(th2);
            }
        }
        return str;
    }

    public final String m755a() {
        String string = this.f747a.getString("hashedDeviceID", null);
        if (string == null) {
            string = this.f748b.getString("com.crittercism.prefs.did", null);
            if (string != null && m753a(string)) {
                Editor edit = this.f748b.edit();
                edit.remove("com.crittercism.prefs.did");
                edit.commit();
            }
        }
        if (string != null) {
            return string;
        }
        string = m754b();
        m753a(string);
        return string;
    }

    private boolean m753a(String str) {
        Editor edit = this.f747a.edit();
        edit.putString("hashedDeviceID", str);
        return edit.commit();
    }
}
