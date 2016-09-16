package crittercism.android;

import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import com.crittercism.app.Transaction;
import com.voxelbusters.nativeplugins.defines.Keys.GameServices;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bg extends Transaction implements ch {
    private static ExecutorService f431b;
    private static ScheduledExecutorService f432c;
    private static List f433o;
    private static volatile long f434p;
    private static volatile long f435q;
    private static final int[] f436r;
    private static bg f437s;
    private static bh f438t;
    private String f439d;
    private long f440e;
    private int f441f;
    private long f442g;
    private long f443h;
    private long f444i;
    private C1120a f445j;
    private Map f446k;
    private String f447l;
    private long f448m;
    private ScheduledFuture f449n;

    /* renamed from: crittercism.android.bg.1 */
    static class C11121 extends di {
        final /* synthetic */ List f408a;
        final /* synthetic */ az f409b;

        C11121(List list, az azVar) {
            this.f408a = list;
            this.f409b = azVar;
        }

        public final void m482a() {
            for (bg bgVar : this.f408a) {
                synchronized (bgVar) {
                    if (bgVar.f445j == C1120a.STARTED) {
                        this.f409b.f377n.m579b(bgVar);
                    }
                }
            }
        }
    }

    /* renamed from: crittercism.android.bg.2 */
    static class C11132 extends di {
        final /* synthetic */ az f410a;

        C11132(az azVar) {
            this.f410a = azVar;
        }

        public final void m483a() {
            ea eaVar = ea.TXN_CRASH_ALL_FAULT;
            this.f410a.f377n.m574a();
        }
    }

    /* renamed from: crittercism.android.bg.3 */
    static class C11143 extends di {
        final /* synthetic */ az f411a;
        final /* synthetic */ bg f412b;

        C11143(az azVar, bg bgVar) {
            this.f411a = azVar;
            this.f412b = bgVar;
        }

        public final void m484a() {
            this.f411a.f380q.f711a.block();
            this.f411a.f377n.m577a(this.f412b);
        }
    }

    /* renamed from: crittercism.android.bg.4 */
    class C11154 extends di {
        final /* synthetic */ bg f413a;
        final /* synthetic */ bg f414b;

        C11154(bg bgVar, bg bgVar2) {
            this.f414b = bgVar;
            this.f413a = bgVar2;
        }

        public final void m485a() {
            this.f414b.a.f380q.f711a.block();
            this.f414b.a.f377n.m577a(this.f413a);
        }
    }

    /* renamed from: crittercism.android.bg.5 */
    class C11165 extends di {
        final /* synthetic */ bg f415a;

        C11165(bg bgVar) {
            this.f415a = bgVar;
        }

        public final void m486a() {
            this.f415a.m513s();
        }
    }

    /* renamed from: crittercism.android.bg.6 */
    class C11186 extends di {
        final /* synthetic */ bg f417a;
        final /* synthetic */ bg f418b;

        /* renamed from: crittercism.android.bg.6.1 */
        class C11171 implements Runnable {
            final /* synthetic */ C11186 f416a;

            C11171(C11186 c11186) {
                this.f416a = c11186;
            }

            public final void run() {
            }
        }

        C11186(bg bgVar, bg bgVar2) {
            this.f418b = bgVar;
            this.f417a = bgVar2;
        }

        public final void m487a() {
            if (this.f417a.f445j != C1120a.SUCCESS) {
                Runnable c11171 = new C11171(this);
                Executor executor = this.f418b.a.f382s;
                Object futureTask = new FutureTask(c11171, null);
                executor.execute(futureTask);
                try {
                    futureTask.get();
                } catch (Throwable e) {
                    dx.m777a(e);
                } catch (Throwable e2) {
                    dx.m777a(e2);
                }
            }
            this.f418b.a.f380q.f711a.block();
            this.f418b.a.f377n.m576a(this.f418b.f447l);
            this.f418b.a.f378o.m577a(this.f417a);
        }
    }

    /* renamed from: crittercism.android.bg.7 */
    class C11197 extends di {
        final /* synthetic */ bg f419a;
        final /* synthetic */ bg f420b;

        C11197(bg bgVar, bg bgVar2) {
            this.f420b = bgVar;
            this.f419a = bgVar2;
        }

        public final void m488a() {
            this.f420b.a.f380q.f711a.block();
            this.f420b.a.f377n.m577a(this.f419a);
        }
    }

    /* renamed from: crittercism.android.bg.a */
    enum C1120a {
        CREATED,
        STARTED,
        SUCCESS,
        SLOW,
        FAILED,
        TIMEOUT,
        CRASHED,
        ABORTED,
        INTERRUPTED
    }

    static {
        f431b = Executors.newSingleThreadExecutor(new dz());
        f432c = Executors.newScheduledThreadPool(1, new dz());
        f433o = new LinkedList();
        f434p = 0;
        f435q = 0;
        f436r = new int[]{32, 544, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 8224};
        f437s = null;
        f438t = new bh();
    }

    public static void m497a(bh bhVar) {
        f438t = bhVar;
    }

    public bg(az azVar, String str) {
        int i = -1;
        this.f440e = -1;
        this.f441f = -1;
        this.f449n = null;
        if (str.length() > MotionEventCompat.ACTION_MASK) {
            dx.m782c("Transaction name exceeds 255 characters! Truncating to first 255 characters.");
            this.f439d = str.substring(0, MotionEventCompat.ACTION_MASK);
        } else {
            this.f439d = str;
        }
        this.f445j = C1120a.CREATED;
        this.f446k = new HashMap();
        this.a = azVar;
        this.f447l = cg.f620a.m688a();
        this.f440e = -1;
        JSONObject optJSONObject = f438t.f453d.optJSONObject(str);
        if (optJSONObject != null) {
            i = optJSONObject.optInt(GameServices.SCORE_VALUE, -1);
        }
        this.f441f = i;
    }

    private bg(bg bgVar) {
        this.f440e = -1;
        this.f441f = -1;
        this.f449n = null;
        this.f439d = bgVar.f439d;
        this.f440e = bgVar.f440e;
        this.f441f = bgVar.f441f;
        this.f442g = bgVar.f442g;
        this.f443h = bgVar.f443h;
        this.f445j = bgVar.f445j;
        this.f446k = bgVar.f446k;
        this.f447l = bgVar.f447l;
        this.f444i = bgVar.f444i;
        this.f448m = bgVar.f448m;
    }

    public bg(JSONArray jSONArray) {
        this.f440e = -1;
        this.f441f = -1;
        this.f449n = null;
        this.f439d = jSONArray.getString(0);
        this.f445j = C1120a.values()[jSONArray.getInt(1)];
        this.f440e = (long) ((int) (jSONArray.getDouble(2) * 1000.0d));
        this.f441f = jSONArray.optInt(3, -1);
        this.f446k = new HashMap();
        JSONObject jSONObject = jSONArray.getJSONObject(4);
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            this.f446k.put(str, jSONObject.getString(str));
        }
        this.f442g = ed.f788a.m795a(jSONArray.getString(5));
        this.f443h = ed.f788a.m795a(jSONArray.getString(6));
        this.f444i = (long) (jSONArray.optDouble(7, 0.0d) * Math.pow(10.0d, 9.0d));
        this.f447l = cg.f620a.m688a();
    }

    public static void m503f() {
        f434p = System.nanoTime();
        List<bg> linkedList = new LinkedList();
        synchronized (f433o) {
            linkedList.addAll(f433o);
        }
        if (f437s != null && f435q == 0) {
            synchronized (f437s) {
                bg bgVar = f437s;
                bgVar.f444i += f434p - f437s.f448m;
            }
        }
        for (bg bgVar2 : linkedList) {
            synchronized (bgVar2) {
                if (bgVar2.f445j == C1120a.STARTED) {
                    if (bgVar2.f449n != null && bgVar2.f449n.isCancelled()) {
                        bgVar2.m493a(bgVar2.f440e - TimeUnit.MILLISECONDS.convert(bgVar2.f444i, TimeUnit.NANOSECONDS));
                    } else if (bgVar2.f449n == null) {
                        bgVar2.m493a(bgVar2.f440e);
                    }
                }
            }
        }
    }

    private static boolean m506l() {
        return f434p > f435q;
    }

    public static void m495a(az azVar) {
        f435q = System.nanoTime();
        List<bg> linkedList = new LinkedList();
        synchronized (f433o) {
            linkedList.addAll(f433o);
        }
        for (bg bgVar : linkedList) {
            synchronized (bgVar) {
                if (bgVar.f445j == C1120a.STARTED) {
                    if (bgVar.f448m < f434p) {
                        bgVar.f444i += f435q - f434p;
                    } else if (bgVar.f448m <= f435q) {
                        bgVar.f444i += f435q - bgVar.f448m;
                    }
                }
                bgVar.m512r();
            }
        }
        Object futureTask = new FutureTask(new C11121(linkedList, azVar), null);
        synchronized (f431b) {
            f431b.execute(futureTask);
        }
        try {
            futureTask.get();
        } catch (Throwable e) {
            dx.m777a(e);
        } catch (Throwable e2) {
            dx.m777a(e2);
        }
    }

    public static List m492a(az azVar, boolean z) {
        List linkedList = new LinkedList();
        synchronized (f433o) {
            linkedList.addAll(f433o);
        }
        long currentTimeMillis = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        for (int size = linkedList.size() - 1; size >= 0; size--) {
            bg bgVar = (bg) linkedList.get(size);
            synchronized (bgVar) {
                if (bgVar.f445j == C1120a.STARTED) {
                    bgVar.f443h = currentTimeMillis;
                    bgVar.f445j = C1120a.CRASHED;
                    if (m506l()) {
                        bgVar.f444i = (nanoTime - Math.max(f434p, bgVar.f448m)) + bgVar.f444i;
                    }
                } else {
                    linkedList.remove(size);
                }
                bgVar.m512r();
            }
        }
        Object futureTask = new FutureTask(new C11132(azVar), null);
        synchronized (f431b) {
            f431b.execute(futureTask);
            if (z) {
                azVar.f389z.clear();
            } else {
                f431b.shutdown();
            }
        }
        try {
            futureTask.get();
        } catch (Throwable e) {
            dx.m777a(e);
        } catch (Throwable e2) {
            dx.m777a(e2);
        }
        return linkedList;
    }

    public static void m494a(aw awVar) {
        try {
            bs w = awVar.m381w();
            List<bq> c = w.m580c();
            long currentTimeMillis = System.currentTimeMillis();
            for (bq bqVar : c) {
                JSONArray jSONArray = (JSONArray) ((bz) bqVar).m654a();
                if (jSONArray != null) {
                    try {
                        ch bgVar = new bg(jSONArray);
                        bgVar.f443h = currentTimeMillis;
                        bgVar.f445j = C1120a.ABORTED;
                        awVar.m382x().m577a(bgVar);
                    } catch (Throwable e) {
                        dx.m777a(e);
                    } catch (Throwable e2) {
                        dx.m777a(e2);
                    }
                }
            }
            w.m574a();
        } catch (ThreadDeath e3) {
            throw e3;
        } catch (Throwable e22) {
            dx.m777a(e22);
        }
    }

    public static void m499b(az azVar) {
        try {
            bg bgVar = new bg(azVar, "App Load");
            f437s = bgVar;
            synchronized (bgVar) {
                long m = m507m();
                if (m != -1) {
                    f437s.f445j = C1120a.STARTED;
                    f437s.f442g = System.currentTimeMillis() - (SystemClock.elapsedRealtime() - m);
                    bg bgVar2 = f437s;
                    m = TimeUnit.NANOSECONDS.convert(m, TimeUnit.MILLISECONDS);
                    bgVar2.f448m = System.nanoTime() - (TimeUnit.NANOSECONDS.convert(SystemClock.elapsedRealtime(), TimeUnit.MILLISECONDS) - m);
                    f437s.f440e = f438t.m526a(f437s.f439d);
                    synchronized (f433o) {
                        f433o.add(f437s);
                    }
                    Runnable c11143 = new C11143(azVar, new bg(f437s));
                    synchronized (f431b) {
                        f431b.execute(c11143);
                        f437s.m493a(f437s.f440e);
                    }
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private static long m507m() {
        long[] jArr = new long[1];
        String str = "/proc/" + Process.myPid() + "/stat";
        try {
            return !((Boolean) Process.class.getDeclaredMethod("readProcFile", new Class[]{String.class, int[].class, String[].class, long[].class, float[].class}).invoke(null, new Object[]{str, f436r, null, jArr, null})).booleanValue() ? -1 : jArr[0] * 10;
        } catch (Throwable e) {
            dx.m777a(e);
            return -1;
        } catch (Throwable e2) {
            dx.m777a(e2);
            return -1;
        } catch (Throwable e22) {
            dx.m777a(e22);
            return -1;
        } catch (Throwable e222) {
            dx.m777a(e222);
            return -1;
        }
    }

    public static void m504g() {
        try {
            if (f437s != null) {
                f437s.m518b();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final void m515a() {
        try {
            m508n();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private synchronized void m508n() {
        if (this.f445j == C1120a.CREATED) {
            this.f445j = C1120a.STARTED;
            this.f442g = System.currentTimeMillis();
            this.f448m = System.nanoTime();
            this.f440e = f438t.m526a(this.f439d);
            synchronized (f433o) {
                f433o.add(this);
            }
            Runnable c11154 = new C11154(this, new bg(this));
            synchronized (f431b) {
                f431b.execute(c11154);
                m493a(this.f440e);
            }
        } else {
            dx.m780b("Transaction " + this.f439d + " has already been started.", new IllegalStateException("Transaction has already started"));
        }
    }

    public final void m518b() {
        try {
            m509o();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private synchronized void m509o() {
        m496a(C1120a.SUCCESS);
    }

    public final void m519c() {
        try {
            m510p();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private synchronized void m510p() {
        m496a(C1120a.FAILED);
    }

    public final void m522h() {
        try {
            m511q();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private synchronized void m511q() {
        m496a(C1120a.INTERRUPTED);
    }

    private void m496a(C1120a c1120a) {
        if (!(c1120a == C1120a.SUCCESS || c1120a == C1120a.FAILED)) {
            C1120a c1120a2 = C1120a.INTERRUPTED;
        }
        if (this.f445j == C1120a.STARTED) {
            m512r();
            m500b(c1120a);
        } else if (this.f445j != C1120a.TIMEOUT) {
            dx.m780b("Transaction " + this.f439d + " is not running. Either it has not been started or it has been stopped.", new IllegalStateException("Transaction is not running"));
        }
    }

    public static void m505i() {
        List<bg> linkedList = new LinkedList();
        synchronized (f433o) {
            linkedList.addAll(f433o);
        }
        for (bg bgVar : linkedList) {
            synchronized (bgVar) {
                if (bgVar.f445j == C1120a.STARTED) {
                    bgVar.f440e = f438t.m526a(bgVar.f439d);
                    bgVar.m512r();
                    bgVar.m493a(bgVar.f440e);
                }
            }
        }
    }

    private void m493a(long j) {
        if (m506l()) {
            this.f449n = f432c.schedule(new C11165(this), j, TimeUnit.MILLISECONDS);
        }
    }

    private synchronized void m512r() {
        if (this.f449n != null) {
            this.f449n.cancel(false);
        }
    }

    private synchronized void m513s() {
        if (this.f445j == C1120a.STARTED) {
            m500b(C1120a.TIMEOUT);
        }
    }

    private void m500b(C1120a c1120a) {
        this.f445j = c1120a;
        this.f443h = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        if (m506l()) {
            this.f444i = (nanoTime - Math.max(f434p, this.f448m)) + this.f444i;
        }
        synchronized (f433o) {
            f433o.remove(this);
        }
        Runnable c11186 = new C11186(this, new bg(this));
        synchronized (f431b) {
            f431b.execute(c11186);
        }
    }

    public final void m516a(int i) {
        try {
            m498b(i);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private synchronized void m498b(int i) {
        if (i < 0) {
            dx.m782c("Ignoring Transaction.setValue(int) call. Negative parameter provided.");
        } else if (this.f445j == C1120a.CREATED) {
            this.f441f = i;
        } else if (this.f445j == C1120a.STARTED) {
            this.f441f = i;
            Runnable c11197 = new C11197(this, new bg(this));
            synchronized (f431b) {
                f431b.execute(c11197);
            }
        } else {
            dx.m780b("Transaction " + this.f439d + " no longer in progress. Ignoring setValue(int) call.", new IllegalStateException("Transaction no longer in progress"));
        }
    }

    public final int m520d() {
        try {
            return m514t();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return -1;
        }
    }

    private synchronized int m514t() {
        return this.f441f;
    }

    public final JSONArray m523j() {
        Object obj;
        JSONArray put = new JSONArray().put(this.f439d).put(this.f445j.ordinal()).put(((double) this.f440e) / 1000.0d);
        if (this.f441f == -1) {
            obj = JSONObject.NULL;
        } else {
            obj = Integer.valueOf(this.f441f);
        }
        JSONArray put2 = put.put(obj).put(new JSONObject(this.f446k)).put(ed.f788a.m797a(new Date(this.f442g))).put(ed.f788a.m797a(new Date(this.f443h)));
        if (VERSION.SDK_INT >= 14) {
            put2.put(((double) Math.round((((double) this.f444i) / Math.pow(10.0d, 9.0d)) * 1000.0d)) / 1000.0d);
        } else {
            put2.put(JSONObject.NULL);
        }
        return put2;
    }

    public final String m521e() {
        return this.f447l;
    }

    public final void m517a(OutputStream outputStream) {
        JSONArray jSONArray = null;
        try {
            jSONArray = m523j();
        } catch (JSONException e) {
        }
        if (jSONArray != null) {
            outputStream.write(jSONArray.toString().getBytes());
        }
    }

    public final C1120a m524k() {
        return this.f445j;
    }
}
