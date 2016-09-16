package com.unity3d.player;

import android.app.Activity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/* renamed from: com.unity3d.player.b */
abstract class C0850b implements Callback {
    private final Activity f143a;
    private final int f144b;
    private SurfaceView f145c;

    /* renamed from: com.unity3d.player.b.1 */
    class C08531 implements Runnable {
        final /* synthetic */ C0850b f159a;

        C08531(C0850b c0850b) {
            this.f159a = c0850b;
        }

        public final void run() {
            if (this.f159a.f145c == null) {
                this.f159a.f145c = new SurfaceView(C0884t.f223a.m185a());
                this.f159a.f145c.getHolder().setType(this.f159a.f144b);
                this.f159a.f145c.getHolder().addCallback(this.f159a);
                C0884t.f223a.m186a(this.f159a.f145c);
                this.f159a.f145c.setVisibility(0);
            }
        }
    }

    /* renamed from: com.unity3d.player.b.2 */
    class C08542 implements Runnable {
        final /* synthetic */ C0850b f160a;

        C08542(C0850b c0850b) {
            this.f160a = c0850b;
        }

        public final void run() {
            if (this.f160a.f145c != null) {
                C0884t.f223a.m187b(this.f160a.f145c);
            }
            this.f160a.f145c = null;
        }
    }

    C0850b(int i) {
        this.f143a = (Activity) C0884t.f223a.m185a();
        this.f144b = 3;
    }

    final void m109a() {
        this.f143a.runOnUiThread(new C08531(this));
    }

    final void m110b() {
        this.f143a.runOnUiThread(new C08542(this));
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}
