package crittercism.android;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.location.places.Place;
import com.voxelbusters.nativeplugins.defines.Keys.Twitter;
import java.math.BigInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spacemadness.com.lunarconsole.BuildConfig;

public final class bx {
    private static at f554a;
    private static Context f555b;
    private static bf f556c;
    private static cb f557d;

    /* renamed from: crittercism.android.bx.a */
    public static class C1125a implements bw {
        private String f534a;

        public final /* bridge */ /* synthetic */ Object m587b() {
            return this.f534a;
        }

        public C1125a() {
            String str = null;
            this.f534a = null;
            bx.f556c;
            bx.f555b;
            if (bx.f556c.f406b) {
                str = ((RunningTaskInfo) ((ActivityManager) bx.f555b.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.flattenToShortString().replace("/", BuildConfig.FLAVOR);
            }
            this.f534a = str;
        }

        public final String m586a() {
            return "activity";
        }
    }

    public static class aa implements bw {
        private Float f535a;

        public final /* bridge */ /* synthetic */ Object m589b() {
            return this.f535a;
        }

        public aa() {
            this.f535a = null;
            bx.f555b;
            this.f535a = Float.valueOf(bx.f555b.getResources().getDisplayMetrics().ydpi);
        }

        public final String m588a() {
            return "ydpi";
        }
    }

    /* renamed from: crittercism.android.bx.b */
    public static class C1126b implements bw {
        private Integer f536a;

        public final /* bridge */ /* synthetic */ Object m591b() {
            return this.f536a;
        }

        public C1126b() {
            this.f536a = null;
            bx.f554a;
            this.f536a = Integer.valueOf(bx.f554a.f320b);
        }

        public final String m590a() {
            return "app_version_code";
        }
    }

    /* renamed from: crittercism.android.bx.c */
    public static class C1127c implements bw {
        private String f537a;

        public final /* bridge */ /* synthetic */ Object m593b() {
            return this.f537a;
        }

        public C1127c() {
            this.f537a = null;
            bx.f554a;
            this.f537a = bx.f554a.f319a;
        }

        public final String m592a() {
            return "app_version";
        }
    }

    /* renamed from: crittercism.android.bx.d */
    public static class C1128d implements bw {
        public final /* synthetic */ Object m595b() {
            return System.getProperty("os.arch");
        }

        public final String m594a() {
            return "arch";
        }
    }

    /* renamed from: crittercism.android.bx.e */
    public static class C1129e implements bw {
        private Double f538a;

        public final /* bridge */ /* synthetic */ Object m597b() {
            return this.f538a;
        }

        public C1129e() {
            this.f538a = null;
            bx.f555b;
            double d = 1.0d;
            Intent registerReceiver = bx.f555b.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("level", -1);
            double intExtra2 = (double) registerReceiver.getIntExtra("scale", -1);
            if (intExtra >= 0 && intExtra2 > 0.0d) {
                d = ((double) intExtra) / intExtra2;
            }
            this.f538a = Double.valueOf(d);
        }

        public final String m596a() {
            return "battery_level";
        }
    }

    /* renamed from: crittercism.android.bx.f */
    public static class C1130f implements bw {
        public String f539a;

        public final /* bridge */ /* synthetic */ Object m599b() {
            return this.f539a;
        }

        public C1130f() {
            String networkOperatorName;
            this.f539a = null;
            bx.f555b;
            try {
                networkOperatorName = ((TelephonyManager) bx.f555b.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception e) {
                networkOperatorName = Build.BRAND;
            }
            this.f539a = networkOperatorName;
            new StringBuilder("carrier == ").append(this.f539a);
            dx.m778b();
        }

        public final String m598a() {
            return "carrier";
        }
    }

    /* renamed from: crittercism.android.bx.g */
    static class C1131g implements bw {
        private JSONObject f540a;

        public final /* synthetic */ Object m602b() {
            return m603c();
        }

        public C1131g(int i) {
            this.f540a = null;
            bx.f555b;
            bx.f556c;
            this.f540a = C1131g.m600a(i);
        }

        private static JSONObject m600a(int i) {
            Object obj = 1;
            if (!bx.f556c.f407c) {
                return null;
            }
            if (!ConnectivityManager.isNetworkTypeValid(i)) {
                return null;
            }
            NetworkInfo networkInfo = ((ConnectivityManager) bx.f555b.getSystemService("connectivity")).getNetworkInfo(i);
            JSONObject jSONObject = new JSONObject();
            if (networkInfo != null) {
                try {
                    jSONObject.put("available", networkInfo.isAvailable());
                    jSONObject.put("connected", networkInfo.isConnected());
                    if (!networkInfo.isConnected()) {
                        jSONObject.put("connecting", networkInfo.isConnectedOrConnecting());
                    }
                    jSONObject.put("failover", networkInfo.isFailover());
                    if (i != 0) {
                        obj = null;
                    }
                    if (obj == null) {
                        return jSONObject;
                    }
                    jSONObject.put("roaming", networkInfo.isRoaming());
                    return jSONObject;
                } catch (JSONException e) {
                    dx.m781c();
                    return null;
                }
            }
            jSONObject.put("available", false);
            jSONObject.put("connected", false);
            jSONObject.put("connecting", false);
            jSONObject.put("failover", false);
            if (i != 0) {
                obj = null;
            }
            if (obj == null) {
                return jSONObject;
            }
            jSONObject.put("roaming", false);
            return jSONObject;
        }

        public String m601a() {
            return null;
        }

        public JSONObject m603c() {
            return this.f540a;
        }
    }

    /* renamed from: crittercism.android.bx.h */
    public static class C1132h implements bw {
        private Float f541a;

        public final /* bridge */ /* synthetic */ Object m605b() {
            return this.f541a;
        }

        public C1132h() {
            this.f541a = null;
            bx.f555b;
            this.f541a = Float.valueOf(bx.f555b.getResources().getDisplayMetrics().density);
        }

        public final String m604a() {
            return "dpi";
        }
    }

    /* renamed from: crittercism.android.bx.i */
    public static class C1133i implements bw {
        private String f542a;

        public final /* bridge */ /* synthetic */ Object m607b() {
            return this.f542a;
        }

        public C1133i() {
            this.f542a = null;
            try {
                BigInteger.valueOf(-1);
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                this.f542a = BigInteger.valueOf((long) statFs.getAvailableBlocks()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).toString();
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                this.f542a = null;
            }
        }

        public final String m606a() {
            return "disk_space_free";
        }
    }

    /* renamed from: crittercism.android.bx.j */
    public static class C1134j implements bw {
        private String f543a;

        public final /* bridge */ /* synthetic */ Object m609b() {
            return this.f543a;
        }

        public C1134j() {
            this.f543a = null;
            try {
                BigInteger.valueOf(-1);
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                this.f543a = BigInteger.valueOf((long) statFs.getBlockCount()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).toString();
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                this.f543a = null;
            }
        }

        public final String m608a() {
            return "disk_space_total";
        }
    }

    /* renamed from: crittercism.android.bx.k */
    public static class C1135k implements bw {
        public String f544a;

        public final /* bridge */ /* synthetic */ Object m611b() {
            return this.f544a;
        }

        public C1135k() {
            this.f544a = null;
            bx.f555b;
            this.f544a = bx.f555b.getResources().getConfiguration().locale.getLanguage();
            if (this.f544a == null || this.f544a.length() == 0) {
                this.f544a = "en";
            }
        }

        public final String m610a() {
            return "locale";
        }
    }

    /* renamed from: crittercism.android.bx.l */
    public static class C1136l implements bw {
        private JSONArray f545a;

        public final /* bridge */ /* synthetic */ Object m613b() {
            return this.f545a;
        }

        public C1136l() {
            this.f545a = null;
            bx.f556c;
            bx.f557d;
            if (bx.f556c.f405a) {
                this.f545a = bx.f557d.m678a();
            }
        }

        public final String m612a() {
            return "logcat";
        }
    }

    /* renamed from: crittercism.android.bx.m */
    public static class C1137m implements bw {
        private Long f546a;

        public final /* bridge */ /* synthetic */ Object m615b() {
            return this.f546a;
        }

        public C1137m() {
            this.f546a = null;
            this.f546a = Long.valueOf(Runtime.getRuntime().maxMemory());
        }

        public final String m614a() {
            return "memory_total";
        }
    }

    /* renamed from: crittercism.android.bx.n */
    public static class C1138n implements bw {
        private Integer f547a;

        public final /* bridge */ /* synthetic */ Object m617b() {
            return this.f547a;
        }

        public C1138n() {
            this.f547a = null;
            MemoryInfo memoryInfo = new MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            this.f547a = Integer.valueOf((memoryInfo.otherPss + (memoryInfo.dalvikPss + memoryInfo.nativePss)) * Place.TYPE_SUBLOCALITY_LEVEL_2);
        }

        public final String m616a() {
            return "memory_usage";
        }
    }

    /* renamed from: crittercism.android.bx.o */
    public static class C1139o implements bw {
        public Integer f548a;

        public final /* bridge */ /* synthetic */ Object m619b() {
            return this.f548a;
        }

        public C1139o() {
            this.f548a = Integer.valueOf(0);
            bx.f555b;
            try {
                String networkOperator = ((TelephonyManager) bx.f555b.getSystemService("phone")).getNetworkOperator();
                if (networkOperator != null) {
                    this.f548a = Integer.valueOf(Integer.parseInt(networkOperator.substring(0, 3)));
                }
                new StringBuilder("mobileCountryCode == ").append(this.f548a);
                dx.m778b();
            } catch (Exception e) {
            }
        }

        public final String m618a() {
            return "mobile_country_code";
        }
    }

    /* renamed from: crittercism.android.bx.p */
    public static class C1140p implements bw {
        public Integer f549a;

        public final /* bridge */ /* synthetic */ Object m621b() {
            return this.f549a;
        }

        public C1140p() {
            this.f549a = Integer.valueOf(0);
            bx.f555b;
            try {
                String networkOperator = ((TelephonyManager) bx.f555b.getSystemService("phone")).getNetworkOperator();
                if (networkOperator != null) {
                    this.f549a = Integer.valueOf(Integer.parseInt(networkOperator.substring(3)));
                }
                new StringBuilder("mobileNetworkCode == ").append(this.f549a);
                dx.m778b();
            } catch (Exception e) {
            }
        }

        public final String m620a() {
            return "mobile_network_code";
        }
    }

    /* renamed from: crittercism.android.bx.q */
    public static class C1141q extends C1131g {
        public final /* bridge */ /* synthetic */ JSONObject m623c() {
            return super.m603c();
        }

        public C1141q() {
            super(0);
        }

        public final String m622a() {
            return "mobile_network";
        }
    }

    /* renamed from: crittercism.android.bx.r */
    public static class C1142r implements bw {
        public final /* bridge */ /* synthetic */ Object m625b() {
            return Build.MODEL;
        }

        public final String m624a() {
            return Models.CONTENT_DIRECTORY;
        }
    }

    /* renamed from: crittercism.android.bx.s */
    public static class C1143s implements bw {
        public final /* synthetic */ Object m627b() {
            return new String();
        }

        public final String m626a() {
            return Twitter.NAME;
        }
    }

    /* renamed from: crittercism.android.bx.t */
    public static class C1144t implements bw {
        private Integer f550a;

        public final /* bridge */ /* synthetic */ Object m629b() {
            return this.f550a;
        }

        public C1144t() {
            this.f550a = null;
            bx.f555b;
            int i = bx.f555b.getResources().getConfiguration().orientation;
            if (i == 0) {
                Display defaultDisplay = ((WindowManager) bx.f555b.getSystemService("window")).getDefaultDisplay();
                if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
                    i = 3;
                } else if (defaultDisplay.getWidth() > defaultDisplay.getHeight()) {
                    i = 2;
                } else {
                    i = 1;
                }
            }
            this.f550a = Integer.valueOf(i);
        }

        public final String m628a() {
            return "orientation";
        }
    }

    /* renamed from: crittercism.android.bx.u */
    public static class C1145u implements bw {
        private String f551a;

        public final /* bridge */ /* synthetic */ Object m631b() {
            return this.f551a;
        }

        public C1145u() {
            this.f551a = null;
            try {
                BigInteger.valueOf(-1);
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                this.f551a = BigInteger.valueOf((long) statFs.getAvailableBlocks()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).toString();
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                this.f551a = null;
            }
        }

        public final String m630a() {
            return "sd_space_free";
        }
    }

    /* renamed from: crittercism.android.bx.v */
    public static class C1146v implements bw {
        private String f552a;

        public final /* bridge */ /* synthetic */ Object m633b() {
            return this.f552a;
        }

        public C1146v() {
            this.f552a = null;
            try {
                BigInteger.valueOf(-1);
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                this.f552a = BigInteger.valueOf((long) statFs.getBlockCount()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).toString();
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                this.f552a = null;
            }
        }

        public final String m632a() {
            return "sd_space_total";
        }
    }

    /* renamed from: crittercism.android.bx.w */
    public static class C1147w implements bw {
        public final /* bridge */ /* synthetic */ Object m635b() {
            return "android";
        }

        public final String m634a() {
            return "system";
        }
    }

    /* renamed from: crittercism.android.bx.x */
    public static class C1148x implements bw {
        public final /* bridge */ /* synthetic */ Object m637b() {
            return VERSION.RELEASE;
        }

        public final String m636a() {
            return "system_version";
        }
    }

    /* renamed from: crittercism.android.bx.y */
    public static class C1149y extends C1131g {
        public final /* bridge */ /* synthetic */ JSONObject m639c() {
            return super.m603c();
        }

        public C1149y() {
            super(1);
        }

        public final String m638a() {
            return "wifi";
        }
    }

    /* renamed from: crittercism.android.bx.z */
    public static class C1150z implements bw {
        private Float f553a;

        public final /* bridge */ /* synthetic */ Object m641b() {
            return this.f553a;
        }

        public C1150z() {
            this.f553a = null;
            bx.f555b;
            this.f553a = Float.valueOf(bx.f555b.getResources().getDisplayMetrics().xdpi);
        }

        public final String m640a() {
            return "xdpi";
        }
    }

    static {
        f554a = null;
        f555b = null;
        f556c = null;
        f557d = null;
    }

    public static void m644a(at atVar) {
        f554a = atVar;
    }

    public static void m643a(Context context) {
        f555b = context;
    }

    public static void m646a(cb cbVar) {
        f557d = cbVar;
    }

    public static void m645a(bf bfVar) {
        f556c = bfVar;
    }
}
