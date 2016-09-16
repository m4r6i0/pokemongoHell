package crittercism.android;

import com.voxelbusters.nativeplugins.defines.Keys.Twitter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class by extends ci {
    private String f558a;
    private String f559b;
    private String f560c;
    private String f561d;

    public by(String str, String str2) {
        this.f558a = cg.f620a.m688a();
        this.f559b = ed.f788a.m796a();
        this.f560c = str;
        this.f561d = str2;
    }

    public final String m651e() {
        return this.f558a;
    }

    public final JSONArray m650a() {
        Map hashMap = new HashMap();
        hashMap.put(Twitter.NAME, this.f560c);
        hashMap.put("reason", this.f561d);
        return new JSONArray().put(this.f559b).put(6).put(new JSONObject(hashMap));
    }
}
