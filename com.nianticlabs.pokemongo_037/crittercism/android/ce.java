package crittercism.android;

import com.voxelbusters.nativeplugins.defines.Keys;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ce extends ci {
    private String f606a;
    private String f607b;
    private C1155a f608c;
    private String f609d;
    private String f610e;
    private String f611f;

    /* renamed from: crittercism.android.ce.a */
    public enum C1155a {
        INTERNET_UP,
        INTERNET_DOWN,
        CONN_TYPE_GAINED,
        CONN_TYPE_LOST,
        CONN_TYPE_SWITCHED
    }

    public ce(C1155a c1155a) {
        if (c1155a != C1155a.INTERNET_UP) {
            C1155a c1155a2 = C1155a.INTERNET_DOWN;
        }
        this.f606a = cg.f620a.m688a();
        this.f607b = ed.f788a.m796a();
        this.f608c = c1155a;
    }

    public ce(C1155a c1155a, String str) {
        if (c1155a != C1155a.CONN_TYPE_GAINED) {
            C1155a c1155a2 = C1155a.CONN_TYPE_LOST;
        }
        this.f606a = cg.f620a.m688a();
        this.f607b = ed.f788a.m796a();
        this.f608c = c1155a;
        this.f609d = str;
    }

    public ce(C1155a c1155a, String str, String str2) {
        C1155a c1155a2 = C1155a.CONN_TYPE_SWITCHED;
        this.f606a = cg.f620a.m688a();
        this.f607b = ed.f788a.m796a();
        this.f608c = c1155a;
        this.f610e = str;
        this.f611f = str2;
    }

    public final String m684e() {
        return this.f606a;
    }

    public final JSONArray m683a() {
        Map hashMap = new HashMap();
        hashMap.put("change", Integer.valueOf(this.f608c.ordinal()));
        if (this.f608c == C1155a.CONN_TYPE_GAINED || this.f608c == C1155a.CONN_TYPE_LOST) {
            hashMap.put(Keys.TYPE, this.f609d);
        } else if (this.f608c == C1155a.CONN_TYPE_SWITCHED) {
            hashMap.put("oldType", this.f610e);
            hashMap.put("newType", this.f611f);
        }
        return new JSONArray().put(this.f607b).put(4).put(new JSONObject(hashMap));
    }
}
