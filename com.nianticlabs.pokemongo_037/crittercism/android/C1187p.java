package crittercism.android;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: crittercism.android.p */
public final class C1187p extends C1184n {

    /* renamed from: crittercism.android.p.1 */
    class C11861 implements Comparator {
        final /* synthetic */ C1187p f843a;

        C11861(C1187p c1187p) {
            this.f843a = c1187p;
        }

        public final /* bridge */ /* synthetic */ int compare(Object x0, Object x1) {
            String str = (String) x0;
            String str2 = (String) x1;
            if (str == str2) {
                return 0;
            }
            if (str == null) {
                return -1;
            }
            return str2 == null ? 1 : String.CASE_INSENSITIVE_ORDER.compare(str, str2);
        }
    }

    public C1187p(Map map) {
        super(map);
        Map treeMap = new TreeMap(new C11861(this));
        treeMap.putAll(map);
        this.f841a = treeMap;
    }
}
