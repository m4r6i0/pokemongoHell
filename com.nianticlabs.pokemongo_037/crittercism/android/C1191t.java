package crittercism.android;

import java.io.InputStream;

/* renamed from: crittercism.android.t */
public final class C1191t extends InputStream {
    private final InputStream f857a;
    private final C1171e f858b;
    private final C1153c f859c;

    public C1191t(InputStream inputStream, C1171e c1171e, C1153c c1153c) {
        if (inputStream == null) {
            throw new NullPointerException("delegate was null");
        } else if (c1171e == null) {
            throw new NullPointerException("dispatch was null");
        } else if (c1153c == null) {
            throw new NullPointerException("stats were null");
        } else {
            this.f857a = inputStream;
            this.f858b = c1171e;
            this.f859c = c1153c;
        }
    }

    public final int available() {
        return this.f857a.available();
    }

    public final void close() {
        this.f857a.close();
    }

    public final void mark(int readlimit) {
        this.f857a.mark(readlimit);
    }

    public final boolean markSupported() {
        return this.f857a.markSupported();
    }

    private void m839a(int i, int i2) {
        try {
            if (this.f859c == null) {
                return;
            }
            if (i == -1) {
                this.f858b.m785a(this.f859c);
            } else {
                this.f859c.m658a((long) i2);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private void m840a(Exception exception) {
        try {
            this.f859c.m663a((Throwable) exception);
            this.f858b.m785a(this.f859c);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public final int read() {
        try {
            int read = this.f857a.read();
            m839a(read, 1);
            return read;
        } catch (Exception e) {
            m840a(e);
            throw e;
        }
    }

    public final int read(byte[] buffer) {
        try {
            int read = this.f857a.read(buffer);
            m839a(read, read);
            return read;
        } catch (Exception e) {
            m840a(e);
            throw e;
        }
    }

    public final int read(byte[] buffer, int offset, int length) {
        try {
            int read = this.f857a.read(buffer, offset, length);
            m839a(read, read);
            return read;
        } catch (Exception e) {
            m840a(e);
            throw e;
        }
    }

    public final synchronized void reset() {
        this.f857a.reset();
    }

    public final long skip(long byteCount) {
        long skip = this.f857a.skip(byteCount);
        try {
            if (this.f859c != null) {
                this.f859c.m658a(skip);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
        return skip;
    }
}
