package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.voxelbusters.nativeplugins.defines.Keys;
import crittercism.android.dx.C1169a;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public final class ct extends da {
    private au f686a;
    private Context f687b;
    private String f688c;
    private JSONObject f689d;
    private JSONObject f690e;
    private boolean f691f;

    /* renamed from: crittercism.android.ct.a */
    public static class C1159a implements cz {
        public final /* synthetic */ cy m706a(bs bsVar, bs bsVar2, String str, Context context, au auVar) {
            return new ct(bsVar, bsVar2, str, context, auVar);
        }
    }

    public ct(bs bsVar, bs bsVar2, String str, Context context, au auVar) {
        super(bsVar, bsVar2);
        this.f688c = str;
        this.f687b = context;
        this.f686a = auVar;
    }

    public final void m709a(boolean z, int i, JSONObject jSONObject) {
        super.m708a(z, i, jSONObject);
        if (jSONObject != null) {
            Editor edit;
            if (jSONObject.optBoolean("internalExceptionReporting", false)) {
                dx.f761a = C1169a.ON;
                C1178i.m811d();
            } else {
                dx.f761a = C1169a.OFF;
            }
            dt m = this.f686a.m371m();
            if (m != null) {
                JSONObject optJSONObject = jSONObject.optJSONObject("rateMyApp");
                if (optJSONObject == null) {
                    m.m759a(false);
                } else {
                    try {
                        int i2 = optJSONObject.getInt("rateAfterLoadNum");
                        if (i2 < 0) {
                            i2 = 0;
                        }
                        m.f752a.edit().putInt("rateAfterNumLoads", i2).commit();
                        i2 = optJSONObject.getInt("remindAfterLoadNum");
                        if (i2 <= 0) {
                            i2 = 1;
                        }
                        m.f752a.edit().putInt("remindAfterNumLoads", i2).commit();
                        m.f752a.edit().putString("rateAppMessage", optJSONObject.getString(Keys.MESSAGE)).commit();
                        m.f752a.edit().putString("rateAppTitle", optJSONObject.getString(Keys.TITLE)).commit();
                        m.m759a(true);
                    } catch (JSONException e) {
                        m.m759a(false);
                    }
                }
            }
            if (jSONObject.optInt("needPkg", 0) == 1) {
                try {
                    new dj(new cu(this.f686a).m711a("device_name", this.f686a.m367i()).m711a("pkg", this.f687b.getPackageName()), new dc(new db(this.f688c, "/android_v2/update_package_name").m720a()), null).run();
                } catch (IOException e2) {
                    new StringBuilder("IOException in handleResponse(): ").append(e2.getMessage());
                    dx.m778b();
                    dx.m781c();
                }
                this.f691f = true;
            }
            this.f689d = jSONObject.optJSONObject("apm");
            if (this.f689d != null) {
                C1175h c1175h = new C1175h(this.f689d);
                Context context = this.f687b;
                if (c1175h.f803c) {
                    C1175h.m806b(context);
                } else {
                    File a = C1175h.m805a(context);
                    if (!a.delete() && a.exists()) {
                        dx.m779b("Unable to reenable OPTMZ instrumentation");
                    }
                }
                edit = context.getSharedPreferences("com.crittercism.optmz.config", 0).edit();
                if (c1175h.f802b) {
                    edit.putBoolean("enabled", c1175h.f801a);
                    edit.putBoolean("kill", c1175h.f803c);
                    edit.putBoolean("persist", c1175h.f802b);
                    edit.putInt("interval", c1175h.f804d);
                } else {
                    edit.clear();
                }
                edit.commit();
                az.m400A().m415a(c1175h);
            }
            this.f690e = jSONObject.optJSONObject("txnConfig");
            if (this.f690e != null) {
                bh bhVar = new bh(this.f690e);
                edit = this.f687b.getSharedPreferences("com.crittercism.txn.config", 0).edit();
                edit.putBoolean("enabled", bhVar.f450a);
                edit.putInt("interval", bhVar.f451b);
                edit.putInt("defaultTimeout", bhVar.f452c);
                edit.putString("transactions", bhVar.f453d.toString());
                edit.commit();
                az.m400A().m412a(bhVar);
            }
        }
    }
}
