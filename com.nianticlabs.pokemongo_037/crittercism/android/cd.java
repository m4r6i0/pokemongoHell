package crittercism.android;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import crittercism.android.bx.C1126b;
import crittercism.android.bx.C1127c;
import crittercism.android.bx.C1128d;
import crittercism.android.bx.C1130f;
import crittercism.android.bx.C1132h;
import crittercism.android.bx.C1134j;
import crittercism.android.bx.C1135k;
import crittercism.android.bx.C1137m;
import crittercism.android.bx.C1139o;
import crittercism.android.bx.C1140p;
import crittercism.android.bx.C1142r;
import crittercism.android.bx.C1143s;
import crittercism.android.bx.C1147w;
import crittercism.android.bx.C1148x;
import crittercism.android.bx.C1150z;
import crittercism.android.bx.aa;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class cd implements ch {
    private JSONObject f594a;
    private JSONObject f595b;
    private JSONArray f596c;
    private JSONArray f597d;
    private File f598e;
    private String f599f;

    public cd(File file, bs bsVar, bs bsVar2, bs bsVar3, bs bsVar4) {
        file.exists();
        this.f599f = cg.f620a.m688a();
        this.f598e = file;
        this.f594a = new bu().m582a(new C1127c()).m582a(new C1126b()).m582a(new C1128d()).m582a(new C1130f()).m582a(new C1139o()).m582a(new C1140p()).m582a(new C1134j()).m582a(new C1132h()).m582a(new C1150z()).m582a(new aa()).m582a(new C1135k()).m582a(new C1142r()).m582a(new C1137m()).m582a(new C1143s()).m582a(new C1147w()).m582a(new C1148x()).m583a();
        Map hashMap = new HashMap();
        hashMap.put("crashed_session", new bo(bsVar).f503a);
        if (bsVar2.m578b() > 0) {
            hashMap.put("previous_session", new bo(bsVar2).f503a);
        }
        this.f595b = new JSONObject(hashMap);
        this.f596c = new bo(bsVar3).f503a;
        this.f597d = new bo(bsVar4).f503a;
    }

    public final void m681a(OutputStream outputStream) {
        Map hashMap = new HashMap();
        hashMap.put("app_state", this.f594a);
        hashMap.put("breadcrumbs", this.f595b);
        hashMap.put("endpoints", this.f596c);
        hashMap.put("systemBreadcrumbs", this.f597d);
        Object obj = new byte[0];
        Object obj2 = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
        InputStream fileInputStream = new FileInputStream(this.f598e);
        while (true) {
            int read = fileInputStream.read(obj2);
            if (read != -1) {
                Object obj3 = new byte[(obj.length + read)];
                System.arraycopy(obj, 0, obj3, 0, obj.length);
                System.arraycopy(obj2, 0, obj3, obj.length, read);
                obj = obj3;
            } else {
                fileInputStream.close();
                Map hashMap2 = new HashMap();
                hashMap2.put("dmp_name", this.f598e.getName());
                hashMap2.put("dmp_file", cr.m695a(obj));
                hashMap.put("ndk_dmp_info", new JSONObject(hashMap2));
                outputStream.write(new JSONObject(hashMap).toString().getBytes());
                return;
            }
        }
    }

    public final String m682e() {
        return this.f599f;
    }
}
