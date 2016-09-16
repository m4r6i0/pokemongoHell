package crittercism.android;

import com.google.android.gms.common.GooglePlayServicesUtil;
import crittercism.android.C1167do.C1164a.C1163a;
import crittercism.android.C1167do.C1166b.C1165a;
import java.util.HashMap;
import java.util.Map;

public final class dp {
    private static Map f745a;

    static {
        Map hashMap = new HashMap();
        f745a = hashMap;
        hashMap.put("com.amazon.venezia", new C1163a());
        f745a.put(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE, new C1165a());
    }

    public static dn m750a(String str) {
        if (str == null || !f745a.containsKey(str)) {
            return null;
        }
        return (dn) f745a.get(str);
    }
}
