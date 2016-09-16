package crittercism.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.ConditionVariable;
import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.os.Process;
import com.crittercism.app.CritterRateMyAppButtons;
import com.crittercism.app.CrittercismConfig;
import com.crittercism.app.Transaction;
import com.crittercism.integrations.PluginException;
import com.mopub.volley.toolbox.HttpClientStack.HttpPatch;
import crittercism.android.C1153c.C1152a;
import crittercism.android.bx.C1130f;
import crittercism.android.bx.C1139o;
import crittercism.android.bx.C1140p;
import crittercism.android.cs.C1158b;
import crittercism.android.ct.C1159a;
import crittercism.android.cu.C1160a;
import crittercism.android.da.C1162a;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
import spacemadness.com.lunarconsole.BuildConfig;

public final class az implements au, aw, ax, C1110f {
    static az f354a;
    public dt f355A;
    int f356B;
    public boolean f357C;
    private String f358D;
    private bs f359E;
    private bs f360F;
    private C1174g f361G;
    private at f362H;
    private boolean f363I;
    private String f364J;
    public boolean f365b;
    public Context f366c;
    public final ConditionVariable f367d;
    public final ConditionVariable f368e;
    public dw f369f;
    bs f370g;
    bs f371h;
    bs f372i;
    bs f373j;
    bs f374k;
    bs f375l;
    bs f376m;
    bs f377n;
    bs f378o;
    cv f379p;
    public dg f380q;
    ExecutorService f381r;
    public ExecutorService f382s;
    public boolean f383t;
    public bb f384u;
    protected C1171e f385v;
    public dr f386w;
    dv f387x;
    public bi f388y;
    public Map f389z;

    /* renamed from: crittercism.android.az.10 */
    class AnonymousClass10 implements OnClickListener {
        final /* synthetic */ String f330a;
        final /* synthetic */ az f331b;

        AnonymousClass10(az azVar, String str) {
            this.f331b = azVar;
            this.f330a = str;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            try {
                az.m400A().m416a(this.f330a);
            } catch (Exception e) {
                dx.m782c("YES button failed.  Email support@crittercism.com.");
            }
        }
    }

    /* renamed from: crittercism.android.az.1 */
    public class C11001 extends di {
        final /* synthetic */ az f334a;

        public C11001(az azVar) {
            this.f334a = azVar;
        }

        public final void m391a() {
            if (!this.f334a.f369f.m772b()) {
                ch chVar = this.f334a.f380q.f712b;
                if (chVar != null) {
                    this.f334a.f370g.m577a(chVar);
                }
                df dfVar = new df(this.f334a.f366c);
                dfVar.m725a(this.f334a.f370g, new C1159a(), this.f334a.f384u.m470e(), "/v0/appload", this.f334a.f384u.m467b(), az.f354a, new C1158b());
                dfVar.m725a(this.f334a.f371h, new C1162a(), this.f334a.f384u.m467b(), "/android_v2/handle_exceptions", null, az.f354a, new C1160a());
                dfVar.m725a(this.f334a.f372i, new C1162a(), this.f334a.f384u.m467b(), "/android_v2/handle_ndk_crashes", null, az.f354a, new C1160a());
                dfVar.m725a(this.f334a.f373j, new C1162a(), this.f334a.f384u.m467b(), "/android_v2/handle_crashes", null, az.f354a, new C1160a());
                dfVar.m726a(this.f334a.f380q, this.f334a.f381r);
            }
        }
    }

    /* renamed from: crittercism.android.az.2 */
    class C11012 extends di {
        final /* synthetic */ az f335a;
        final /* synthetic */ JSONObject f336b;
        final /* synthetic */ az f337c;

        C11012(az azVar, az azVar2, JSONObject jSONObject) {
            this.f337c = azVar;
            this.f335a = azVar2;
            this.f336b = jSONObject;
        }

        public final void m392a() {
            if (!this.f335a.f369f.m772b()) {
                this.f335a.f387x.m767a(this.f336b);
                if (this.f335a.f387x.m769b()) {
                    this.f335a.m407E();
                }
            }
        }
    }

    /* renamed from: crittercism.android.az.3 */
    class C11023 extends di {
        final /* synthetic */ az f338a;
        final /* synthetic */ az f339b;

        C11023(az azVar, az azVar2) {
            this.f339b = azVar;
            this.f338a = azVar2;
        }

        public final void m393a() {
            if (!this.f338a.f369f.m772b()) {
                cw cuVar = new cu(this.f338a);
                JSONObject a = this.f338a.f387x.m766a();
                cuVar.f692a.put("metadata", a);
                new dj(cuVar, new dc(new db(this.f339b.f384u.m467b(), "/android_v2/update_user_metadata").m720a()), new dd(this.f338a.f387x)).run();
            }
        }
    }

    /* renamed from: crittercism.android.az.4 */
    public static /* synthetic */ class C11034 {
        public static final /* synthetic */ int[] f340a;

        static {
            f340a = new int[CritterRateMyAppButtons.values().length];
            try {
                f340a[CritterRateMyAppButtons.YES.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f340a[CritterRateMyAppButtons.NO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f340a[CritterRateMyAppButtons.LATER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: crittercism.android.az.5 */
    class C11045 extends di {
        final /* synthetic */ Throwable f341a;
        final /* synthetic */ long f342b;
        final /* synthetic */ az f343c;

        C11045(az azVar, Throwable th, long j) {
            this.f343c = azVar;
            this.f341a = th;
            this.f342b = j;
        }

        public final void m394a() {
            if (!this.f343c.f369f.m772b()) {
                synchronized (this.f343c.f379p) {
                    if (this.f343c.f356B < 10) {
                        bk bkVar = new bk(this.f341a, this.f342b);
                        bkVar.m546a("current_session", this.f343c.f374k);
                        bkVar.m544a(this.f343c.f375l);
                        bkVar.f478f = "he";
                        if (this.f343c.f379p.m715a()) {
                            new dj(new cu(az.f354a).m712a(br.HAND_EXCS.m565f(), new JSONArray().put(bkVar.m548b())), new dc(new db(this.f343c.f384u.m467b(), "/android_v2/handle_exceptions").m720a()), null).run();
                            az azVar = this.f343c;
                            azVar.f356B++;
                            this.f343c.f379p.m716b();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: crittercism.android.az.6 */
    class C11056 extends di {
        final /* synthetic */ Throwable f344a;
        final /* synthetic */ long f345b;
        final /* synthetic */ az f346c;

        C11056(az azVar, Throwable th, long j) {
            this.f346c = azVar;
            this.f344a = th;
            this.f345b = j;
        }

        public final void m395a() {
            if (!this.f346c.f369f.m772b()) {
                ch bkVar = new bk(this.f344a, this.f345b);
                bkVar.m546a("current_session", this.f346c.f374k);
                bkVar.f478f = "he";
                if (this.f346c.f371h.m577a(bkVar)) {
                    az.f354a.m414a(new by(bkVar.f475c, bkVar.f476d));
                    if (this.f346c.f379p.m715a()) {
                        df dfVar = new df(this.f346c.f366c);
                        dfVar.m725a(this.f346c.f371h, new C1162a(), this.f346c.f384u.m467b(), "/android_v2/handle_exceptions", null, az.f354a, new C1160a());
                        dfVar.m726a(this.f346c.f380q, this.f346c.f381r);
                        this.f346c.f379p.m716b();
                    }
                }
            }
        }
    }

    /* renamed from: crittercism.android.az.7 */
    public class C11067 extends di {
        final /* synthetic */ cf f347a;
        final /* synthetic */ az f348b;

        public C11067(az azVar, cf cfVar) {
            this.f348b = azVar;
            this.f347a = cfVar;
        }

        public final void m396a() {
            this.f348b.f374k.m577a(this.f347a);
        }
    }

    /* renamed from: crittercism.android.az.8 */
    class C11078 extends di {
        final /* synthetic */ C1153c f349a;
        final /* synthetic */ az f350b;

        C11078(az azVar, C1153c c1153c) {
            this.f350b = azVar;
            this.f349a = c1153c;
        }

        public final void m397a() {
            this.f350b.f375l.m577a(this.f349a);
        }
    }

    /* renamed from: crittercism.android.az.9 */
    class C11089 extends di {
        final /* synthetic */ ci f351a;
        final /* synthetic */ az f352b;

        C11089(az azVar, ci ciVar) {
            this.f352b = azVar;
            this.f351a = ciVar;
        }

        public final void m398a() {
            this.f352b.f376m.m577a(this.f351a);
        }
    }

    /* renamed from: crittercism.android.az.a */
    static class C1109a implements IdleHandler {
        private boolean f353a;

        private C1109a() {
            this.f353a = false;
        }

        public final boolean queueIdle() {
            synchronized (this) {
                if (!this.f353a) {
                    this.f353a = true;
                    bg.m504g();
                }
            }
            return true;
        }
    }

    protected az() {
        this.f365b = false;
        this.f366c = null;
        this.f358D = null;
        this.f367d = new ConditionVariable(false);
        this.f368e = new ConditionVariable(false);
        this.f369f = new dw();
        this.f379p = null;
        this.f380q = null;
        this.f361G = null;
        this.f381r = Executors.newCachedThreadPool(new dz());
        this.f382s = Executors.newSingleThreadExecutor(new dz());
        this.f363I = false;
        this.f383t = false;
        this.f364J = BuildConfig.FLAVOR;
        this.f387x = null;
        this.f389z = new HashMap();
        this.f355A = null;
        this.f356B = 0;
        this.f357C = false;
        this.f385v = new C1171e(this.f382s);
    }

    public static az m400A() {
        if (f354a == null) {
            f354a = new az();
        }
        return f354a;
    }

    public final void m411a(Context context, String str, CrittercismConfig crittercismConfig) {
        dx.m775a("Initializing Crittercism 5.0.8 for App ID " + str);
        bn bnVar = new bn(str);
        this.f358D = str;
        this.f384u = new bb(bnVar, crittercismConfig);
        this.f366c = context;
        this.f362H = new at(this.f366c, this.f384u);
        this.f364J = context.getPackageName();
        this.f386w = new dr(context);
        m402G();
        long j = 60000000000L;
        if (this.f383t) {
            j = 12000000000L;
        }
        this.f379p = new cv(j);
        if (!m401F()) {
            dx.m782c("Crittercism should be initialized in onCreate() of MainActivity");
        }
        bx.m644a(this.f362H);
        bx.m643a(this.f366c);
        bx.m646a(new cc());
        bx.m645a(new bf(this.f366c, this.f384u));
        try {
            this.f385v.m788a(this.f384u.m466a());
            this.f385v.m789b(this.f384u.getPreserveQueryStringPatterns());
            this.f361G = new C1174g(this, new URL(this.f384u.m468c() + "/api/apm/network"));
            this.f385v.m787a(this.f361G);
            this.f385v.m787a((C1110f) this);
            new dy(this.f361G, "OPTMZ").start();
            if (!C1175h.m805a(this.f366c).exists() && this.f384u.isServiceMonitoringEnabled()) {
                this.f363I = new C1178i(this.f385v, new C1161d(this.f366c)).m816a();
                new StringBuilder("installedApm = ").append(this.f363I);
                dx.m778b();
            }
        } catch (Exception e) {
            new StringBuilder("Exception in startApm: ").append(e.getClass().getName());
            dx.m778b();
            dx.m781c();
        }
        this.f380q = new dg(this.f384u, context, this, this, this);
        if (!this.f383t) {
            dx.m774a(new ec(this, this.f382s, this.f380q, this.f369f));
        }
        UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUncaughtExceptionHandler instanceof ay)) {
            Thread.setDefaultUncaughtExceptionHandler(new ay(this, defaultUncaughtExceptionHandler));
        }
        if (VERSION.SDK_INT < 14) {
            dx.m775a("API Level is less than 14. Automatic breadcrumbs are not supported.");
        } else if (this.f366c instanceof Application) {
            dx.m778b();
            ((Application) this.f366c).registerActivityLifecycleCallbacks(new av(this.f366c, this));
        } else {
            dx.m782c("Application context not provided. Automatic breadcrumbs will not be recorded.");
        }
        if (!this.f383t) {
            bg.m499b(this);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Looper.myQueue().addIdleHandler(new C1109a());
            }
        }
        new dy(this.f380q).start();
        this.f365b = true;
    }

    private static boolean m401F() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getMethodName().equals("onCreate") || stackTraceElement.getMethodName().equals("onResume")) {
                return true;
            }
        }
        return false;
    }

    private void m402G() {
        int myUid = Process.myUid();
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) this.f366c.getSystemService("activity");
        int i = 0;
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            int i2;
            if (runningAppProcessInfo.uid == myUid) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        if (i <= 1) {
            this.f383t = false;
            return;
        }
        for (RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (runningServiceInfo.pid == myPid) {
                this.f383t = true;
                return;
            }
        }
    }

    public final void m420a(Throwable th) {
        if (this.f380q == null) {
            dx.m779b("Unable to handle application crash. Crittercism not yet initialized");
            return;
        }
        this.f380q.m734b();
        dq.m752a(this.f366c, true);
        if (!this.f369f.m772b()) {
            if (this.f383t) {
                new dj(new cu(this).m712a(br.SDK_CRASHES.m565f(), new JSONArray().put(new bk(th, Thread.currentThread().getId()).m548b())), new dc(new db(this.f384u.m467b(), "/android_v2/handle_crashes").m720a()), null).run();
                return;
            }
            List a = bg.m492a(this, th instanceof PluginException);
            ch bkVar = new bk(th, Thread.currentThread().getId());
            bkVar.m546a("crashed_session", this.f374k);
            if (this.f360F.m578b() > 0) {
                bkVar.m546a("previous_session", this.f360F);
            }
            bkVar.m544a(this.f375l);
            bkVar.f474b = new bo(this.f376m).f503a;
            bkVar.m543a();
            bkVar.m547a(a);
            this.f373j.m577a(bkVar);
            df dfVar = new df(this.f366c);
            dfVar.m725a(this.f370g, new C1162a(), this.f384u.m470e(), "/v0/appload", null, this, new C1158b());
            dfVar.m725a(this.f371h, new C1162a(), this.f384u.m467b(), "/android_v2/handle_exceptions", null, this, new C1160a());
            dfVar.m725a(this.f372i, new C1162a(), this.f384u.m467b(), "/android_v2/handle_ndk_crashes", null, this, new C1160a());
            dfVar.m725a(this.f373j, new C1162a(), this.f384u.m467b(), "/android_v2/handle_crashes", null, this, new C1160a());
            try {
                dfVar.m724a();
            } catch (InterruptedException e) {
                new StringBuilder("InterruptedException in logCrashException: ").append(e.getMessage());
                dx.m778b();
                dx.m781c();
            } catch (Throwable th2) {
                new StringBuilder("Unexpected throwable in logCrashException: ").append(th2.getMessage());
                dx.m778b();
                dx.m781c();
            }
        }
    }

    public final void m419a(String str, URL url, long j, long j2, long j3, int i, Exception exception, long j4) {
        if (str == null) {
            dx.m779b("Null HTTP request method provided. Endpoint will not be logged.");
            return;
        }
        String toUpperCase = str.toUpperCase(Locale.US);
        Set hashSet = new HashSet();
        hashSet.add("GET");
        hashSet.add("POST");
        hashSet.add("HEAD");
        hashSet.add("PUT");
        hashSet.add("DELETE");
        hashSet.add("TRACE");
        hashSet.add("OPTIONS");
        hashSet.add("CONNECT");
        hashSet.add(HttpPatch.METHOD_NAME);
        if (!hashSet.contains(toUpperCase)) {
            dx.m782c("Logging endpoint with invalid HTTP request method: " + str);
        }
        if (url == null) {
            dx.m779b("Null URL provided. Endpoint will not be logged");
        } else if (j2 < 0 || j3 < 0) {
            dx.m779b("Invalid byte values. Bytes need to be non-negative. Endpoint will not be logged.");
        } else {
            if (i != 0) {
                if (i < 100 || i >= 600) {
                    dx.m782c("Logging endpoint with invalid HTTP response code: " + Integer.toString(i));
                }
            } else if (exception == null) {
                dx.m782c("Logging endpoint with null error and response code of 0.");
            }
            C1111b a = new C1161d(this.f366c).m717a();
            if (j < 0) {
                dx.m779b("Invalid latency. Endpoint will not be logged.");
            } else if (j4 < 0) {
                dx.m779b("Invalid start time. Endpoint will not be logged.");
            } else {
                C1153c c1153c = new C1153c();
                c1153c.f581f = toUpperCase;
                c1153c.m662a(url.toExternalForm());
                c1153c.m666b(j2);
                c1153c.m671d(j3);
                c1153c.f580e = i;
                c1153c.f585j = a;
                c1153c.m673e(j4);
                c1153c.m675f(j4 + j);
                if (bc.m475b()) {
                    c1153c.m659a(bc.m473a());
                }
                c1153c.m663a((Throwable) exception);
                this.f385v.m786a(c1153c, C1152a.LOG_ENDPOINT);
            }
        }
    }

    private String m403H() {
        try {
            if (this.f364J == null || this.f364J.equals(BuildConfig.FLAVOR)) {
                this.f364J = this.f366c.getPackageName();
            }
        } catch (Exception e) {
            dx.m782c("Call to getPackageName() failed.  Please contact us at support@crittercism.com.");
            this.f364J = new String();
        }
        return this.f364J;
    }

    public final synchronized void m425b(Throwable th) {
        if (th == null) {
            dx.m782c("Calling logHandledException with a null java.lang.Throwable. Nothing will be reported to Crittercism");
        } else if (this.f383t) {
            r2 = new C11045(this, th, Thread.currentThread().getId());
            if (!this.f380q.m733a(r2)) {
                this.f382s.execute(r2);
            }
        } else {
            r2 = new C11056(this, th, Thread.currentThread().getId());
            if (!this.f380q.m733a(r2)) {
                this.f382s.execute(r2);
            }
        }
    }

    public final void m413a(C1153c c1153c) {
        Runnable c11078 = new C11078(this, c1153c);
        if (!this.f380q.m733a(c11078)) {
            this.f382s.execute(c11078);
        }
    }

    public final void m414a(ci ciVar) {
        if (!this.f369f.m772b()) {
            Runnable c11089 = new C11089(this, ciVar);
            if (!this.f380q.m733a(c11089)) {
                this.f382s.execute(c11089);
            }
        }
    }

    public final String m409a() {
        String str = this.f358D;
        if (str == null) {
            return BuildConfig.FLAVOR;
        }
        return str;
    }

    public final String m426c() {
        String str = BuildConfig.FLAVOR;
        if (this.f386w != null) {
            return this.f386w.m755a();
        }
        return str;
    }

    public final String m430f() {
        return new C1130f().f539a;
    }

    public final int m431g() {
        return new C1139o().f548a.intValue();
    }

    public final int m432h() {
        return new C1140p().f549a.intValue();
    }

    public final int m429e() {
        if (this.f369f != null) {
            return Integer.valueOf(this.f369f.m770a().f753a).intValue();
        }
        return -1;
    }

    public final void m450z() {
        if (this.f383t) {
            this.f374k = new bs(this.f366c, br.CURR_BCS).m573a(this.f366c);
        } else {
            this.f374k = new bs(this.f366c, br.CURR_BCS);
        }
        this.f360F = new bs(this.f366c, br.PREV_BCS);
        this.f375l = new bs(this.f366c, br.NW_BCS);
        this.f376m = new bs(this.f366c, br.SYSTEM_BCS);
        this.f370g = new bs(this.f366c, br.APP_LOADS);
        this.f371h = new bs(this.f366c, br.HAND_EXCS);
        this.f359E = new bs(this.f366c, br.INTERNAL_EXCS);
        this.f372i = new bs(this.f366c, br.NDK_CRASHES);
        this.f373j = new bs(this.f366c, br.SDK_CRASHES);
        this.f377n = new bs(this.f366c, br.STARTED_TXNS);
        this.f378o = new bs(this.f366c, br.FINISHED_TXNS);
        if (!this.f383t) {
            this.f387x = new dv(this.f366c, this.f358D);
        }
    }

    public final bs m438n() {
        return this.f370g;
    }

    public final bs m439o() {
        return this.f371h;
    }

    public final bs m440p() {
        return this.f359E;
    }

    public final bs m441q() {
        return this.f372i;
    }

    public final bs m442r() {
        return this.f373j;
    }

    public final dw m436l() {
        return this.f369f;
    }

    public final bs m447w() {
        return this.f377n;
    }

    public final bs m448x() {
        return this.f378o;
    }

    public final String m410a(String str, String str2) {
        SharedPreferences sharedPreferences = this.f366c.getSharedPreferences(str, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(str2, null);
        }
        return null;
    }

    public final void m418a(String str, String str2, String str3) {
        SharedPreferences sharedPreferences = this.f366c.getSharedPreferences(str, 0);
        if (sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.remove(str2);
                edit.putString(str2, str3);
                edit.commit();
            }
        }
    }

    public final void m412a(bh bhVar) {
        bi biVar = this.f388y;
        if (this.f388y != null) {
            bg.m497a(bhVar);
            bg.m505i();
            if (bhVar.f450a) {
                this.f388y.m532a(bhVar.f451b, TimeUnit.SECONDS);
                this.f388y.m533b();
            }
        }
    }

    public final void m415a(C1175h c1175h) {
        if (this.f361G != null && c1175h.f801a && !c1175h.f803c) {
            dx.m775a("Enabling OPTMZ");
            this.f361G.m803a(c1175h.f804d, TimeUnit.SECONDS);
            this.f361G.m802a();
        }
    }

    public final int m423b(String str, String str2) {
        SharedPreferences sharedPreferences = this.f366c.getSharedPreferences(str, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt(str2, 0);
        }
        return 0;
    }

    public final void m417a(String str, String str2, int i) {
        SharedPreferences sharedPreferences = this.f366c.getSharedPreferences(str, 0);
        if (sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.remove(str2);
                edit.putInt(str2, i);
                edit.commit();
            }
        }
    }

    public final boolean m427c(String str, String str2) {
        SharedPreferences sharedPreferences = this.f366c.getSharedPreferences(str, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(str2, false);
        }
        return false;
    }

    public final bs m443s() {
        return this.f374k;
    }

    public final bs m445u() {
        return this.f360F;
    }

    public final bs m444t() {
        return this.f375l;
    }

    public final bs m446v() {
        return this.f376m;
    }

    public final String m424b() {
        return this.f362H.f319a;
    }

    public final String m428d() {
        return CrittercismConfig.API_VERSION;
    }

    public final String m433i() {
        return "Android";
    }

    public final String m434j() {
        return Build.MODEL;
    }

    public final String m435k() {
        return VERSION.RELEASE;
    }

    public final boolean m404B() {
        this.f367d.block();
        return this.f369f.m772b();
    }

    public final void m416a(String str) {
        dt dtVar = this.f355A;
        if (this.f355A != null) {
            this.f355A.m762d();
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(Uri.parse(str));
        this.f366c.startActivity(intent);
    }

    public final void m405C() {
        dt dtVar = this.f355A;
        if (this.f355A != null) {
            this.f355A.m762d();
        }
    }

    public final String m406D() {
        PackageManager packageManager = this.f366c.getPackageManager();
        String H = m403H();
        if (H == null || H.length() <= 0) {
            return null;
        }
        dn a = dp.m750a(packageManager.getInstallerPackageName(H));
        if (a != null) {
            return a.m745a(H).m744a();
        }
        dx.m782c("Could not find app market for this app.  Will try rate-my-app test target in config.");
        return this.f384u.getRateMyAppTestTarget();
    }

    public final AlertDialog m408a(Context context, String str, String str2) {
        AlertDialog alertDialog = null;
        Object obj = null;
        if (this.f369f.m772b()) {
            dx.m779b("User has opted out of crittercism.  generateRateMyAppAlertDialog returning null.");
        } else if (!(context instanceof Activity)) {
            dx.m779b("Context object must be an instance of Activity for AlertDialog to form correctly.  generateRateMyAppAlertDialog returning null.");
        } else if (str2 == null || (str2 != null && str2.length() == 0)) {
            dx.m779b("Message has to be a non-empty string.  generateRateMyAppAlertDialog returning null.");
        } else if (VERSION.SDK_INT < 5) {
            dx.m779b("Rate my app not supported below api level 5");
        } else {
            obj = 1;
        }
        if (obj != null) {
            String D = m406D();
            if (D == null) {
                dx.m779b("Cannot create proper URI to open app market.  Returning null.");
            } else {
                Builder builder = new Builder(context);
                builder.setTitle(str).setMessage(str2);
                try {
                    alertDialog = builder.create();
                    alertDialog.setButton(-1, "Yes", new AnonymousClass10(this, D));
                    alertDialog.setButton(-2, "No", new OnClickListener() {
                        final /* synthetic */ az f332a;

                        {
                            this.f332a = r1;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                az.m400A().m405C();
                            } catch (Exception e) {
                                dx.m782c("NO button failed.  Email support@crittercism.com.");
                            }
                        }
                    });
                    alertDialog.setButton(-3, "Maybe Later", new OnClickListener() {
                        final /* synthetic */ az f333a;

                        {
                            this.f333a = r1;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                az.m400A();
                            } catch (Exception e) {
                                dx.m782c("MAYBE LATER button failed.  Email support@crittercism.com.");
                            }
                        }
                    });
                } catch (Exception e) {
                    dx.m779b("Failed to create AlertDialog instance from AlertDialog.Builder.  Did you remember to call Looper.prepare() before calling Crittercism.generateRateMyAppAlertDialog()?");
                }
            }
        }
        return alertDialog;
    }

    public final void m421a(JSONObject jSONObject) {
        if (!this.f383t) {
            Runnable c11012 = new C11012(this, this, jSONObject);
            if (!this.f380q.m733a(c11012)) {
                this.f382s.execute(c11012);
            }
        }
    }

    public final void m407E() {
        if (!this.f383t) {
            Runnable c11023 = new C11023(this, this);
            if (!this.f380q.m733a(c11023)) {
                this.f381r.execute(c11023);
            }
        }
    }

    public final dv m449y() {
        return this.f387x;
    }

    public final int m422b(String str) {
        if (this.f383t) {
            dx.m782c("Transactions are not supported for services. Returning default value of -1 for " + str + ".");
            return -1;
        }
        int d;
        synchronized (this.f389z) {
            Transaction transaction = (Transaction) this.f389z.get(str);
            if (transaction != null) {
                d = transaction.m39d();
            } else {
                d = -1;
            }
        }
        return d;
    }

    public final dt m437m() {
        return this.f355A;
    }
}
