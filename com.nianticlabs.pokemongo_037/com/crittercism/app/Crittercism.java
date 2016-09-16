package com.crittercism.app;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import com.crittercism.integrations.PluginException;
import com.voxelbusters.nativeplugins.defines.Keys.Ui;
import crittercism.android.az;
import crittercism.android.az.C11001;
import crittercism.android.az.C11034;
import crittercism.android.az.C11067;
import crittercism.android.bc;
import crittercism.android.bg;
import crittercism.android.bn.C1123a;
import crittercism.android.cf;
import crittercism.android.cf.C1156a;
import crittercism.android.dk;
import crittercism.android.dq;
import crittercism.android.dt;
import crittercism.android.dx;
import java.lang.reflect.Array;
import java.net.URL;
import org.json.JSONObject;
import spacemadness.com.lunarconsole.C1562R;

public class Crittercism {
    private Crittercism() {
    }

    public static void performRateMyAppButtonAction(CritterRateMyAppButtons critterRateMyAppButtons) {
        try {
            if (az.m400A().f369f.m772b()) {
                dx.m782c("User has opted out of crittercism.  performRateMyAppButtonAction exiting.");
                return;
            }
            az A = az.m400A();
            if (VERSION.SDK_INT < 5) {
                dx.m782c("Rate my app not supported below api level 5");
                return;
            }
            String D = A.m406D();
            if (D == null) {
                dx.m779b("Cannot create proper URI to open app market.  Returning null.");
                return;
            }
            switch (C11034.f340a[critterRateMyAppButtons.ordinal()]) {
                case C1562R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                    try {
                        A.m416a(D);
                    } catch (Exception e) {
                        dx.m782c("performRateMyAppButtonAction(CritterRateMyAppButtons.YES) failed.  Email support@crittercism.com.");
                        dx.m781c();
                    }
                case C1562R.styleable.LoadingImageView_circleCrop /*2*/:
                    try {
                        A.m405C();
                    } catch (Exception e2) {
                        dx.m782c("performRateMyAppButtonAction(CritterRateMyAppButtons.NO) failed.  Email support@crittercism.com.");
                    }
                default:
            }
        } catch (ThreadDeath e3) {
            throw e3;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static AlertDialog generateRateMyAppAlertDialog(Context context, String str, String str2) {
        try {
            return az.m400A().m408a(context, str, str2);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return null;
        }
    }

    public static AlertDialog generateRateMyAppAlertDialog(Context context) {
        AlertDialog alertDialog = null;
        try {
            String b;
            String c;
            az A = az.m400A();
            dt dtVar = A.f355A;
            if (A.f355A != null) {
                b = A.f355A.m760b();
                c = A.f355A.m761c();
            } else {
                c = null;
                b = null;
            }
            alertDialog = A.m408a(context, c, b);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
        return alertDialog;
    }

    public static synchronized void initialize(Context context, String str, CrittercismConfig crittercismConfig) {
        synchronized (Crittercism.class) {
            if (str == null) {
                try {
                    m25a(String.class.getCanonicalName());
                } catch (C1123a e) {
                    throw new IllegalArgumentException("Crittercism cannot be initialized. " + e.getMessage());
                } catch (ThreadDeath e2) {
                    throw e2;
                } catch (Throwable th) {
                    dx.m777a(th);
                }
            } else if (context == null) {
                m25a(Context.class.getCanonicalName());
            } else if (crittercismConfig == null) {
                m25a(CrittercismConfig.class.getCanonicalName());
            } else if (!az.m400A().f365b) {
                long nanoTime = System.nanoTime();
                az.m400A().m411a(context, str, crittercismConfig);
                new StringBuilder("Crittercism finished initializing in ").append((System.nanoTime() - nanoTime) / 1000000).append("ms");
                dx.m778b();
            }
        }
    }

    public static synchronized void initialize(Context context, String appID) {
        synchronized (Crittercism.class) {
            initialize(context, appID, new CrittercismConfig());
        }
    }

    private static void m25a(String str) {
        dx.m780b("Crittercism cannot be initialized", new NullPointerException(str + " was null"));
    }

    public static void sendAppLoadData() {
        try {
            CrittercismConfig crittercismConfig = az.m400A().f384u;
            if (crittercismConfig == null) {
                m27b("sendAppLoadData");
            } else if (!crittercismConfig.delaySendingAppLoad()) {
                dx.m775a("sendAppLoadData() will only send data to Crittercism if \"delaySendingAppLoad\" is set to true in the configuration settings you include in the init call.");
            } else if (!az.m400A().f369f.m772b()) {
                az A = az.m400A();
                if (!A.f384u.delaySendingAppLoad()) {
                    dx.m782c("CrittercismConfig instance not set to delay sending app loads.");
                } else if (!A.f383t && !A.f357C) {
                    A.f357C = true;
                    Runnable c11001 = new C11001(A);
                    if (!A.f380q.m733a(c11001)) {
                        A.f382s.execute(c11001);
                    }
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void logHandledException(Throwable th) {
        try {
            if (!az.m400A().f365b) {
                m27b("logHandledException");
            } else if (!az.m400A().f369f.m772b()) {
                az.m400A().m425b(th);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th2) {
            dx.m777a(th2);
        }
    }

    public static void _logHandledException(String name, String msg, String stack) {
        try {
            new StringBuilder("_logHandledException(name, msg, stack) called: ").append(name).append(" ").append(msg).append(" ").append(stack);
            dx.m778b();
            if (name == null || msg == null || stack == null) {
                dx.m782c("Calling logHandledException with null parameter(s). Nothing will be reported to Crittercism");
            } else {
                logHandledException(new PluginException(name, msg, stack));
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void _logHandledException(String name, String msg, String[] classStackElems, String[] methodStackElems, String[] fileStackElems, int[] lineNumberStackElems) {
        try {
            new StringBuilder("_logHandledException(name, msg, classes, methods, files, lines) called: ").append(name);
            dx.m778b();
            if (name == null || msg == null || classStackElems == null) {
                dx.m782c("Calling logHandledException with null parameter(s). Nothing will be reported to Crittercism");
            } else {
                logHandledException(new PluginException(name, msg, classStackElems, methodStackElems, fileStackElems, lineNumberStackElems));
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void logNetworkRequest(String method, URL url, long responseTime, long bytesRead, long bytesSent, int responseCode, Exception error) {
        try {
            long currentTimeMillis = System.currentTimeMillis() - responseTime;
            if (!az.m400A().f365b) {
                m27b("logEndpoint");
            } else if (!az.m400A().f369f.m772b()) {
                az.m400A().m419a(method, url, responseTime, bytesRead, bytesSent, responseCode, error, currentTimeMillis);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static boolean didCrashOnLastLoad() {
        boolean z = false;
        try {
            az A = az.m400A();
            if (!A.f365b) {
                m27b("didCrashOnLoad");
            } else if (!A.m404B()) {
                A.f368e.block();
                z = dq.f746a;
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
        return z;
    }

    @Deprecated
    public static void _logCrashException(Throwable exception) {
        try {
            new StringBuilder("_logCrashException(Throwable) called with throwable: ").append(exception.getMessage());
            dx.m778b();
            if (az.m400A().f365b) {
                az.m400A().m420a(exception);
            } else {
                m27b("_logCrashException");
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void _logCrashException(String name, String msg, String stack) {
        if (name == null || msg == null || stack == null) {
            try {
                dx.m779b("Unable to handle application crash. Missing parameters");
                return;
            } catch (Throwable th) {
                dx.m777a(th);
                return;
            }
        }
        new StringBuilder("_logCrashException(name, msg, stack) called: ").append(name).append(" ").append(msg).append(" ").append(stack);
        dx.m778b();
        _logCrashException(new PluginException(name, msg, stack));
    }

    public static void _logCrashException(String msg, String stack) {
        if (msg == null || stack == null) {
            try {
                dx.m779b("Unable to handle application crash. Missing parameters");
                return;
            } catch (Throwable th) {
                dx.m777a(th);
                return;
            }
        }
        new StringBuilder("_logCrashException(msg, stack) called: ").append(msg).append(" ").append(stack);
        dx.m778b();
        _logCrashException(new PluginException(msg, stack));
    }

    public static void _logCrashException(String name, String msg, String[] classStackElems, String[] methodStackElems, String[] fileStackElems, int[] lineNumberStackElems) {
        try {
            new StringBuilder("_logCrashException(String, String, String[], String[], String[], int[]) called: ").append(name).append(" ").append(msg);
            dx.m778b();
            if (name == null || msg == null || classStackElems == null || methodStackElems == null || fileStackElems == null || lineNumberStackElems == null) {
                dx.m779b("Unable to handle application crash. Missing parameters");
                return;
            }
            if (m26a(classStackElems, methodStackElems, fileStackElems, lineNumberStackElems)) {
                _logCrashException(new PluginException(name, msg, classStackElems, methodStackElems, fileStackElems, lineNumberStackElems));
            } else {
                dx.m779b("Unable to handle application crash. Missing stack elements");
            }
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    private static boolean m26a(Object... objArr) {
        if (objArr.length <= 0 || objArr[0] == null) {
            return false;
        }
        int length = Array.getLength(objArr[0]);
        for (int i = 1; i < objArr.length; i++) {
            if (objArr[i] == null) {
                return false;
            }
            if (Array.getLength(objArr[i]) != length) {
                return false;
            }
        }
        return true;
    }

    public static boolean getOptOutStatus() {
        boolean z = false;
        try {
            az A = az.m400A();
            if (A.f365b) {
                z = A.m404B();
            } else {
                m27b("getOptOutStatus");
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
        return z;
    }

    public static void setOptOutStatus(boolean z) {
        try {
            if (az.m400A().f365b) {
                az A = az.m400A();
                Runnable dkVar = new dk(A.f366c, A, z);
                if (!A.f380q.m733a(dkVar)) {
                    A.f382s.execute(dkVar);
                    return;
                }
                return;
            }
            m27b("setOptOutStatus");
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void setMetadata(JSONObject jSONObject) {
        try {
            if (az.m400A().f365b) {
                az.m400A().m421a(jSONObject);
            } else {
                m27b("setMetadata");
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void setUsername(String str) {
        try {
            if (!az.m400A().f365b) {
                m27b("setUsername");
            } else if (str == null) {
                dx.m782c("Crittercism.setUsername() given invalid parameter: null");
            } else {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt(Ui.USER_NAME, str);
                    az.m400A().m421a(jSONObject);
                } catch (Throwable e) {
                    dx.m780b("Crittercism.setUsername()", e);
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable e3) {
            dx.m777a(e3);
        }
    }

    public static void leaveBreadcrumb(String str) {
        try {
            if (!az.m400A().f365b) {
                m27b("leaveBreadcrumb");
            } else if (str == null) {
                dx.m780b("Cannot leave null breadcrumb", new NullPointerException());
            } else {
                az A = az.m400A();
                if (!A.f369f.m772b()) {
                    Runnable c11067 = new C11067(A, new cf(str, C1156a.NORMAL));
                    if (!A.f380q.m733a(c11067)) {
                        new StringBuilder("SENDING ").append(str).append(" TO EXECUTOR");
                        dx.m778b();
                        A.f382s.execute(c11067);
                    }
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void beginTransaction(String str) {
        try {
            az A = az.m400A();
            if (A.f383t) {
                dx.m782c("Transactions are not supported for services. Ignoring Crittercism.beginTransaction() call for " + str + ".");
                return;
            }
            Transaction a = Transaction.m34a(str);
            if (a instanceof bg) {
                synchronized (A.f389z) {
                    Transaction transaction = (Transaction) A.f389z.remove(str);
                    if (transaction != null) {
                        ((bg) transaction).m522h();
                    }
                    if (A.f389z.size() > 50) {
                        dx.m782c("Crittercism only supports a maximum of 50 concurrent transactions. Ignoring Crittercism.beginTransaction() call for " + str + ".");
                        return;
                    }
                    A.f389z.put(str, a);
                    a.m35a();
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void endTransaction(String str) {
        try {
            az A = az.m400A();
            if (A.f383t) {
                dx.m782c("Transactions are not supported for services. Ignoring Crittercism.endTransaction() call for " + str + ".");
                return;
            }
            Transaction transaction;
            synchronized (A.f389z) {
                transaction = (Transaction) A.f389z.remove(str);
            }
            if (transaction != null) {
                transaction.m37b();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void failTransaction(String str) {
        try {
            az A = az.m400A();
            if (A.f383t) {
                dx.m782c("Transactions are not supported for services. Ignoring Crittercism.failTransaction() call for " + str + ".");
                return;
            }
            Transaction transaction;
            synchronized (A.f389z) {
                transaction = (Transaction) A.f389z.remove(str);
            }
            if (transaction != null) {
                transaction.m38c();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static void setTransactionValue(String str, int i) {
        try {
            az A = az.m400A();
            if (A.f383t) {
                dx.m782c("Transactions are not supported for services. Ignoring Crittercism.setTransactionValue() call for " + str + ".");
                return;
            }
            synchronized (A.f389z) {
                Transaction transaction = (Transaction) A.f389z.get(str);
                if (transaction != null) {
                    transaction.m36a(i);
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
        }
    }

    public static int getTransactionValue(String str) {
        try {
            return az.m400A().m422b(str);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dx.m777a(th);
            return -1;
        }
    }

    public static void updateLocation(Location curLocation) {
        if (!az.m400A().f365b) {
            m27b("updateLocation");
        } else if (curLocation == null) {
            dx.m780b("Cannot leave null location", new NullPointerException());
        } else {
            bc.m474a(curLocation);
        }
    }

    private static void m27b(String str) {
        dx.m780b("Must initialize Crittercism before calling " + Crittercism.class.getName() + "." + str + "().  Request is being ignored...", new IllegalStateException());
    }
}
