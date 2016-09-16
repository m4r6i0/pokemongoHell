package com.upsight.android.unity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import spacemadness.com.lunarconsole.BuildConfig;

public class UnityBridge {
    protected static final String MANAGER_NAME = "UpsightManager";
    protected static final String TAG = "Upsight";

    /* renamed from: com.upsight.android.unity.UnityBridge.1 */
    static class C10231 implements Runnable {
        final /* synthetic */ Runnable val$r;

        C10231(Runnable runnable) {
            this.val$r = runnable;
        }

        public void run() {
            try {
                this.val$r.run();
            } catch (Exception e) {
                Log.e(UnityBridge.TAG, "Exception running command on UI thread: " + e.getMessage());
            }
        }
    }

    @Nullable
    public static Activity getActivity() {
        return UnityPlayer.currentActivity;
    }

    public static void UnitySendMessage(@NonNull String method) {
        UnityPlayer.UnitySendMessage(MANAGER_NAME, method, BuildConfig.FLAVOR);
    }

    public static void UnitySendMessage(@NonNull String method, @Nullable String parameter) {
        String str = MANAGER_NAME;
        if (parameter == null) {
            parameter = BuildConfig.FLAVOR;
        }
        UnityPlayer.UnitySendMessage(str, method, parameter);
    }

    public static void runSafelyOnUiThread(@NonNull Runnable r) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new C10231(r));
        }
    }
}
