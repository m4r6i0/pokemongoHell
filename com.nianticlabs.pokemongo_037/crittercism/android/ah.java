package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ah extends af {
    private ai f305d;
    private int f306e;
    private int f307f;

    public ah(ai aiVar, int i) {
        super((af) aiVar);
        this.f307f = 0;
        this.f305d = aiVar;
        this.f306e = i;
    }

    public final boolean m294a(int i) {
        if (this.f307f >= this.f306e + 2) {
            return false;
        }
        if (i == -1) {
            this.f299a.m327b(m275a());
            this.f299a.m323a(as.f318d);
            return true;
        }
        this.c++;
        char c = (char) i;
        this.f307f++;
        if (this.f307f <= this.f306e) {
            return false;
        }
        if (c == '\n') {
            this.f305d.m281b(m275a());
            this.f299a.m323a(this.f305d);
            return true;
        } else if (this.f307f != this.f306e + 2 || c == '\n') {
            return false;
        } else {
            this.f299a.m323a(as.f318d);
            return true;
        }
    }

    public final af m296b() {
        return this.f305d;
    }

    public final af m297c() {
        return null;
    }

    public final boolean m295a(CharArrayBuffer charArrayBuffer) {
        return true;
    }

    protected final int m298d() {
        return 0;
    }

    protected final int m299e() {
        return 0;
    }

    public final void m300f() {
        this.f299a.m327b(m275a());
        this.f299a.m323a(as.f318d);
    }
}
