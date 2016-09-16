package crittercism.android;

import java.lang.Thread.UncaughtExceptionHandler;

public final class ay implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler f328a;
    private final az f329b;

    public ay(az azVar, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f329b = azVar;
        this.f328a = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable exception) {
        try {
            this.f329b.m420a(exception);
            if (this.f328a != null && !(this.f328a instanceof ay)) {
                this.f328a.uncaughtException(Thread.currentThread(), exception);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            if (!(this.f328a == null || (this.f328a instanceof ay))) {
                this.f328a.uncaughtException(Thread.currentThread(), exception);
            }
        }
    }
}
