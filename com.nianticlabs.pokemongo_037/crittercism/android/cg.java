package crittercism.android;

import java.util.Locale;

public final class cg {
    public static final cg f620a;
    private volatile int f621b;
    private final long f622c;

    static {
        f620a = new cg();
    }

    private cg() {
        this.f621b = 1;
        this.f622c = System.currentTimeMillis();
    }

    private synchronized int m687b() {
        int i;
        i = this.f621b;
        this.f621b = i + 1;
        return i;
    }

    public final String m688a() {
        return String.format(Locale.US, "%d.%d.%09d", new Object[]{Integer.valueOf(1), Long.valueOf(this.f622c), Integer.valueOf(m687b())});
    }
}
