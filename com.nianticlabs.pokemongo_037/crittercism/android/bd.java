package crittercism.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.os.EnvironmentCompat;
import crittercism.android.ce.C1155a;

public final class bd extends BroadcastReceiver {
    private az f402a;
    private String f403b;
    private C1111b f404c;

    public bd(Context context, az azVar) {
        this.f402a = azVar;
        C1161d c1161d = new C1161d(context);
        this.f403b = c1161d.m718b();
        this.f404c = c1161d.m717a();
    }

    public final void onReceive(Context context, Intent intent) {
        new StringBuilder("CrittercismReceiver: INTENT ACTION = ").append(intent.getAction());
        dx.m778b();
        C1161d c1161d = new C1161d(context);
        C1111b a = c1161d.m717a();
        if (!(this.f404c == a || a == C1111b.UNKNOWN)) {
            if (a == C1111b.NOT_CONNECTED) {
                this.f402a.m414a(new ce(C1155a.INTERNET_DOWN));
            } else if (this.f404c == C1111b.NOT_CONNECTED || this.f404c == C1111b.UNKNOWN) {
                this.f402a.m414a(new ce(C1155a.INTERNET_UP));
            }
            this.f404c = a;
        }
        String b = c1161d.m718b();
        if (!b.equals(this.f403b)) {
            if (this.f403b.equals(EnvironmentCompat.MEDIA_UNKNOWN) || this.f403b.equals("disconnected")) {
                if (!(b.equals(EnvironmentCompat.MEDIA_UNKNOWN) || b.equals("disconnected"))) {
                    this.f402a.m414a(new ce(C1155a.CONN_TYPE_GAINED, b));
                }
            } else if (b.equals("disconnected")) {
                this.f402a.m414a(new ce(C1155a.CONN_TYPE_LOST, this.f403b));
            } else if (!b.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                this.f402a.m414a(new ce(C1155a.CONN_TYPE_SWITCHED, this.f403b, b));
            }
            this.f403b = b;
        }
    }
}
