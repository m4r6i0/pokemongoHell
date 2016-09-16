package com.unity3d.player;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.SurfaceHolder;
import com.google.android.gms.location.LocationStatusCodes;
import com.upsight.android.internal.util.NetworkHelper;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.unity3d.player.a */
final class C0852a {
    Camera f148a;
    Parameters f149b;
    Size f150c;
    int f151d;
    int[] f152e;
    C0850b f153f;
    private final Object[] f154g;
    private final int f155h;
    private final int f156i;
    private final int f157j;
    private final int f158k;

    /* renamed from: com.unity3d.player.a.a */
    interface C0848a {
        void onCameraFrame(C0852a c0852a, byte[] bArr);
    }

    /* renamed from: com.unity3d.player.a.1 */
    class C08491 implements PreviewCallback {
        long f140a;
        final /* synthetic */ C0848a f141b;
        final /* synthetic */ C0852a f142c;

        C08491(C0852a c0852a, C0848a c0848a) {
            this.f142c = c0852a;
            this.f141b = c0848a;
            this.f140a = 0;
        }

        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            if (this.f142c.f148a == camera) {
                this.f141b.onCameraFrame(this.f142c, bArr);
            }
        }
    }

    /* renamed from: com.unity3d.player.a.2 */
    class C08512 extends C0850b {
        Camera f146a;
        final /* synthetic */ C0852a f147b;

        C08512(C0852a c0852a) {
            this.f147b = c0852a;
            super(3);
            this.f146a = this.f147b.f148a;
        }

        public final void surfaceCreated(SurfaceHolder surfaceHolder) {
            synchronized (this.f147b.f154g) {
                if (this.f147b.f148a != this.f146a) {
                    return;
                }
                try {
                    this.f147b.f148a.setPreviewDisplay(surfaceHolder);
                    this.f147b.f148a.startPreview();
                } catch (Exception e) {
                    C0872m.Log(6, "Unable to initialize webcam data stream: " + e.getMessage());
                }
            }
        }

        public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            synchronized (this.f147b.f154g) {
                if (this.f147b.f148a != this.f146a) {
                    return;
                }
                this.f147b.f148a.stopPreview();
            }
        }
    }

    public C0852a(int i, int i2, int i3, int i4) {
        this.f154g = new Object[0];
        this.f155h = i;
        this.f156i = C0852a.m111a(i2, 640);
        this.f157j = C0852a.m111a(i3, 480);
        this.f158k = C0852a.m111a(i4, 24);
    }

    private static final int m111a(int i, int i2) {
        return i != 0 ? i : i2;
    }

    private static void m112a(Parameters parameters) {
        if (parameters.getSupportedColorEffects() != null) {
            parameters.setColorEffect(NetworkHelper.NETWORK_OPERATOR_NONE);
        }
        if (parameters.getSupportedFocusModes().contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
    }

    private void m114b(C0848a c0848a) {
        synchronized (this.f154g) {
            this.f148a = Camera.open(this.f155h);
            this.f149b = this.f148a.getParameters();
            this.f150c = m117f();
            this.f152e = m116e();
            this.f151d = m115d();
            C0852a.m112a(this.f149b);
            this.f149b.setPreviewSize(this.f150c.width, this.f150c.height);
            this.f149b.setPreviewFpsRange(this.f152e[0], this.f152e[1]);
            this.f148a.setParameters(this.f149b);
            PreviewCallback c08491 = new C08491(this, c0848a);
            int i = (((this.f150c.width * this.f150c.height) * this.f151d) / 8) + AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD;
            this.f148a.addCallbackBuffer(new byte[i]);
            this.f148a.addCallbackBuffer(new byte[i]);
            this.f148a.setPreviewCallbackWithBuffer(c08491);
        }
    }

    private final int m115d() {
        this.f149b.setPreviewFormat(17);
        return ImageFormat.getBitsPerPixel(17);
    }

    private final int[] m116e() {
        double d = (double) (this.f158k * LocationStatusCodes.GEOFENCE_NOT_AVAILABLE);
        List supportedPreviewFpsRange = this.f149b.getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange == null) {
            supportedPreviewFpsRange = new ArrayList();
        }
        int[] iArr = new int[]{this.f158k * LocationStatusCodes.GEOFENCE_NOT_AVAILABLE, this.f158k * LocationStatusCodes.GEOFENCE_NOT_AVAILABLE};
        double d2 = Double.MAX_VALUE;
        for (int[] iArr2 : r0) {
            int[] iArr3;
            double d3;
            double abs = Math.abs(Math.log(d / ((double) iArr2[0]))) + Math.abs(Math.log(d / ((double) iArr2[1])));
            if (abs < d2) {
                iArr3 = iArr2;
                d3 = abs;
            } else {
                double d4 = d2;
                iArr3 = iArr;
                d3 = d4;
            }
            iArr = iArr3;
            d2 = d3;
        }
        return iArr;
    }

    private final Size m117f() {
        double d = (double) this.f156i;
        double d2 = (double) this.f157j;
        Size size = null;
        double d3 = Double.MAX_VALUE;
        for (Size size2 : this.f149b.getSupportedPreviewSizes()) {
            Size size3;
            double d4;
            double abs = Math.abs(Math.log(d / ((double) size2.width))) + Math.abs(Math.log(d2 / ((double) size2.height)));
            if (abs < d3) {
                double d5 = abs;
                size3 = size2;
                d4 = d5;
            } else {
                size3 = size;
                d4 = d3;
            }
            d3 = d4;
            size = size3;
        }
        return size;
    }

    public final int m118a() {
        return this.f155h;
    }

    public final void m119a(C0848a c0848a) {
        synchronized (this.f154g) {
            if (this.f148a == null) {
                m114b(c0848a);
            }
            if (C0878q.f195a && C0878q.f203i.m127a(this.f148a)) {
                this.f148a.startPreview();
                return;
            }
            if (this.f153f == null) {
                this.f153f = new C08512(this);
                this.f153f.m109a();
            }
        }
    }

    public final void m120a(byte[] bArr) {
        synchronized (this.f154g) {
            if (this.f148a != null) {
                this.f148a.addCallbackBuffer(bArr);
            }
        }
    }

    public final Size m121b() {
        return this.f150c;
    }

    public final void m122c() {
        synchronized (this.f154g) {
            if (this.f148a != null) {
                this.f148a.setPreviewCallbackWithBuffer(null);
                this.f148a.stopPreview();
                this.f148a.release();
                this.f148a = null;
            }
            if (this.f153f != null) {
                this.f153f.m110b();
                this.f153f = null;
            }
        }
    }
}
