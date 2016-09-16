package crittercism.android;

import android.util.SparseArray;

/* renamed from: crittercism.android.b */
public enum C1111b {
    MOBILE(0),
    WIFI(1),
    UNKNOWN(2),
    NOT_CONNECTED(3);
    
    private static SparseArray f394e;
    private int f396f;

    static {
        SparseArray sparseArray = new SparseArray();
        f394e = sparseArray;
        sparseArray.put(0, MOBILE);
        f394e.put(1, WIFI);
    }

    private C1111b(int i) {
        this.f396f = i;
    }

    public final int m452a() {
        return this.f396f;
    }

    public static C1111b m451a(int i) {
        C1111b c1111b = (C1111b) f394e.get(i);
        if (c1111b == null) {
            return UNKNOWN;
        }
        return c1111b;
    }
}
