package com.unity3d.player;

import android.app.Activity;
import android.content.ContextWrapper;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import com.upsight.mediation.mraid.properties.MRAIDResizeProperties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import spacemadness.com.lunarconsole.C1562R;

/* renamed from: com.unity3d.player.p */
public final class C0877p implements C0864j {
    private final Queue f192a;
    private final Activity f193b;
    private Runnable f194c;

    /* renamed from: com.unity3d.player.p.1 */
    class C08761 implements Runnable {
        final /* synthetic */ C0877p f191a;

        C08761(C0877p c0877p) {
            this.f191a = c0877p;
        }

        private static void m158a(View view, MotionEvent motionEvent) {
            if (C0878q.f196b) {
                C0878q.f204j.m123a(view, motionEvent);
            }
        }

        public final void run() {
            while (true) {
                MotionEvent motionEvent = (MotionEvent) this.f191a.f192a.poll();
                if (motionEvent != null) {
                    View decorView = this.f191a.f193b.getWindow().getDecorView();
                    int source = motionEvent.getSource();
                    if ((source & 2) != 0) {
                        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
                            case C1562R.styleable.AdsAttrs_adSize /*0*/:
                            case C1562R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                            case C1562R.styleable.LoadingImageView_circleCrop /*2*/:
                            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
                            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
                            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
                            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_RIGHT /*6*/:
                                decorView.dispatchTouchEvent(motionEvent);
                                break;
                            default:
                                C08761.m158a(decorView, motionEvent);
                                break;
                        }
                    } else if ((source & 4) != 0) {
                        decorView.dispatchTrackballEvent(motionEvent);
                    } else {
                        C08761.m158a(decorView, motionEvent);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public C0877p(ContextWrapper contextWrapper) {
        this.f192a = new ConcurrentLinkedQueue();
        this.f194c = new C08761(this);
        this.f193b = (Activity) contextWrapper;
    }

    private static int m159a(PointerCoords[] pointerCoordsArr, float[] fArr, int i) {
        for (int i2 = 0; i2 < pointerCoordsArr.length; i2++) {
            PointerCoords pointerCoords = new PointerCoords();
            pointerCoordsArr[i2] = pointerCoords;
            int i3 = i + 1;
            pointerCoords.orientation = fArr[i];
            int i4 = i3 + 1;
            pointerCoords.pressure = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.size = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.toolMajor = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.toolMinor = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.touchMajor = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.touchMinor = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.x = fArr[i3];
            i = i4 + 1;
            pointerCoords.y = fArr[i4];
        }
        return i;
    }

    private static PointerCoords[] m161a(int i, float[] fArr) {
        PointerCoords[] pointerCoordsArr = new PointerCoords[i];
        C0877p.m159a(pointerCoordsArr, fArr, 0);
        return pointerCoordsArr;
    }

    public final void m163a(long j, long j2, int i, int i2, int[] iArr, float[] fArr, int i3, float f, float f2, int i4, int i5, int i6, int i7, int i8, long[] jArr, float[] fArr2) {
        if (this.f193b != null) {
            MotionEvent obtain = MotionEvent.obtain(j, j2, i, i2, iArr, C0877p.m161a(i2, fArr), i3, f, f2, i4, i5, i6, i7);
            int i9 = 0;
            for (int i10 = 0; i10 < i8; i10++) {
                PointerCoords[] pointerCoordsArr = new PointerCoords[i2];
                i9 = C0877p.m159a(pointerCoordsArr, fArr2, i9);
                obtain.addBatch(jArr[i10], pointerCoordsArr, i3);
            }
            this.f192a.add(obtain);
            this.f193b.runOnUiThread(this.f194c);
        }
    }
}
