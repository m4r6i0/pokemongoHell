package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ag extends af {
    private int f303d;
    private int f304e;

    public ag(af afVar, int i) {
        super(afVar);
        this.f304e = 0;
        this.f303d = i;
    }

    public final boolean m286a(int i) {
        if (i == -1) {
            this.f299a.m323a(as.f318d);
            return true;
        }
        this.f304e++;
        this.c++;
        if (this.f304e != this.f303d) {
            return false;
        }
        this.f299a.m327b(m275a());
        this.f299a.m323a(this.f299a.m326b());
        return true;
    }

    public final int m288b(byte[] bArr, int i, int i2) {
        if (i2 == -1) {
            this.f299a.m323a(as.f318d);
            return -1;
        } else if (this.f304e + i2 < this.f303d) {
            this.f304e += i2;
            this.c += i2;
            return i2;
        } else {
            i2 = this.f303d - this.f304e;
            this.c += i2;
            this.f299a.m327b(m275a());
            this.f299a.m323a(this.f299a.m326b());
            return i2;
        }
    }

    public final af m289b() {
        return as.f318d;
    }

    public final af m290c() {
        return as.f318d;
    }

    public final boolean m287a(CharArrayBuffer charArrayBuffer) {
        return true;
    }

    protected final int m291d() {
        return 0;
    }

    protected final int m292e() {
        return 0;
    }

    public final void m293f() {
        this.f299a.m327b(m275a());
        this.f299a.m323a(as.f318d);
    }
}
