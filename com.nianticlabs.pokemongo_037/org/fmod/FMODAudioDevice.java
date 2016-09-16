package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {
    private static int f890h;
    private static int f891i;
    private static int f892j;
    private static int f893k;
    private volatile Thread f894a;
    private volatile boolean f895b;
    private AudioTrack f896c;
    private boolean f897d;
    private ByteBuffer f898e;
    private byte[] f899f;
    private volatile C1213a f900g;

    static {
        f890h = 0;
        f891i = 1;
        f892j = 2;
        f893k = 3;
    }

    public FMODAudioDevice() {
        this.f894a = null;
        this.f895b = false;
        this.f896c = null;
        this.f897d = false;
        this.f898e = null;
        this.f899f = null;
    }

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        if (this.f896c != null) {
            if (this.f896c.getState() == 1) {
                this.f896c.stop();
            }
            this.f896c.release();
            this.f896c = null;
        }
        this.f898e = null;
        this.f899f = null;
        this.f897d = false;
    }

    public synchronized void close() {
        stop();
    }

    native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.f894a != null && this.f894a.isAlive();
    }

    public void run() {
        int i = 3;
        while (this.f895b) {
            int i2;
            if (this.f897d || i <= 0) {
                i2 = i;
            } else {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(f890h);
                int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                int fmodGetInfo2 = fmodGetInfo(f891i);
                i2 = fmodGetInfo(f892j);
                if ((fmodGetInfo2 * i2) * 4 > round) {
                    round = (i2 * fmodGetInfo2) * 4;
                }
                this.f896c = new AudioTrack(3, fmodGetInfo, 3, 2, round, 1);
                this.f897d = this.f896c.getState() == 1;
                if (this.f897d) {
                    this.f898e = ByteBuffer.allocateDirect((fmodGetInfo2 * 2) * 2);
                    this.f899f = new byte[this.f898e.capacity()];
                    this.f896c.play();
                    i2 = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f896c.getState() + ")");
                    releaseAudioTrack();
                    i2 = i - 1;
                }
            }
            if (!this.f897d) {
                i = i2;
            } else if (fmodGetInfo(f893k) == 1) {
                fmodProcess(this.f898e);
                this.f898e.get(this.f899f, 0, this.f898e.capacity());
                this.f896c.write(this.f899f, 0, this.f898e.capacity());
                this.f898e.position(0);
                i = i2;
            } else {
                releaseAudioTrack();
                i = i2;
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f894a != null) {
            stop();
        }
        this.f894a = new Thread(this, "FMODAudioDevice");
        this.f894a.setPriority(10);
        this.f895b = true;
        this.f894a.start();
        if (this.f900g != null) {
            this.f900g.m882b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f900g == null) {
            this.f900g = new C1213a(this, i, i2);
            this.f900g.m882b();
        }
        return this.f900g.m881a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop() {
        /*
        r1 = this;
        monitor-enter(r1);
    L_0x0001:
        r0 = r1.f894a;	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x0013;
    L_0x0005:
        r0 = 0;
        r1.f895b = r0;	 Catch:{ all -> 0x001e }
        r0 = r1.f894a;	 Catch:{ InterruptedException -> 0x0011 }
        r0.join();	 Catch:{ InterruptedException -> 0x0011 }
        r0 = 0;
        r1.f894a = r0;	 Catch:{ InterruptedException -> 0x0011 }
        goto L_0x0001;
    L_0x0011:
        r0 = move-exception;
        goto L_0x0001;
    L_0x0013:
        r0 = r1.f900g;	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x001c;
    L_0x0017:
        r0 = r1.f900g;	 Catch:{ all -> 0x001e }
        r0.m883c();	 Catch:{ all -> 0x001e }
    L_0x001c:
        monitor-exit(r1);
        return;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fmod.FMODAudioDevice.stop():void");
    }

    public synchronized void stopAudioRecord() {
        if (this.f900g != null) {
            this.f900g.m883c();
            this.f900g = null;
        }
    }
}
