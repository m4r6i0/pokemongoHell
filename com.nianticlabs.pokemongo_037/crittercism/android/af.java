package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public abstract class af {
    al f299a;
    CharArrayBuffer f300b;
    protected int f301c;
    private int f302d;

    public abstract boolean m278a(CharArrayBuffer charArrayBuffer);

    public abstract af m280b();

    public abstract af m282c();

    protected abstract int m283d();

    protected abstract int m284e();

    public af(al alVar) {
        m273a(alVar, 0);
    }

    public af(af afVar) {
        m273a(afVar.f299a, afVar.f301c);
    }

    private void m273a(al alVar, int i) {
        this.f299a = alVar;
        this.f302d = m284e();
        this.f300b = new CharArrayBuffer(m283d());
        this.f301c = i;
    }

    public boolean m277a(int i) {
        if (i == -1) {
            m274g();
            return true;
        }
        af b;
        this.f301c++;
        char c = (char) i;
        if (c == '\n') {
            if (m278a(this.f300b)) {
                b = m280b();
            } else {
                b = as.f318d;
            }
        } else if (this.f300b.length() < this.f302d) {
            this.f300b.append(c);
            b = this;
        } else {
            b = m282c();
        }
        if (b != this) {
            this.f299a.m323a(b);
        }
        if (b == this) {
            return false;
        }
        return true;
    }

    public final void m276a(byte[] bArr, int i, int i2) {
        int b = m279b(bArr, i, i2);
        while (b > 0 && b < i2) {
            int b2 = this.f299a.m321a().m279b(bArr, i + b, i2 - b);
            if (b2 > 0) {
                b += b2;
            } else {
                return;
            }
        }
    }

    protected int m279b(byte[] bArr, int i, int i2) {
        boolean z = false;
        int i3 = -1;
        if (i2 == -1) {
            m274g();
        } else if (!(bArr == null || i2 == 0)) {
            i3 = 0;
            while (!z && i3 < i2) {
                z = m277a((char) bArr[i + i3]);
                i3++;
            }
        }
        return i3;
    }

    public final int m275a() {
        return this.f301c;
    }

    public final void m281b(int i) {
        this.f301c = i;
    }

    public final String toString() {
        return this.f300b.toString();
    }

    private void m274g() {
        this.f299a.m323a(as.f318d);
    }

    public void m285f() {
        if (this.f299a != null) {
            this.f299a.m323a(as.f318d);
        }
    }
}
