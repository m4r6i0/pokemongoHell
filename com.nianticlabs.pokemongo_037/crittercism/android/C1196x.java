package crittercism.android;

import crittercism.android.C1153c.C1152a;
import java.io.InputStream;

/* renamed from: crittercism.android.x */
public final class C1196x extends InputStream implements al {
    private ae f875a;
    private C1153c f876b;
    private InputStream f877c;
    private C1171e f878d;
    private af f879e;

    public C1196x(ae aeVar, InputStream inputStream, C1171e c1171e) {
        if (aeVar == null) {
            throw new NullPointerException("socket was null");
        } else if (inputStream == null) {
            throw new NullPointerException("delegate was null");
        } else if (c1171e == null) {
            throw new NullPointerException("dispatch was null");
        } else {
            this.f875a = aeVar;
            this.f877c = inputStream;
            this.f878d = c1171e;
            this.f879e = m867b();
            if (this.f879e == null) {
                throw new NullPointerException("parser was null");
            }
        }
    }

    public final int available() {
        return this.f877c.available();
    }

    public final void close() {
        try {
            this.f879e.m285f();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
        this.f877c.close();
    }

    public final void mark(int readlimit) {
        this.f877c.mark(readlimit);
    }

    public final boolean markSupported() {
        return this.f877c.markSupported();
    }

    private void m858a(Exception exception) {
        try {
            C1153c e = m860e();
            e.m663a((Throwable) exception);
            this.f878d.m786a(e, C1152a.PARSING_INPUT_STREAM_LOG_ERROR);
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (IllegalStateException e3) {
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final int read() {
        try {
            int read = this.f877c.read();
            try {
                this.f879e.m277a(read);
            } catch (ThreadDeath e) {
                throw e;
            } catch (IllegalStateException e2) {
                this.f879e = as.f318d;
            } catch (Throwable th) {
                this.f879e = as.f318d;
                dx.m777a(th);
            }
            return read;
        } catch (Exception e3) {
            m858a(e3);
            throw e3;
        }
    }

    public final int read(byte[] buffer) {
        try {
            int read = this.f877c.read(buffer);
            m859a(buffer, 0, read);
            return read;
        } catch (Exception e) {
            m858a(e);
            throw e;
        }
    }

    public final int read(byte[] buffer, int offset, int length) {
        try {
            int read = this.f877c.read(buffer, offset, length);
            m859a(buffer, offset, read);
            return read;
        } catch (Exception e) {
            m858a(e);
            throw e;
        }
    }

    private void m859a(byte[] bArr, int i, int i2) {
        try {
            this.f879e.m276a(bArr, i, i2);
        } catch (ThreadDeath e) {
            throw e;
        } catch (IllegalStateException e2) {
            this.f879e = as.f318d;
        } catch (Throwable th) {
            this.f879e = as.f318d;
            dx.m777a(th);
        }
    }

    public final synchronized void reset() {
        this.f877c.reset();
    }

    public final long skip(long byteCount) {
        return this.f877c.skip(byteCount);
    }

    public final void m865a(String str, String str2) {
    }

    public final void m862a(int i) {
        C1153c e = m860e();
        e.m668c();
        e.f580e = i;
    }

    public final void m863a(af afVar) {
        this.f879e = afVar;
    }

    public final af m861a() {
        return this.f879e;
    }

    public final void m868b(int i) {
        C1153c c1153c = null;
        C1153c c1153c2 = this.f876b;
        if (this.f876b != null) {
            int i2 = this.f876b.f580e;
            if (i2 >= 100 && i2 < 200) {
                c1153c = new C1153c(this.f876b.m656a());
                c1153c.m673e(this.f876b.f576a);
                c1153c.m671d(this.f876b.f579d);
                c1153c.f581f = this.f876b.f581f;
            }
            this.f876b.m666b((long) i);
            this.f878d.m786a(this.f876b, C1152a.INPUT_STREAM_FINISHED);
        }
        this.f876b = c1153c;
    }

    private C1153c m860e() {
        if (this.f876b == null) {
            this.f876b = this.f875a.m255b();
        }
        if (this.f876b != null) {
            return this.f876b;
        }
        throw new IllegalStateException("No statistics were queued up.");
    }

    public final af m867b() {
        return new ap(this);
    }

    public final String m869c() {
        return m860e().f581f;
    }

    public final void m864a(String str) {
    }

    public final boolean m866a(InputStream inputStream) {
        return this.f877c == inputStream;
    }

    public final void m870d() {
        if (this.f876b != null) {
            cn cnVar = this.f876b.f582g;
            Object obj = (cnVar.f655a == co.Android.ordinal() && cnVar.f656b == cm.OK.m691a()) ? 1 : null;
            if (obj != null && this.f879e != null) {
                this.f879e.m285f();
            }
        }
    }
}
