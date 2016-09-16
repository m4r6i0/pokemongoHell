package crittercism.android;

import android.content.Context;
import android.os.Build.VERSION;
import com.crittercism.app.CrittercismConfig;

public final class bf {
    public boolean f405a;
    public boolean f406b;
    public boolean f407c;

    public bf(Context context, CrittercismConfig crittercismConfig) {
        boolean z = true;
        if (!crittercismConfig.isLogcatReportingEnabled() || (VERSION.SDK_INT < 16 && !m481a("android.permission.READ_LOGS", context))) {
            z = false;
        }
        this.f405a = z;
        this.f407c = m481a("android.permission.ACCESS_NETWORK_STATE", context);
        this.f406b = m481a("android.permission.GET_TASKS", context);
    }

    private static boolean m481a(String str, Context context) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
