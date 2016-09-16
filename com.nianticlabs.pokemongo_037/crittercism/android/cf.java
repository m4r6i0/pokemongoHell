package crittercism.android;

import java.io.OutputStream;
import org.json.JSONArray;

public final class cf extends bp {
    public static final cf f615a;
    private String f616b;
    private String f617c;
    private String f618d;
    private C1156a f619e;

    /* renamed from: crittercism.android.cf.a */
    public enum C1156a {
        NORMAL,
        URGENT
    }

    static {
        f615a = new cf("session_start", C1156a.NORMAL);
    }

    public cf(String str, C1156a c1156a) {
        this(str, ed.f788a.m796a(), c1156a);
    }

    private cf(String str, String str2, C1156a c1156a) {
        this.f618d = cg.f620a.m688a();
        if (str.length() > 140) {
            str = str.substring(0, 140);
        }
        this.f616b = str;
        this.f617c = str2;
        this.f619e = c1156a;
    }

    public final void m685a(OutputStream outputStream) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.f616b);
        jSONArray.put(this.f617c);
        String jSONArray2 = jSONArray.toString();
        new StringBuilder("BREADCRUMB WRITING ").append(jSONArray2);
        dx.m778b();
        outputStream.write(jSONArray2.getBytes());
    }

    public final String m686e() {
        return this.f618d;
    }
}
