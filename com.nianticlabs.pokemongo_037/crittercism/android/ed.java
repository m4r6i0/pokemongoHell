package crittercism.android;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class ed {
    public static final ed f788a;
    private ee f789b;
    private ThreadLocal f790c;

    /* renamed from: crittercism.android.ed.a */
    class C1173a implements ee {
        final /* synthetic */ ed f787a;

        private C1173a(ed edVar) {
            this.f787a = edVar;
        }

        public final Date m793a() {
            return new Date();
        }
    }

    static {
        f788a = new ed();
    }

    private ed() {
        this.f789b = new C1173a();
        this.f790c = new ThreadLocal();
    }

    private SimpleDateFormat m794b() {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) this.f790c.get();
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        simpleDateFormat.setLenient(false);
        this.f790c.set(simpleDateFormat);
        return simpleDateFormat;
    }

    public final String m796a() {
        return m797a(this.f789b.m792a());
    }

    public final String m797a(Date date) {
        return m794b().format(date);
    }

    public final long m795a(String str) {
        return m794b().parse(str).getTime();
    }
}
