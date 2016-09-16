package com.upsight.android.unity;

import android.util.Log;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.session.UpsightSessionCallbacks;
import com.upsight.android.managedvariables.experience.UpsightUserExperience;
import com.upsight.android.managedvariables.experience.UpsightUserExperience.Handler;
import java.util.List;
import org.json.JSONArray;

public class UnitySessionCallbacks implements UpsightSessionCallbacks {
    protected static final String TAG = "UnitySessionCallbacks";
    private static boolean mShouldSynchronizeManagedVariables;

    /* renamed from: com.upsight.android.unity.UnitySessionCallbacks.1 */
    class C10241 implements Handler {
        C10241() {
        }

        public boolean onReceive() {
            return UnitySessionCallbacks.mShouldSynchronizeManagedVariables;
        }

        public void onSynchronize(List<String> tags) {
            Log.i(UnitySessionCallbacks.TAG, "onSynchronize");
            JSONArray json = new JSONArray();
            for (String t : tags) {
                json.put(t);
            }
            UnityBridge.UnitySendMessage("managedVariablesDidSynchronize", json.toString());
        }
    }

    static {
        mShouldSynchronizeManagedVariables = true;
    }

    public static void setShouldSynchronizeManagedVariables(boolean shouldSynchronizeManagedVariables) {
        mShouldSynchronizeManagedVariables = shouldSynchronizeManagedVariables;
    }

    public void onStart(UpsightContext upsight) {
        UpsightUserExperience.registerHandler(upsight, new C10241());
    }
}
