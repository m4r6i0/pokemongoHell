package com.unity3d.player;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Handler;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import com.google.android.gms.location.LocationStatusCodes;

/* renamed from: com.unity3d.player.d */
public final class C0860d implements C0859f {
    private static final SurfaceTexture f165a;
    private static final int f166b;
    private volatile boolean f167c;

    /* renamed from: com.unity3d.player.d.1 */
    class C08571 implements OnSystemUiVisibilityChangeListener {
        final /* synthetic */ View f161a;
        final /* synthetic */ C0860d f162b;

        C08571(C0860d c0860d, View view) {
            this.f162b = c0860d;
            this.f161a = view;
        }

        public final void onSystemUiVisibilityChange(int i) {
            this.f162b.m129a(this.f161a, (int) LocationStatusCodes.GEOFENCE_NOT_AVAILABLE);
        }
    }

    /* renamed from: com.unity3d.player.d.2 */
    class C08582 implements Runnable {
        final /* synthetic */ View f163a;
        final /* synthetic */ C0860d f164b;

        C08582(C0860d c0860d, View view) {
            this.f164b = c0860d;
            this.f163a = view;
        }

        public final void run() {
            this.f164b.m133a(this.f163a, this.f164b.f167c);
        }
    }

    static {
        f165a = new SurfaceTexture(-1);
        f166b = C0878q.f200f ? 5894 : 1;
    }

    private void m129a(View view, int i) {
        Handler handler = view.getHandler();
        if (handler == null) {
            m133a(view, this.f167c);
        } else {
            handler.postDelayed(new C08582(this, view), 1000);
        }
    }

    public final void m132a(View view) {
        if (!C0878q.f201g) {
            view.setOnSystemUiVisibilityChangeListener(new C08571(this, view));
        }
    }

    public final void m133a(View view, boolean z) {
        this.f167c = z;
        view.setSystemUiVisibility(this.f167c ? view.getSystemUiVisibility() | f166b : view.getSystemUiVisibility() & (f166b ^ -1));
    }

    public final boolean m134a(Camera camera) {
        try {
            camera.setPreviewTexture(f165a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public final void m135b(View view) {
        if (!C0878q.f200f && this.f167c) {
            m133a(view, false);
            this.f167c = true;
        }
        m129a(view, (int) LocationStatusCodes.GEOFENCE_NOT_AVAILABLE);
    }
}
