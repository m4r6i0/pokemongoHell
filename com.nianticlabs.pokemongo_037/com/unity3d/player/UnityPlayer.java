package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.NativeActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import com.google.android.gms.location.places.Place;
import com.unity3d.player.C0852a.C0848a;
import com.upsight.android.marketing.internal.BaseMarketingModule;
import com.voxelbusters.nativeplugins.defines.Keys.Twitter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class UnityPlayer extends FrameLayout implements C0848a {
    private static Lock f99D;
    public static Activity currentActivity;
    private static boolean f100p;
    private ProgressBar f101A;
    private Runnable f102B;
    private Runnable f103C;
    C0847b f104a;
    C0883s f105b;
    private boolean f106c;
    private boolean f107d;
    private boolean f108e;
    private final C0864j f109f;
    private final C0884t f110g;
    private boolean f111h;
    private C0886v f112i;
    private final ConcurrentLinkedQueue f113j;
    private BroadcastReceiver f114k;
    private boolean f115l;
    private ContextWrapper f116m;
    private SurfaceView f117n;
    private WindowManager f118o;
    private boolean f119q;
    private boolean f120r;
    private int f121s;
    private int f122t;
    private final C0879r f123u;
    private String f124v;
    private NetworkInfo f125w;
    private Bundle f126x;
    private List f127y;
    private C0888w f128z;

    /* renamed from: com.unity3d.player.UnityPlayer.c */
    private abstract class C0835c implements Runnable {
        final /* synthetic */ UnityPlayer f40f;

        private C0835c(UnityPlayer unityPlayer) {
            this.f40f = unityPlayer;
        }

        public abstract void m54a();

        public final void run() {
            if (!this.f40f.isFinishing()) {
                m54a();
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.10 */
    class AnonymousClass10 extends C0835c {
        final /* synthetic */ boolean f41a;
        final /* synthetic */ String f42b;
        final /* synthetic */ int f43c;
        final /* synthetic */ UnityPlayer f44d;

        AnonymousClass10(UnityPlayer unityPlayer, boolean z, String str, int i) {
            this.f44d = unityPlayer;
            this.f41a = z;
            this.f42b = str;
            this.f43c = i;
            super((byte) 0);
        }

        public final void m55a() {
            if (this.f41a) {
                this.f44d.nativeSetInputCanceled(true);
            } else if (this.f42b != null) {
                this.f44d.nativeSetInputString(this.f42b);
            }
            if (this.f43c == 1) {
                this.f44d.nativeSoftInputClosed();
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.11 */
    class AnonymousClass11 extends C0835c {
        final /* synthetic */ int f45a;
        final /* synthetic */ byte[] f46b;
        final /* synthetic */ Size f47c;
        final /* synthetic */ C0852a f48d;
        final /* synthetic */ UnityPlayer f49e;

        AnonymousClass11(UnityPlayer unityPlayer, int i, byte[] bArr, Size size, C0852a c0852a) {
            this.f49e = unityPlayer;
            this.f45a = i;
            this.f46b = bArr;
            this.f47c = size;
            this.f48d = c0852a;
            super((byte) 0);
        }

        public final void m56a() {
            this.f49e.nativeVideoFrameCallback(this.f45a, this.f46b, this.f47c.width, this.f47c.height);
            this.f48d.m120a(this.f46b);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.12 */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ String f50a;
        final /* synthetic */ int f51b;
        final /* synthetic */ int f52c;
        final /* synthetic */ int f53d;
        final /* synthetic */ boolean f54e;
        final /* synthetic */ int f55f;
        final /* synthetic */ int f56g;
        final /* synthetic */ UnityPlayer f57h;

        AnonymousClass12(UnityPlayer unityPlayer, String str, int i, int i2, int i3, boolean z, int i4, int i5) {
            this.f57h = unityPlayer;
            this.f50a = str;
            this.f51b = i;
            this.f52c = i2;
            this.f53d = i3;
            this.f54e = z;
            this.f55f = i4;
            this.f56g = i5;
        }

        public final void run() {
            if (this.f57h.f128z == null) {
                this.f57h.pause();
                this.f57h.f128z = new C0888w(this.f57h, this.f57h.f116m, this.f50a, this.f51b, this.f52c, this.f53d, this.f54e, (long) this.f55f, (long) this.f56g);
                this.f57h.addView(this.f57h.f128z);
                this.f57h.f128z.requestFocus();
                this.f57h.f110g.m189d(this.f57h.f117n);
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.17 */
    class AnonymousClass17 implements Runnable {
        final /* synthetic */ Semaphore f63a;
        final /* synthetic */ UnityPlayer f64b;

        AnonymousClass17(UnityPlayer unityPlayer, Semaphore semaphore) {
            this.f64b = unityPlayer;
            this.f63a = semaphore;
        }

        public final void run() {
            this.f64b.m88f();
            this.f63a.release();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.18 */
    class AnonymousClass18 implements Runnable {
        final /* synthetic */ Semaphore f65a;
        final /* synthetic */ UnityPlayer f66b;

        AnonymousClass18(UnityPlayer unityPlayer, Semaphore semaphore) {
            this.f66b = unityPlayer;
            this.f65a = semaphore;
        }

        public final void run() {
            if (this.f66b.nativePause()) {
                this.f66b.f119q = true;
                this.f66b.m88f();
                this.f65a.release(2);
                return;
            }
            this.f65a.release();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.1 */
    class C08371 implements OnClickListener {
        final /* synthetic */ UnityPlayer f68a;

        C08371(UnityPlayer unityPlayer) {
            this.f68a = unityPlayer;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            this.f68a.m77b();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.20 */
    class AnonymousClass20 implements Runnable {
        final /* synthetic */ boolean f69a;
        final /* synthetic */ UnityPlayer f70b;

        AnonymousClass20(UnityPlayer unityPlayer, boolean z) {
            this.f70b = unityPlayer;
            this.f69a = z;
        }

        public final void run() {
            this.f70b.nativeFocusChanged(this.f69a);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.2 */
    class C08382 implements Runnable {
        final /* synthetic */ UnityPlayer f71a;

        C08382(UnityPlayer unityPlayer) {
            this.f71a = unityPlayer;
        }

        public final void run() {
            int l = this.f71a.nativeActivityIndicatorStyle();
            if (l >= 0) {
                if (this.f71a.f101A == null) {
                    this.f71a.f101A = new ProgressBar(this.f71a.f116m, null, new int[]{16842874, 16843401, 16842873, 16843400}[l]);
                    this.f71a.f101A.setIndeterminate(true);
                    this.f71a.f101A.setLayoutParams(new LayoutParams(-2, -2, 51));
                    this.f71a.addView(this.f71a.f101A);
                }
                this.f71a.f101A.setVisibility(0);
                this.f71a.bringChildToFront(this.f71a.f101A);
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.3 */
    class C08393 extends BroadcastReceiver {
        final /* synthetic */ UnityPlayer f72a;

        public void onReceive(Context context, Intent intent) {
            this.f72a.m77b();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.4 */
    class C08404 implements Runnable {
        final /* synthetic */ UnityPlayer f73a;

        C08404(UnityPlayer unityPlayer) {
            this.f73a = unityPlayer;
        }

        public final void run() {
            if (this.f73a.f101A != null) {
                this.f73a.f101A.setVisibility(8);
                this.f73a.removeView(this.f73a.f101A);
                this.f73a.f101A = null;
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.5 */
    class C08415 implements Runnable {
        final /* synthetic */ boolean f74a;
        final /* synthetic */ UnityPlayer f75b;

        C08415(UnityPlayer unityPlayer, boolean z) {
            this.f75b = unityPlayer;
            this.f74a = z;
        }

        public final void run() {
            C0878q.f203i.m126a(this.f75b, this.f74a);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.6 */
    class C08426 implements Runnable {
        final /* synthetic */ UnityPlayer f76a;
        final /* synthetic */ String f77b;
        final /* synthetic */ int f78c;
        final /* synthetic */ boolean f79d;
        final /* synthetic */ boolean f80e;
        final /* synthetic */ boolean f81f;
        final /* synthetic */ boolean f82g;
        final /* synthetic */ String f83h;
        final /* synthetic */ UnityPlayer f84i;

        C08426(UnityPlayer unityPlayer, UnityPlayer unityPlayer2, String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
            this.f84i = unityPlayer;
            this.f76a = unityPlayer2;
            this.f77b = str;
            this.f78c = i;
            this.f79d = z;
            this.f80e = z2;
            this.f81f = z3;
            this.f82g = z4;
            this.f83h = str2;
        }

        public final void run() {
            this.f84i.f105b = new C0883s(this.f84i.f116m, this.f76a, this.f77b, this.f78c, this.f79d, this.f80e, this.f81f, this.f83h);
            this.f84i.f105b.show();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.7 */
    class C08437 implements Runnable {
        final /* synthetic */ UnityPlayer f85a;

        C08437(UnityPlayer unityPlayer) {
            this.f85a = unityPlayer;
        }

        public final void run() {
            if (this.f85a.f105b != null) {
                this.f85a.f105b.dismiss();
                this.f85a.f105b = null;
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.8 */
    class C08448 extends C0835c {
        final /* synthetic */ Runnable f86a;
        final /* synthetic */ UnityPlayer f87b;

        C08448(UnityPlayer unityPlayer, Runnable runnable) {
            this.f87b = unityPlayer;
            this.f86a = runnable;
            super((byte) 0);
        }

        public final void m57a() {
            this.f87b.m105b(this.f86a);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.9 */
    class C08459 implements Runnable {
        final /* synthetic */ String f88a;
        final /* synthetic */ UnityPlayer f89b;

        C08459(UnityPlayer unityPlayer, String str) {
            this.f89b = unityPlayer;
            this.f88a = str;
        }

        public final void run() {
            if (this.f89b.f105b != null && this.f88a != null) {
                this.f89b.f105b.m182a(this.f88a);
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.a */
    enum C0846a {
        PAUSE,
        RESUME,
        QUIT,
        FOCUS_GAINED,
        FOCUS_LOST
    }

    /* renamed from: com.unity3d.player.UnityPlayer.b */
    private class C0847b extends Thread {
        ArrayBlockingQueue f96a;
        boolean f97b;
        final /* synthetic */ UnityPlayer f98c;

        C0847b(UnityPlayer unityPlayer) {
            this.f98c = unityPlayer;
            this.f97b = false;
            this.f96a = new ArrayBlockingQueue(32);
        }

        private void m58a(C0846a c0846a) {
            try {
                this.f96a.put(c0846a);
            } catch (InterruptedException e) {
                interrupt();
            }
        }

        public final void m59a() {
            m58a(C0846a.QUIT);
        }

        public final void m60a(boolean z) {
            m58a(z ? C0846a.FOCUS_GAINED : C0846a.FOCUS_LOST);
        }

        public final void m61b() {
            m58a(C0846a.RESUME);
        }

        public final void m62c() {
            m58a(C0846a.PAUSE);
        }

        public final void run() {
            setName("UnityMain");
            while (true) {
                try {
                    C0846a c0846a = (C0846a) this.f96a.take();
                    if (c0846a != C0846a.QUIT) {
                        if (c0846a == C0846a.RESUME) {
                            this.f97b = true;
                        } else if (c0846a == C0846a.PAUSE) {
                            this.f97b = false;
                            this.f98c.executeGLThreadJobs();
                        } else if (c0846a == C0846a.FOCUS_LOST && !this.f97b) {
                            this.f98c.executeGLThreadJobs();
                        }
                        if (this.f97b) {
                            do {
                                this.f98c.executeGLThreadJobs();
                                if (this.f96a.peek() != null) {
                                    break;
                                } else if (!(this.f98c.isFinishing() || this.f98c.nativeRender())) {
                                    this.f98c.m77b();
                                }
                            } while (!C0847b.interrupted());
                        }
                    } else {
                        return;
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    static {
        currentActivity = null;
        new C0885u().m190a();
        f100p = false;
        f100p = loadLibraryStatic(BaseMarketingModule.SCHEDULER_MAIN);
        f99D = new ReentrantLock();
    }

    public UnityPlayer(ContextWrapper contextWrapper) {
        super(contextWrapper);
        this.f106c = false;
        this.f107d = false;
        this.f108e = true;
        this.f111h = false;
        this.f112i = new C0886v();
        this.f113j = new ConcurrentLinkedQueue();
        this.f114k = null;
        this.f115l = false;
        this.f104a = new C0847b(this);
        this.f120r = true;
        this.f121s = 0;
        this.f122t = 0;
        this.f124v = null;
        this.f125w = null;
        this.f126x = new Bundle();
        this.f127y = new ArrayList();
        this.f105b = null;
        this.f101A = null;
        this.f102B = new C08382(this);
        this.f103C = new C08404(this);
        if (contextWrapper instanceof Activity) {
            currentActivity = (Activity) contextWrapper;
        }
        this.f110g = new C0884t(this);
        this.f116m = contextWrapper;
        this.f109f = contextWrapper instanceof Activity ? new C0877p(contextWrapper) : null;
        this.f123u = new C0879r(contextWrapper, this);
        m66a();
        if (C0878q.f195a) {
            C0878q.f203i.m125a((View) this);
        }
        setFullscreen(true);
        m68a(this.f116m.getApplicationInfo());
        if (C0886v.m193c()) {
            initJni(contextWrapper);
            nativeFile(this.f116m.getPackageCodePath());
            m95j();
            this.f117n = new SurfaceView(contextWrapper);
            this.f117n.getHolder().setFormat(2);
            this.f117n.getHolder().addCallback(new Callback() {
                final /* synthetic */ UnityPlayer f58a;

                {
                    this.f58a = r1;
                }

                public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    this.f58a.m67a(0, surfaceHolder.getSurface());
                }

                public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                    this.f58a.m67a(0, surfaceHolder.getSurface());
                }

                public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    this.f58a.m67a(0, null);
                }
            });
            this.f117n.setFocusable(true);
            this.f117n.setFocusableInTouchMode(true);
            this.f110g.m188c(this.f117n);
            this.f119q = false;
            m81c();
            nativeInitWWW(WWW.class);
            nativeInitWebRequest(UnityWebRequest.class);
            if (C0878q.f199e) {
                C0878q.f206l.m137a(this, this.f116m);
            }
            if (C0878q.f202h) {
                if (currentActivity != null) {
                    C0878q.f207m.m141a(currentActivity, new Runnable() {
                        final /* synthetic */ UnityPlayer f61a;

                        /* renamed from: com.unity3d.player.UnityPlayer.15.1 */
                        class C08361 implements Runnable {
                            final /* synthetic */ AnonymousClass15 f60a;

                            C08361(AnonymousClass15 anonymousClass15) {
                                this.f60a = anonymousClass15;
                            }

                            public final void run() {
                                this.f60a.f61a.f112i.m197d();
                                this.f60a.f61a.m90g();
                            }
                        }

                        {
                            this.f61a = r1;
                        }

                        public final void run() {
                            this.f61a.m105b(new C08361(this));
                        }
                    });
                } else {
                    this.f112i.m197d();
                }
            }
            if (C0878q.f198d) {
                C0878q.f205k.m140a(this);
            }
            this.f118o = (WindowManager) this.f116m.getSystemService("window");
            m97k();
            this.f104a.start();
            return;
        }
        AlertDialog create = new Builder(this.f116m).setTitle("Failure to initialize!").setPositiveButton("OK", new C08371(this)).setMessage("Your hardware does not support this application, sorry!").create();
        create.setCancelable(false);
        create.show();
    }

    public static native void UnitySendMessage(String str, String str2, String str3);

    private static String m65a(String str) {
        byte[] digest;
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(str);
            long length = new File(str).length();
            fileInputStream.skip(length - Math.min(length, 65558));
            byte[] bArr = new byte[Place.TYPE_SUBLOCALITY_LEVEL_2];
            for (int i2 = 0; i2 != -1; i2 = fileInputStream.read(bArr)) {
                instance.update(bArr, 0, i2);
            }
            digest = instance.digest();
        } catch (FileNotFoundException e) {
            digest = null;
        } catch (IOException e2) {
            digest = null;
        } catch (NoSuchAlgorithmException e3) {
            digest = null;
        }
        if (digest == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (i < digest.length) {
            stringBuffer.append(Integer.toString((digest[i] & MotionEventCompat.ACTION_MASK) + AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY, 16).substring(1));
            i++;
        }
        return stringBuffer.toString();
    }

    private void m66a() {
        try {
            File file = new File(this.f116m.getPackageCodePath(), "assets/bin/Data/settings.xml");
            InputStream fileInputStream = file.exists() ? new FileInputStream(file) : this.f116m.getAssets().open("bin/Data/settings.xml");
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(fileInputStream, null);
            String str = null;
            String str2 = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    str2 = newPullParser.getName();
                    String str3 = str;
                    for (int i = 0; i < newPullParser.getAttributeCount(); i++) {
                        if (newPullParser.getAttributeName(i).equalsIgnoreCase(Twitter.NAME)) {
                            str3 = newPullParser.getAttributeValue(i);
                        }
                    }
                    str = str3;
                } else if (eventType == 3) {
                    str2 = null;
                } else if (eventType == 4 && str != null) {
                    if (str2.equalsIgnoreCase("integer")) {
                        this.f126x.putInt(str, Integer.parseInt(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("string")) {
                        this.f126x.putString(str, newPullParser.getText());
                    } else if (str2.equalsIgnoreCase("bool")) {
                        this.f126x.putBoolean(str, Boolean.parseBoolean(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("float")) {
                        this.f126x.putFloat(str, Float.parseFloat(newPullParser.getText()));
                    }
                    str = null;
                }
            }
        } catch (Exception e) {
            C0872m.Log(6, "Unable to locate player settings. " + e.getLocalizedMessage());
            m77b();
        }
    }

    private void m67a(int i, Surface surface) {
        if (!this.f106c) {
            m79b(0, surface);
        }
    }

    private static void m68a(ApplicationInfo applicationInfo) {
        if (f100p && NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            C0886v.m191a();
        }
    }

    private void m69a(C0835c c0835c) {
        if (!isFinishing()) {
            m82c((Runnable) c0835c);
        }
    }

    static void m74a(Runnable runnable) {
        new Thread(runnable).start();
    }

    private static String[] m76a(Context context) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        try {
            int i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File(Environment.getExternalStorageDirectory().toString() + "/Android/obb/" + packageName);
                if (file.exists()) {
                    if (i > 0) {
                        String str = file + File.separator + "main." + i + "." + packageName + ".obb";
                        if (new File(str).isFile()) {
                            vector.add(str);
                        }
                    }
                    if (i > 0) {
                        packageName = file + File.separator + "patch." + i + "." + packageName + ".obb";
                        if (new File(packageName).isFile()) {
                            vector.add(packageName);
                        }
                    }
                }
            }
            String[] strArr = new String[vector.size()];
            vector.toArray(strArr);
            return strArr;
        } catch (NameNotFoundException e) {
            return new String[0];
        }
    }

    private void m77b() {
        if ((this.f116m instanceof Activity) && !((Activity) this.f116m).isFinishing()) {
            ((Activity) this.f116m).finish();
        }
    }

    private boolean m79b(int i, Surface surface) {
        if (!C0886v.m193c()) {
            return false;
        }
        nativeRecreateGfxState(i, surface);
        return true;
    }

    private void m81c() {
        C0875o c0875o = new C0875o((Activity) this.f116m);
        if (this.f116m instanceof NativeActivity) {
            boolean a = c0875o.m157a();
            this.f115l = a;
            nativeForwardEventsToDalvik(a);
        }
    }

    private void m82c(Runnable runnable) {
        if (!C0886v.m193c()) {
            return;
        }
        if (Thread.currentThread() == this.f104a) {
            runnable.run();
        } else {
            this.f113j.add(runnable);
        }
    }

    private void m83d() {
        for (C0852a c : this.f127y) {
            c.m122c();
        }
    }

    private void m85e() {
        for (C0852a c0852a : this.f127y) {
            try {
                c0852a.m119a((C0848a) this);
            } catch (Exception e) {
                C0872m.Log(6, "Unable to initialize camera: " + e.getMessage());
                c0852a.m122c();
            }
        }
    }

    private void m88f() {
        nativeDone();
    }

    private void m90g() {
        if (!this.f112i.m199f()) {
            return;
        }
        if (this.f128z != null) {
            this.f128z.onResume();
            return;
        }
        this.f112i.m196c(true);
        m85e();
        this.f123u.m174e();
        this.f124v = null;
        this.f125w = null;
        if (C0886v.m193c()) {
            m95j();
        }
        m82c(new Runnable() {
            final /* synthetic */ UnityPlayer f67a;

            {
                this.f67a = r1;
            }

            public final void run() {
                this.f67a.nativeResume();
            }
        });
        this.f104a.m61b();
    }

    private static void m91h() {
        if (C0886v.m193c()) {
            lockNativeAccess();
            if (NativeLoader.unload()) {
                C0886v.m192b();
                unlockNativeAccess();
                return;
            }
            unlockNativeAccess();
            throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
        }
    }

    private boolean m93i() {
        return this.f116m.getPackageManager().hasSystemFeature("android.hardware.camera") || this.f116m.getPackageManager().hasSystemFeature("android.hardware.camera.front");
    }

    private final native void initJni(Context context);

    private void m95j() {
        if (this.f126x.getBoolean("useObb")) {
            for (String str : m76a(this.f116m)) {
                String a = m65a(str);
                if (this.f126x.getBoolean(a)) {
                    nativeFile(str);
                }
                this.f126x.remove(a);
            }
        }
    }

    private void m97k() {
        if (this.f116m instanceof Activity) {
            ((Activity) this.f116m).getWindow().setFlags(Place.TYPE_SUBLOCALITY_LEVEL_2, Place.TYPE_SUBLOCALITY_LEVEL_2);
        }
    }

    protected static boolean loadLibraryStatic(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            C0872m.Log(6, "Unable to find " + str);
            return false;
        } catch (Exception e2) {
            C0872m.Log(6, "Unknown error " + e2);
            return false;
        }
    }

    protected static void lockNativeAccess() {
        f99D.lock();
    }

    private final native int nativeActivityIndicatorStyle();

    private final native void nativeDone();

    private final native void nativeFile(String str);

    private final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWWW(Class cls);

    private final native void nativeInitWebRequest(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    private final native boolean nativePause();

    private final native void nativeRecreateGfxState(int i, Surface surface);

    private final native boolean nativeRender();

    private final native void nativeResume();

    private final native void nativeSetExtras(Bundle bundle);

    private final native void nativeSetInputCanceled(boolean z);

    private final native void nativeSetInputString(String str);

    private final native void nativeSetTouchDeltaY(float f);

    private final native void nativeSoftInputClosed();

    private final native void nativeVideoFrameCallback(int i, byte[] bArr, int i2, int i3);

    protected static void unlockNativeAccess() {
        f99D.unlock();
    }

    protected boolean Location_IsServiceEnabledByUser() {
        return this.f123u.m169a();
    }

    protected void Location_SetDesiredAccuracy(float f) {
        this.f123u.m171b(f);
    }

    protected void Location_SetDistanceFilter(float f) {
        this.f123u.m168a(f);
    }

    protected void Location_StartUpdatingLocation() {
        this.f123u.m170b();
    }

    protected void Location_StopUpdatingLocation() {
        this.f123u.m172c();
    }

    final void m105b(Runnable runnable) {
        if (this.f116m instanceof Activity) {
            ((Activity) this.f116m).runOnUiThread(runnable);
        } else {
            C0872m.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    protected void closeCamera(int i) {
        for (C0852a c0852a : this.f127y) {
            if (c0852a.m118a() == i) {
                c0852a.m122c();
                this.f127y.remove(c0852a);
                return;
            }
        }
    }

    public void configurationChanged(Configuration configuration) {
        if (this.f117n instanceof SurfaceView) {
            this.f117n.getHolder().setSizeFromLayout();
        }
        if (this.f128z != null) {
            this.f128z.updateVideoLayout();
        }
    }

    protected void disableLogger() {
        C0872m.f185a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.f106c = surface != null;
            m105b(new Runnable() {
                final /* synthetic */ UnityPlayer f62a;

                {
                    this.f62a = r1;
                }

                public final void run() {
                    if (this.f62a.f106c) {
                        this.f62a.f110g.m189d(this.f62a.f117n);
                    } else {
                        this.f62a.f110g.m188c(this.f62a.f117n);
                    }
                }
            });
        }
        return m79b(i, surface);
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.f113j.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    protected void forwardMotionEventToDalvik(long j, long j2, int i, int i2, int[] iArr, float[] fArr, int i3, float f, float f2, int i4, int i5, int i6, int i7, int i8, long[] jArr, float[] fArr2) {
        this.f109f.m142a(j, j2, i, i2, iArr, fArr, i3, f, f2, i4, i5, i6, i7, i8, jArr, fArr2);
    }

    protected int getCameraOrientation(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        return cameraInfo.orientation;
    }

    protected int getNumCameras() {
        return !m93i() ? 0 : Camera.getNumberOfCameras();
    }

    public Bundle getSettings() {
        return this.f126x;
    }

    protected int getSplashMode() {
        return this.f126x.getInt("splash_mode");
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        Runnable c08437 = new C08437(this);
        if (C0878q.f201g) {
            m69a(new C08448(this, c08437));
        } else {
            m105b(c08437);
        }
    }

    protected void hideVideoPlayer() {
        m105b(new Runnable() {
            final /* synthetic */ UnityPlayer f59a;

            {
                this.f59a = r1;
            }

            public final void run() {
                if (this.f59a.f128z != null) {
                    this.f59a.f110g.m188c(this.f59a.f117n);
                    this.f59a.removeView(this.f59a.f128z);
                    this.f59a.f128z = null;
                    this.f59a.resume();
                }
            }
        });
    }

    public void init(int i, boolean z) {
    }

    protected int[] initCamera(int i, int i2, int i3, int i4) {
        C0852a c0852a = new C0852a(i, i2, i3, i4);
        try {
            c0852a.m119a((C0848a) this);
            this.f127y.add(c0852a);
            Size b = c0852a.m121b();
            return new int[]{b.width, b.height};
        } catch (Exception e) {
            C0872m.Log(6, "Unable to initialize camera: " + e.getMessage());
            c0852a.m122c();
            return null;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        return nativeInjectEvent(inputEvent);
    }

    protected boolean installPresentationDisplay(int i) {
        return C0878q.f199e ? C0878q.f206l.m138a(this, this.f116m, i) : false;
    }

    protected boolean isCameraFrontFacing(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        return cameraInfo.facing == 1;
    }

    protected boolean isFinishing() {
        if (!this.f119q) {
            boolean z = (this.f116m instanceof Activity) && ((Activity) this.f116m).isFinishing();
            this.f119q = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    protected final native void nativeAddVSyncTime(long j);

    final native void nativeForwardEventsToDalvik(boolean z);

    protected native void nativeSetLocation(float f, float f2, float f3, float f4, double d, float f5);

    protected native void nativeSetLocationStatus(int i);

    public void onCameraFrame(C0852a c0852a, byte[] bArr) {
        m69a(new AnonymousClass11(this, c0852a.m118a(), bArr, c0852a.m121b(), c0852a));
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public void pause() {
        if (this.f128z != null) {
            this.f128z.onPause();
            return;
        }
        reportSoftInputStr(null, 1, true);
        if (this.f112i.m200g()) {
            if (C0886v.m193c()) {
                Semaphore semaphore = new Semaphore(0);
                if (isFinishing()) {
                    m82c(new AnonymousClass17(this, semaphore));
                } else {
                    m82c(new AnonymousClass18(this, semaphore));
                }
                try {
                    if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                        C0872m.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException e) {
                    C0872m.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    quit();
                }
            }
            this.f112i.m196c(false);
            this.f112i.m195b(true);
            m83d();
            this.f104a.m62c();
            this.f123u.m173d();
        }
    }

    public void quit() {
        this.f119q = true;
        if (!this.f112i.m198e()) {
            pause();
        }
        this.f104a.m59a();
        try {
            this.f104a.join(4000);
        } catch (InterruptedException e) {
            this.f104a.interrupt();
        }
        if (this.f114k != null) {
            this.f116m.unregisterReceiver(this.f114k);
        }
        this.f114k = null;
        if (C0886v.m193c()) {
            removeAllViews();
        }
        if (C0878q.f199e) {
            C0878q.f206l.m136a(this.f116m);
        }
        if (C0878q.f198d) {
            C0878q.f205k.m139a();
        }
        kill();
        m91h();
    }

    protected void reportSoftInputStr(String str, int i, boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        m69a(new AnonymousClass10(this, z, str, i));
    }

    public void resume() {
        if (C0878q.f195a) {
            C0878q.f203i.m128b(this);
        }
        this.f112i.m195b(false);
        m90g();
    }

    protected void setFullscreen(boolean z) {
        this.f108e = z;
        if (C0878q.f195a) {
            m105b(new C08415(this, z));
        }
    }

    protected void setSoftInputStr(String str) {
        m105b(new C08459(this, str));
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        m105b(new C08426(this, this, str, i, z, z2, z3, z4, str2));
    }

    protected void showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        m105b(new AnonymousClass12(this, str, i, i2, i3, z, i4, i5));
    }

    protected void startActivityIndicator() {
        m105b(this.f102B);
    }

    protected void stopActivityIndicator() {
        m105b(this.f103C);
    }

    public void windowFocusChanged(boolean z) {
        this.f112i.m194a(z);
        if (z && this.f105b != null) {
            reportSoftInputStr(null, 1, false);
        }
        if (C0878q.f195a && z) {
            C0878q.f203i.m128b(this);
        }
        m82c(new AnonymousClass20(this, z));
        this.f104a.m60a(z);
        m90g();
    }
}
