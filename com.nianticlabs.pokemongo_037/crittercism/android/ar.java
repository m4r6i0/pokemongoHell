package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ar extends af {
    private af f317d;

    public ar(af afVar) {
        super(afVar);
        this.f317d = afVar;
    }

    public final boolean m346a(int i) {
        if (i == -1) {
            this.f299a.m323a(as.f318d);
            return true;
        }
        this.c++;
        if (((char) i) != '\n') {
            return false;
        }
        this.f317d.m281b(m275a());
        this.f299a.m323a(this.f317d);
        return true;
    }

    public final af m348b() {
        return this;
    }

    public final af m349c() {
        return this;
    }

    public final boolean m347a(CharArrayBuffer charArrayBuffer) {
        return true;
    }

    protected final int m350d() {
        return 0;
    }

    protected final int m351e() {
        return 0;
    }
}
