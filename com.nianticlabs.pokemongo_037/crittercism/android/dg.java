package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import com.crittercism.app.CrittercismNDK;
import crittercism.android.cs.C1158b;
import crittercism.android.ct.C1159a;
import crittercism.android.cu.C1160a;
import crittercism.android.da.C1162a;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class dg extends di {
    public ConditionVariable f711a;
    public bm f712b;
    private ConditionVariable f713c;
    private bb f714d;
    private Context f715e;
    private aw f716f;
    private ax f717g;
    private au f718h;
    private List f719i;
    private boolean f720j;
    private boolean f721k;
    private Exception f722l;

    public dg(bb bbVar, Context context, aw awVar, ax axVar, au auVar) {
        this.f713c = new ConditionVariable();
        this.f711a = new ConditionVariable();
        this.f719i = new ArrayList();
        this.f720j = false;
        this.f712b = null;
        this.f722l = null;
        this.f714d = bbVar;
        this.f715e = context;
        this.f716f = awVar;
        this.f717g = axVar;
        this.f718h = auVar;
        this.f721k = false;
    }

    private synchronized void m728c() {
        this.f720j = true;
    }

    private synchronized boolean m729d() {
        return this.f720j;
    }

    public final synchronized boolean m733a(Runnable runnable) {
        boolean z;
        if (m729d()) {
            z = false;
        } else {
            this.f719i.add(runnable);
            z = true;
        }
        return z;
    }

    private File m730e() {
        int i = 0;
        File file = new File(this.f715e.getFilesDir().getAbsolutePath() + "/" + this.f714d.m472g());
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        if (listFiles.length == 1) {
            File file2 = listFiles[0];
            file2.isFile();
            if (file2.isFile()) {
                return file2;
            }
            return null;
        } else if (listFiles.length <= 1) {
            return null;
        } else {
            int length = listFiles.length;
            while (i < length) {
                File file3 = listFiles[i];
                file3.isFile();
                file3.delete();
                i++;
            }
            return null;
        }
    }

    private void m727a(File file) {
        boolean z = this.f721k;
        az A = az.m400A();
        if (!A.f383t) {
            if (file != null && file.exists()) {
                file.isFile();
            }
            aw awVar = this.f716f;
            bs s = this.f716f.m377s();
            bs t = this.f716f.m378t();
            bs u = this.f716f.m379u();
            bs v = this.f716f.m380v();
            bs q = this.f716f.m375q();
            if (file != null) {
                dq.f746a = true;
                A.f368e.open();
                q.m577a(new cd(file, s, u, t, v));
                file.delete();
                this.f716f.m381w().m574a();
            } else {
                A.f368e.open();
                bg.m494a(this.f716f);
            }
            u.m574a();
            t.m574a();
            v.m574a();
            s.m575a(u);
        }
    }

    private void m731f() {
        if (!az.m400A().f383t) {
            boolean z = this.f721k;
            bs n = this.f716f.m372n();
            bs o = this.f716f.m373o();
            bs p = this.f716f.m374p();
            bs q = this.f716f.m375q();
            bs r = this.f716f.m376r();
            dv y = this.f716f.m383y();
            this.f714d.m467b();
            this.f712b = new bm(this.f718h);
            if (!this.f714d.delaySendingAppLoad()) {
                n.m577a(this.f712b);
                df dfVar = new df(this.f715e);
                dfVar.m725a(n, new C1159a(), this.f714d.m470e(), "/v0/appload", this.f714d.m467b(), this.f718h, new C1158b());
                dfVar.m725a(o, new C1162a(), this.f714d.m467b(), "/android_v2/handle_exceptions", null, this.f718h, new C1160a());
                dfVar.m725a(q, new C1162a(), this.f714d.m467b(), "/android_v2/handle_ndk_crashes", null, this.f718h, new C1160a());
                dfVar.m725a(r, new C1162a(), this.f714d.m467b(), "/android_v2/handle_crashes", null, this.f718h, new C1160a());
                dfVar.m725a(p, new C1162a(), this.f714d.m467b(), "/android_v2/handle_exceptions", null, new ba(this.f718h, this.f714d), new C1160a());
                dfVar.m724a();
            }
            if (y.m769b()) {
                az.m400A().m407E();
            }
        }
    }

    public final void m732a() {
        dx.m778b();
        File file = new File(this.f715e.getFilesDir().getAbsolutePath() + "/com.crittercism/pending");
        if (!file.exists() || file.isDirectory()) {
            try {
                eb.m790a(file);
            } catch (Exception e) {
                new StringBuilder("Exception in run(): ").append(e.getMessage());
                dx.m778b();
                dx.m781c();
                this.f722l = e;
            } finally {
                this.f713c.open();
            }
        } else {
            dx.m778b();
        }
        az A = az.m400A();
        A.f386w.m755a();
        dw l = this.f718h.m370l();
        A.f367d.open();
        ax axVar = this.f717g;
        Context context = this.f715e;
        l.m771a(axVar);
        dq.f746a = dq.m751a(this.f715e).booleanValue();
        dq.m752a(this.f715e, false);
        if (!l.m772b()) {
            dt dtVar = new dt(this.f715e);
            dtVar.f752a.edit().putInt("numAppLoads", dtVar.m758a() + 1).commit();
            az.m400A().f355A = dtVar;
            l.m770a().m763a(this.f717g, cq.SESSION_ID_SETTING.m693a(), cq.SESSION_ID_SETTING.m694b());
        }
        this.f721k = l.m772b();
        File e2 = m730e();
        boolean z;
        if (this.f721k) {
            z = this.f721k;
            if (!az.m400A().f383t) {
                if (e2 != null && e2.exists()) {
                    e2.isFile();
                }
                new bs(this.f715e, br.APP_LOADS).m574a();
                new bs(this.f715e, br.HAND_EXCS).m574a();
                new bs(this.f715e, br.INTERNAL_EXCS).m574a();
                new bs(this.f715e, br.NDK_CRASHES).m574a();
                new bs(this.f715e, br.SDK_CRASHES).m574a();
                new bs(this.f715e, br.CURR_BCS).m574a();
                new bs(this.f715e, br.PREV_BCS).m574a();
                new bs(this.f715e, br.NW_BCS).m574a();
                new bs(this.f715e, br.SYSTEM_BCS).m574a();
                if (e2 != null) {
                    e2.delete();
                }
            }
            C1175h.m806b(this.f715e);
        } else {
            Context context2 = this.f715e;
            C1175h c1175h = new C1175h(context2);
            SharedPreferences sharedPreferences = context2.getSharedPreferences("com.crittercism.optmz.config", 0);
            if (sharedPreferences.contains("interval")) {
                c1175h.f804d = sharedPreferences.getInt("interval", 10);
                if (sharedPreferences.contains("kill")) {
                    c1175h.f803c = sharedPreferences.getBoolean("kill", false);
                    if (sharedPreferences.contains("persist")) {
                        c1175h.f802b = sharedPreferences.getBoolean("persist", false);
                        if (sharedPreferences.contains("enabled")) {
                            c1175h.f801a = sharedPreferences.getBoolean("enabled", false);
                        } else {
                            c1175h = null;
                        }
                    } else {
                        c1175h = null;
                    }
                } else {
                    c1175h = null;
                }
            } else {
                c1175h = null;
            }
            if (c1175h != null) {
                az.m400A().m415a(c1175h);
            }
            z = this.f721k;
            this.f716f.m384z();
            if (!az.m400A().f383t) {
                bh a = bh.m525a(this.f715e);
                try {
                    Runnable biVar = new bi(this.f715e, this.f718h, this.f716f.m382x(), this.f716f.m377s(), this.f716f.m378t(), this.f716f.m380v(), new URL(this.f714d.m469d() + "/api/v1/transactions"));
                    az A2 = az.m400A();
                    A2.f388y = biVar;
                    new dy(biVar, "TXN Thread").start();
                    A2.m412a(a);
                } catch (MalformedURLException e3) {
                    dx.m773a();
                }
            }
            m727a(e2);
            this.f711a.open();
            this.f716f.m377s().m577a(cf.f615a);
            if (!az.m400A().f383t && this.f714d.isNdkCrashReportingEnabled()) {
                dx.m778b();
                try {
                    CrittercismNDK.installNdkLib(this.f715e, this.f714d.m472g());
                } catch (Throwable th) {
                    new StringBuilder("Exception installing ndk library: ").append(th.getClass().getName());
                    dx.m778b();
                }
            }
            m731f();
        }
        m728c();
        for (Runnable biVar2 : this.f719i) {
            biVar2.run();
        }
    }

    public final void m734b() {
        this.f713c.block();
    }
}
