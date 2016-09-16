package com.unity3d.player;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

/* renamed from: com.unity3d.player.k */
public final class C0869k implements C0861g {
    private Object f176a;
    private Presentation f177b;
    private DisplayListener f178c;

    /* renamed from: com.unity3d.player.k.1 */
    class C08651 implements DisplayListener {
        final /* synthetic */ UnityPlayer f168a;
        final /* synthetic */ C0869k f169b;

        C08651(C0869k c0869k, UnityPlayer unityPlayer) {
            this.f169b = c0869k;
            this.f168a = unityPlayer;
        }

        public final void onDisplayAdded(int i) {
            this.f168a.displayChanged(-1, null);
        }

        public final void onDisplayChanged(int i) {
            this.f168a.displayChanged(-1, null);
        }

        public final void onDisplayRemoved(int i) {
            this.f168a.displayChanged(-1, null);
        }
    }

    /* renamed from: com.unity3d.player.k.2 */
    class C08682 implements Runnable {
        final /* synthetic */ Context f172a;
        final /* synthetic */ Display f173b;
        final /* synthetic */ UnityPlayer f174c;
        final /* synthetic */ C0869k f175d;

        /* renamed from: com.unity3d.player.k.2.1 */
        class C08671 extends Presentation {
            final /* synthetic */ C08682 f171a;

            /* renamed from: com.unity3d.player.k.2.1.1 */
            class C08661 implements Callback {
                final /* synthetic */ C08671 f170a;

                C08661(C08671 c08671) {
                    this.f170a = c08671;
                }

                public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    this.f170a.f171a.f174c.displayChanged(1, surfaceHolder.getSurface());
                }

                public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                    this.f170a.f171a.f174c.displayChanged(1, surfaceHolder.getSurface());
                }

                public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    this.f170a.f171a.f174c.displayChanged(1, null);
                }
            }

            C08671(C08682 c08682, Context context, Display display) {
                this.f171a = c08682;
                super(context, display);
            }

            protected final void onCreate(Bundle bundle) {
                View surfaceView = new SurfaceView(this.f171a.f172a);
                surfaceView.getHolder().addCallback(new C08661(this));
                setContentView(surfaceView);
            }

            public final void onDisplayRemoved() {
                dismiss();
                synchronized (this.f171a.f175d.f176a) {
                    this.f171a.f175d.f177b = null;
                }
            }
        }

        C08682(C0869k c0869k, Context context, Display display, UnityPlayer unityPlayer) {
            this.f175d = c0869k;
            this.f172a = context;
            this.f173b = display;
            this.f174c = unityPlayer;
        }

        public final void run() {
            synchronized (this.f175d.f176a) {
                if (this.f175d.f177b != null) {
                    this.f175d.f177b.dismiss();
                }
                this.f175d.f177b = new C08671(this, this.f172a, this.f173b);
                this.f175d.f177b.show();
            }
        }
    }

    public C0869k() {
        this.f176a = new Object[0];
    }

    public final void m146a(Context context) {
        if (this.f178c != null) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            if (displayManager != null) {
                displayManager.unregisterDisplayListener(this.f178c);
            }
        }
    }

    public final void m147a(UnityPlayer unityPlayer, Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        if (displayManager != null) {
            displayManager.registerDisplayListener(new C08651(this, unityPlayer), null);
        }
    }

    public final boolean m148a(UnityPlayer unityPlayer, Context context, int i) {
        synchronized (this.f176a) {
            Display display;
            if (this.f177b != null && this.f177b.isShowing()) {
                display = this.f177b.getDisplay();
                if (display != null && display.getDisplayId() == i) {
                    return true;
                }
            }
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            if (displayManager == null) {
                return false;
            }
            display = displayManager.getDisplay(i);
            if (display == null) {
                return false;
            }
            unityPlayer.m105b(new C08682(this, context, display, unityPlayer));
            return true;
        }
    }
}
