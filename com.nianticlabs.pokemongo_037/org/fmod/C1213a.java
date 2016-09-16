package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

/* renamed from: org.fmod.a */
final class C1213a implements Runnable {
    private final FMODAudioDevice f901a;
    private final ByteBuffer f902b;
    private final int f903c;
    private final int f904d;
    private final int f905e;
    private volatile Thread f906f;
    private volatile boolean f907g;
    private AudioRecord f908h;
    private boolean f909i;

    C1213a(FMODAudioDevice fMODAudioDevice, int i, int i2) {
        this.f901a = fMODAudioDevice;
        this.f903c = i;
        this.f904d = i2;
        this.f905e = 2;
        this.f902b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i, i2, 2));
    }

    private void m880d() {
        if (this.f908h != null) {
            if (this.f908h.getState() == 1) {
                this.f908h.stop();
            }
            this.f908h.release();
            this.f908h = null;
        }
        this.f902b.position(0);
        this.f909i = false;
    }

    public final int m881a() {
        return this.f902b.capacity();
    }

    public final void m882b() {
        if (this.f906f != null) {
            m883c();
        }
        this.f907g = true;
        this.f906f = new Thread(this);
        this.f906f.start();
    }

    public final void m883c() {
        while (this.f906f != null) {
            this.f907g = false;
            try {
                this.f906f.join();
                this.f906f = null;
            } catch (InterruptedException e) {
            }
        }
    }

    public final void run() {
        int i = 3;
        while (this.f907g) {
            int i2;
            if (!this.f909i && i > 0) {
                m880d();
                this.f908h = new AudioRecord(1, this.f903c, this.f904d, this.f905e, this.f902b.capacity());
                this.f909i = this.f908h.getState() == 1;
                if (this.f909i) {
                    this.f902b.position(0);
                    this.f908h.startRecording();
                    i2 = 3;
                    if (this.f909i || this.f908h.getRecordingState() != 3) {
                        i = i2;
                    } else {
                        this.f901a.fmodProcessMicData(this.f902b, this.f908h.read(this.f902b, this.f902b.capacity()));
                        this.f902b.position(0);
                        i = i2;
                    }
                } else {
                    Log.e("FMOD", "AudioRecord failed to initialize (status " + this.f908h.getState() + ")");
                    i--;
                    m880d();
                }
            }
            i2 = i;
            if (this.f909i) {
            }
            i = i2;
        }
        m880d();
    }
}
