package crittercism.android;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import crittercism.android.bj.C1121a;
import crittercism.android.bl.C1122a;
import crittercism.android.ce.C1155a;

public final class av implements ActivityLifecycleCallbacks {
    private int f321a;
    private boolean f322b;
    private boolean f323c;
    private boolean f324d;
    private Context f325e;
    private az f326f;
    private bd f327g;

    public av(Context context, az azVar) {
        this.f321a = 0;
        this.f322b = false;
        this.f323c = false;
        this.f324d = false;
        this.f325e = context;
        this.f326f = azVar;
        this.f327g = new bd(context, azVar);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        if (activity != null) {
            try {
                if (this.f322b) {
                    dx.m778b();
                    this.f322b = false;
                } else if (this.f321a == 0) {
                    this.f326f.m414a(new bl(C1122a.FOREGROUND));
                    bg.m503f();
                    if (!this.f324d) {
                        this.f324d = true;
                        C1111b a = new C1161d(this.f325e).m717a();
                        if (a != C1111b.UNKNOWN) {
                            if (a == C1111b.NOT_CONNECTED) {
                                this.f326f.m414a(new ce(C1155a.INTERNET_DOWN));
                            } else {
                                this.f326f.m414a(new ce(C1155a.INTERNET_UP));
                            }
                        }
                    }
                } else {
                    this.f326f.m414a(new bj(C1121a.ACTIVATED, activity.getClass().getName()));
                }
                this.f321a++;
                IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                activity.registerReceiver(this.f327g, intentFilter);
                this.f323c = true;
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                dx.m777a(th);
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        if (activity != null) {
            try {
                if (this.f323c) {
                    activity.unregisterReceiver(this.f327g);
                    this.f323c = false;
                }
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                dx.m777a(th);
            }
        }
    }

    public final void onActivityStopped(Activity activity) {
        if (activity != null) {
            try {
                this.f321a--;
                if (activity.isChangingConfigurations()) {
                    dx.m778b();
                    this.f322b = true;
                } else if (this.f321a == 0) {
                    this.f326f.m414a(new bl(C1122a.BACKGROUND));
                    bg.m495a(this.f326f);
                } else {
                    this.f326f.m414a(new bj(C1121a.DEACTIVATED, activity.getClass().getName()));
                }
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                dx.m777a(th);
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }
}
