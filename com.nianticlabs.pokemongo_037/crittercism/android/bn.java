package crittercism.android;

import java.util.HashMap;
import java.util.Map;

public final class bn {
    private static final Map f498a;
    private String f499b;
    private String f500c;
    private String f501d;
    private String f502e;

    /* renamed from: crittercism.android.bn.a */
    public static class C1123a extends Exception {
        public C1123a(String str) {
            super(str);
        }
    }

    static {
        Map hashMap = new HashMap();
        f498a = hashMap;
        hashMap.put("00555300", "crittercism.com");
        f498a.put("00555304", "crit-ci.com");
        f498a.put("00555305", "crit-staging.com");
        f498a.put("00444503", "eu.crittercism.com");
    }

    public bn(String str) {
        if (str == null) {
            throw new C1123a("Given null appId");
        } else if (!str.matches("[0-9a-fA-F]+")) {
            throw new C1123a("Invalid appId: '" + str + "'. AppId must be hexadecimal characters");
        } else if (str.length() == 24 || str.length() == 40) {
            Object obj = null;
            if (str.length() == 24) {
                obj = "00555300";
            } else if (str.length() == 40) {
                obj = str.substring(str.length() - 8);
            }
            String str2 = (String) f498a.get(obj);
            if (str2 == null) {
                throw new C1123a("Invalid appId: '" + str + "'. Invalid app locator code");
            }
            this.f499b = System.getProperty("com.crittercism.apmUrl", "https://apm." + str2);
            this.f500c = System.getProperty("com.crittercism.apiUrl", "https://api." + str2);
            this.f501d = System.getProperty("com.crittercism.txnUrl", "https://txn.ingest." + str2);
            this.f502e = System.getProperty("com.crittercism.appLoadUrl", "https://appload.ingest." + str2);
        } else {
            throw new C1123a("Invalid appId: '" + str + "'. AppId must be either 24 or 40 characters");
        }
    }

    public final String m555a() {
        return this.f500c;
    }

    public final String m556b() {
        return this.f499b;
    }

    public final String m557c() {
        return this.f502e;
    }

    public final String m558d() {
        return this.f501d;
    }
}
