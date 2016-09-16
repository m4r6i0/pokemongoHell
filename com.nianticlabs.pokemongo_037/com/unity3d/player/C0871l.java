package com.unity3d.player;

import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.unity3d.player.l */
public final class C0871l implements C0862h {
    private Choreographer f181a;
    private long f182b;
    private FrameCallback f183c;
    private Lock f184d;

    /* renamed from: com.unity3d.player.l.1 */
    class C08701 implements FrameCallback {
        final /* synthetic */ UnityPlayer f179a;
        final /* synthetic */ C0871l f180b;

        C08701(C0871l c0871l, UnityPlayer unityPlayer) {
            this.f180b = c0871l;
            this.f179a = unityPlayer;
        }

        public final void doFrame(long j) {
            UnityPlayer.lockNativeAccess();
            if (C0886v.m193c()) {
                this.f179a.nativeAddVSyncTime(j);
            }
            UnityPlayer.unlockNativeAccess();
            this.f180b.f184d.lock();
            if (this.f180b.f181a != null) {
                this.f180b.f181a.postFrameCallback(this.f180b.f183c);
            }
            this.f180b.f184d.unlock();
        }
    }

    public C0871l() {
        this.f181a = null;
        this.f182b = 0;
        this.f184d = new ReentrantLock();
    }

    public final void m152a() {
        this.f184d.lock();
        if (this.f181a != null) {
            this.f181a.removeFrameCallback(this.f183c);
        }
        this.f181a = null;
        this.f184d.unlock();
    }

    public final void m153a(UnityPlayer unityPlayer) {
        this.f184d.lock();
        if (this.f181a == null) {
            this.f181a = Choreographer.getInstance();
            if (this.f181a != null) {
                C0872m.Log(4, "Choreographer available: Enabling VSYNC timing");
                this.f183c = new C08701(this, unityPlayer);
                this.f181a.postFrameCallback(this.f183c);
            }
        }
        this.f184d.unlock();
    }
}
