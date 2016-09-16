package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;

public final class dt {
    public SharedPreferences f752a;

    public dt(Context context) {
        this.f752a = context.getSharedPreferences("com.crittercism.ratemyapp", 0);
    }

    protected dt() {
    }

    public final int m758a() {
        return this.f752a.getInt("numAppLoads", 0);
    }

    public final void m759a(boolean z) {
        this.f752a.edit().putBoolean("rateMyAppEnabled", z).commit();
    }

    public final String m760b() {
        return this.f752a.getString("rateAppMessage", "Would you mind taking a second to rate my app?  I would really appreciate it!");
    }

    public final String m761c() {
        return this.f752a.getString("rateAppTitle", "Rate My App");
    }

    public final void m762d() {
        this.f752a.edit().putBoolean("hasRatedApp", true).commit();
    }
}
