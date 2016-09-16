package crittercism.android;

import java.io.OutputStream;

/* renamed from: crittercism.android.w */
public final class C1195w extends OutputStream implements al {
    private ae f871a;
    private OutputStream f872b;
    private C1153c f873c;
    private af f874d;

    public C1195w(ae aeVar, OutputStream outputStream) {
        if (aeVar == null) {
            throw new NullPointerException("socket was null");
        } else if (outputStream == null) {
            throw new NullPointerException("output stream was null");
        } else {
            this.f871a = aeVar;
            this.f872b = outputStream;
            this.f874d = m855b();
            if (this.f874d == null) {
                throw new NullPointerException("parser was null");
            }
        }
    }

    public final void flush() {
        this.f872b.flush();
    }

    public final void close() {
        this.f872b.close();
    }

    public final void write(int oneByte) {
        this.f872b.write(oneByte);
        try {
            this.f874d.m277a(oneByte);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            this.f874d = as.f318d;
        }
    }

    public final void write(byte[] buffer) {
        this.f872b.write(buffer);
        if (buffer != null) {
            m847a(buffer, 0, buffer.length);
        }
    }

    public final void write(byte[] buffer, int offset, int byteCount) {
        this.f872b.write(buffer, offset, byteCount);
        if (buffer != null) {
            m847a(buffer, offset, byteCount);
        }
    }

    private void m847a(byte[] bArr, int i, int i2) {
        try {
            this.f874d.m276a(bArr, i, i2);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            this.f874d = as.f318d;
        }
    }

    public final void m853a(String str, String str2) {
        C1153c d = m848d();
        d.m665b();
        d.f581f = str;
        d.f584i = null;
        C1181k c1181k = d.f583h;
        if (str2 != null) {
            c1181k.f830c = str2;
        }
        this.f871a.m254a(d);
    }

    public final void m850a(int i) {
    }

    public final void m851a(af afVar) {
        this.f874d = afVar;
    }

    public final af m849a() {
        return this.f874d;
    }

    public final void m856b(int i) {
        C1153c c1153c = this.f873c;
        this.f873c = null;
        if (c1153c != null) {
            c1153c.m671d((long) i);
        }
    }

    private C1153c m848d() {
        if (this.f873c == null) {
            this.f873c = this.f871a.m253a();
        }
        C1153c c1153c = this.f873c;
        return this.f873c;
    }

    public final af m855b() {
        return new an(this);
    }

    public final String m857c() {
        C1153c d = m848d();
        if (d != null) {
            return d.f581f;
        }
        return null;
    }

    public final void m852a(String str) {
        C1153c d = m848d();
        if (d != null) {
            d.m667b(str);
        }
    }

    public final boolean m854a(OutputStream outputStream) {
        return this.f872b == outputStream;
    }
}
