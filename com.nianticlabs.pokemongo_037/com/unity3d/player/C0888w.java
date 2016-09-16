package com.unity3d.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: com.unity3d.player.w */
public final class C0888w extends FrameLayout implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, MediaPlayerControl {
    private static boolean f235a;
    private final UnityPlayer f236b;
    private final Context f237c;
    private final SurfaceView f238d;
    private final SurfaceHolder f239e;
    private final String f240f;
    private final int f241g;
    private final int f242h;
    private final boolean f243i;
    private final long f244j;
    private final long f245k;
    private final FrameLayout f246l;
    private final Display f247m;
    private int f248n;
    private int f249o;
    private int f250p;
    private int f251q;
    private MediaPlayer f252r;
    private MediaController f253s;
    private boolean f254t;
    private boolean f255u;
    private int f256v;
    private boolean f257w;
    private int f258x;
    private boolean f259y;

    /* renamed from: com.unity3d.player.w.1 */
    class C08871 implements Runnable {
        final /* synthetic */ C0888w f234a;

        C08871(C0888w c0888w) {
            this.f234a = c0888w;
        }

        public final void run() {
            this.f234a.f236b.hideVideoPlayer();
        }
    }

    static {
        f235a = false;
    }

    protected C0888w(UnityPlayer unityPlayer, Context context, String str, int i, int i2, int i3, boolean z, long j, long j2) {
        super(context);
        this.f254t = false;
        this.f255u = false;
        this.f256v = 0;
        this.f257w = false;
        this.f258x = 0;
        this.f236b = unityPlayer;
        this.f237c = context;
        this.f246l = this;
        this.f238d = new SurfaceView(context);
        this.f239e = this.f238d.getHolder();
        this.f239e.addCallback(this);
        this.f239e.setType(3);
        this.f246l.setBackgroundColor(i);
        this.f246l.addView(this.f238d);
        this.f247m = ((WindowManager) this.f237c.getSystemService("window")).getDefaultDisplay();
        this.f240f = str;
        this.f241g = i2;
        this.f242h = i3;
        this.f243i = z;
        this.f244j = j;
        this.f245k = j2;
        if (f235a) {
            C0888w.m203a("fileName: " + this.f240f);
        }
        if (f235a) {
            C0888w.m203a("backgroundColor: " + i);
        }
        if (f235a) {
            C0888w.m203a("controlMode: " + this.f241g);
        }
        if (f235a) {
            C0888w.m203a("scalingMode: " + this.f242h);
        }
        if (f235a) {
            C0888w.m203a("isURL: " + this.f243i);
        }
        if (f235a) {
            C0888w.m203a("videoOffset: " + this.f244j);
        }
        if (f235a) {
            C0888w.m203a("videoLength: " + this.f245k);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.f259y = true;
    }

    private void m202a() {
        doCleanUp();
        try {
            this.f252r = new MediaPlayer();
            if (this.f243i) {
                this.f252r.setDataSource(this.f237c, Uri.parse(this.f240f));
            } else if (this.f245k != 0) {
                FileInputStream fileInputStream = new FileInputStream(this.f240f);
                this.f252r.setDataSource(fileInputStream.getFD(), this.f244j, this.f245k);
                fileInputStream.close();
            } else {
                try {
                    AssetFileDescriptor openFd = getResources().getAssets().openFd(this.f240f);
                    this.f252r.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    openFd.close();
                } catch (IOException e) {
                    FileInputStream fileInputStream2 = new FileInputStream(this.f240f);
                    this.f252r.setDataSource(fileInputStream2.getFD());
                    fileInputStream2.close();
                }
            }
            this.f252r.setDisplay(this.f239e);
            this.f252r.setScreenOnWhilePlaying(true);
            this.f252r.setOnBufferingUpdateListener(this);
            this.f252r.setOnCompletionListener(this);
            this.f252r.setOnPreparedListener(this);
            this.f252r.setOnVideoSizeChangedListener(this);
            this.f252r.setAudioStreamType(3);
            this.f252r.prepare();
            if (this.f241g == 0 || this.f241g == 1) {
                this.f253s = new MediaController(this.f237c);
                this.f253s.setMediaPlayer(this);
                this.f253s.setAnchorView(this);
                this.f253s.setEnabled(true);
                this.f253s.show();
            }
        } catch (Exception e2) {
            if (f235a) {
                C0888w.m203a("error: " + e2.getMessage() + e2);
            }
            onDestroy();
        }
    }

    private static void m203a(String str) {
        Log.v("Video", "VideoPlayer: " + str);
    }

    private void m204b() {
        if (!isPlaying()) {
            if (f235a) {
                C0888w.m203a("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.f257w) {
                start();
            }
        }
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    protected final void doCleanUp() {
        if (this.f252r != null) {
            this.f252r.release();
            this.f252r = null;
        }
        this.f250p = 0;
        this.f251q = 0;
        this.f255u = false;
        this.f254t = false;
    }

    public final int getBufferPercentage() {
        return this.f243i ? this.f256v : 100;
    }

    public final int getCurrentPosition() {
        return this.f252r == null ? 0 : this.f252r.getCurrentPosition();
    }

    public final int getDuration() {
        return this.f252r == null ? 0 : this.f252r.getDuration();
    }

    public final boolean isPlaying() {
        boolean z = this.f255u && this.f254t;
        return this.f252r == null ? !z : this.f252r.isPlaying() || !z;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f235a) {
            C0888w.m203a("onBufferingUpdate percent:" + i);
        }
        this.f256v = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f235a) {
            C0888w.m203a("onCompletion called");
        }
        onDestroy();
    }

    public final void onControllerHide() {
    }

    protected final void onDestroy() {
        onPause();
        doCleanUp();
        UnityPlayer.m74a(new C08871(this));
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && (this.f241g != 2 || i == 0 || keyEvent.isSystem())) {
            return this.f253s != null ? this.f253s.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
        } else {
            onDestroy();
            return true;
        }
    }

    protected final void onPause() {
        if (f235a) {
            C0888w.m203a("onPause called");
        }
        if (!this.f257w) {
            pause();
            this.f257w = false;
        }
        if (this.f252r != null) {
            this.f258x = this.f252r.getCurrentPosition();
        }
        this.f259y = false;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f235a) {
            C0888w.m203a("onPrepared called");
        }
        this.f255u = true;
        if (this.f255u && this.f254t) {
            m204b();
        }
    }

    protected final void onResume() {
        if (f235a) {
            C0888w.m203a("onResume called");
        }
        if (!(this.f259y || this.f257w)) {
            start();
        }
        this.f259y = true;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        if (this.f241g != 2 || action != 0) {
            return this.f253s != null ? this.f253s.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        } else {
            onDestroy();
            return true;
        }
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f235a) {
            C0888w.m203a("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.f254t = true;
            this.f250p = i;
            this.f251q = i2;
            if (this.f255u && this.f254t) {
                m204b();
            }
        } else if (f235a) {
            C0888w.m203a("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    public final void pause() {
        if (this.f252r != null) {
            this.f252r.pause();
            this.f257w = true;
        }
    }

    public final void seekTo(int i) {
        if (this.f252r != null) {
            this.f252r.seekTo(i);
        }
    }

    public final void start() {
        if (this.f252r != null) {
            this.f252r.start();
            this.f257w = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f235a) {
            C0888w.m203a("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        if (this.f248n != i2 || this.f249o != i3) {
            this.f248n = i2;
            this.f249o = i3;
            updateVideoLayout();
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f235a) {
            C0888w.m203a("surfaceCreated called");
        }
        m202a();
        seekTo(this.f258x);
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f235a) {
            C0888w.m203a("surfaceDestroyed called");
        }
        doCleanUp();
    }

    protected final void updateVideoLayout() {
        if (f235a) {
            C0888w.m203a("updateVideoLayout");
        }
        if (this.f248n == 0 || this.f249o == 0) {
            WindowManager windowManager = (WindowManager) this.f237c.getSystemService("window");
            this.f248n = windowManager.getDefaultDisplay().getWidth();
            this.f249o = windowManager.getDefaultDisplay().getHeight();
        }
        int i = this.f248n;
        int i2 = this.f249o;
        float f = ((float) this.f250p) / ((float) this.f251q);
        float f2 = ((float) this.f248n) / ((float) this.f249o);
        if (this.f242h == 1) {
            if (f2 <= f) {
                i2 = (int) (((float) this.f248n) / f);
            } else {
                i = (int) (((float) this.f249o) * f);
            }
        } else if (this.f242h == 2) {
            if (f2 >= f) {
                i2 = (int) (((float) this.f248n) / f);
            } else {
                i = (int) (((float) this.f249o) * f);
            }
        } else if (this.f242h == 0) {
            i = this.f250p;
            i2 = this.f251q;
        }
        if (f235a) {
            C0888w.m203a("frameWidth = " + i + "; frameHeight = " + i2);
        }
        this.f246l.updateViewLayout(this.f238d, new LayoutParams(i, i2, 17));
    }
}
