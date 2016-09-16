package crittercism.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.crittercism.app.CrittercismConfig;
import spacemadness.com.lunarconsole.BuildConfig;

public final class at {
    public String f319a;
    public int f320b;

    public at(Context context, CrittercismConfig crittercismConfig) {
        this.f319a = BuildConfig.VERSION_NAME;
        this.f320b = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f319a = packageInfo.versionName;
            this.f320b = packageInfo.versionCode;
        } catch (NameNotFoundException e) {
        }
        String customVersionName = crittercismConfig.getCustomVersionName();
        if (customVersionName != null && customVersionName.length() > 0) {
            this.f319a = customVersionName;
        }
        if (crittercismConfig.isVersionCodeToBeIncludedInVersionString()) {
            this.f319a += "-" + Integer.toString(this.f320b);
        }
    }
}
