package crittercism.android;

import com.crittercism.app.CrittercismConfig;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;

public final class ec {
    aw f783a;
    ExecutorService f784b;
    dg f785c;
    dw f786d;

    /* renamed from: crittercism.android.ec.1 */
    class C11721 implements Runnable {
        final /* synthetic */ Throwable f780a;
        final /* synthetic */ long f781b;
        final /* synthetic */ ec f782c;

        C11721(ec ecVar, Throwable th, long j) {
            this.f782c = ecVar;
            this.f780a = th;
            this.f781b = j;
        }

        public final void run() {
            try {
                if (!this.f782c.f786d.m772b()) {
                    ch bkVar = new bk(this.f780a, this.f781b);
                    bkVar.f478f = "he";
                    try {
                        bkVar.f479g.put("app_version", CrittercismConfig.API_VERSION);
                    } catch (JSONException e) {
                    }
                    bkVar.f479g.remove("logcat");
                    this.f782c.f783a.m374p().m577a(bkVar);
                }
            } catch (ThreadDeath e2) {
            } catch (Throwable th) {
                ec ecVar = this.f782c;
                Throwable th2 = this.f780a;
            }
        }
    }

    public ec(aw awVar, ExecutorService executorService, dg dgVar, dw dwVar) {
        this.f783a = awVar;
        this.f784b = executorService;
        this.f785c = dgVar;
        this.f786d = dwVar;
    }
}
