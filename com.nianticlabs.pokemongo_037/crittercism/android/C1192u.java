package crittercism.android;

import java.io.OutputStream;

/* renamed from: crittercism.android.u */
public final class C1192u extends OutputStream {
    private final OutputStream f860a;
    private final C1153c f861b;

    public C1192u(OutputStream outputStream, C1153c c1153c) {
        if (outputStream == null) {
            throw new NullPointerException("delegate was null");
        } else if (c1153c == null) {
            throw new NullPointerException("stats were null");
        } else {
            this.f860a = outputStream;
            this.f861b = c1153c;
        }
    }

    public final void flush() {
        this.f860a.flush();
    }

    public final void close() {
        this.f860a.close();
    }

    public final void write(int oneByte) {
        try {
            if (this.f861b != null) {
                this.f861b.m665b();
                this.f861b.m669c(1);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
        this.f860a.write(oneByte);
    }

    public final void write(byte[] buffer) {
        if (this.f861b != null) {
            this.f861b.m665b();
            if (buffer != null) {
                this.f861b.m669c((long) buffer.length);
            }
        }
        this.f860a.write(buffer);
    }

    public final void write(byte[] buffer, int offset, int byteCount) {
        if (this.f861b != null) {
            this.f861b.m665b();
            if (buffer != null) {
                this.f861b.m669c((long) byteCount);
            }
        }
        this.f860a.write(buffer, offset, byteCount);
    }
}
