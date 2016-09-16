package com.upsight.mediation.vast.util;

import android.text.TextUtils;
import com.upsight.android.googlepushservices.UpsightPushNotificationBuilderFactory.Default;
import com.upsight.mediation.log.FuseLog;
import com.upsight.mediation.vast.VASTPlayer;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTools {
    private static final String TAG;

    /* renamed from: com.upsight.mediation.vast.util.HttpTools.1 */
    static class C10801 extends Thread {
        final /* synthetic */ VASTPlayer val$currentPlayer;
        final /* synthetic */ String val$type;
        final /* synthetic */ String val$url;

        C10801(String str, String str2, VASTPlayer vASTPlayer) {
            this.val$url = str;
            this.val$type = str2;
            this.val$currentPlayer = vASTPlayer;
        }

        public void run() {
            HttpURLConnection conn = null;
            try {
                FuseLog.m239v(HttpTools.TAG, "connection to URL:" + this.val$url);
                URL httpUrl = new URL(this.val$url);
                HttpURLConnection.setFollowRedirects(true);
                conn = (HttpURLConnection) httpUrl.openConnection();
                conn.setConnectTimeout(Default.HTTP_REQUEST_TIMEOUT_MS);
                conn.setRequestProperty("Connection", "close");
                conn.setRequestMethod("GET");
                int code = conn.getResponseCode();
                FuseLog.m239v(HttpTools.TAG, "response code:" + code + ", for URL:" + this.val$url);
                if (code < 200 && code > 226 && this.val$type.equals("impression")) {
                    this.val$currentPlayer.listener.vastError(VASTPlayer.ERROR_UNDEFINED);
                }
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Exception e) {
                        FuseLog.m240w(HttpTools.TAG, e.toString());
                    }
                }
            } catch (Exception e2) {
                FuseLog.m240w(HttpTools.TAG, this.val$url + ": " + e2.getMessage() + UpsightEndpoint.SIGNED_MESSAGE_SEPARATOR + e2.toString());
                if (this.val$type.equals("impression")) {
                    this.val$currentPlayer.listener.vastError(VASTPlayer.ERROR_UNDEFINED);
                }
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Exception e22) {
                        FuseLog.m240w(HttpTools.TAG, e22.toString());
                    }
                }
            } catch (Throwable th) {
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Exception e222) {
                        FuseLog.m240w(HttpTools.TAG, e222.toString());
                    }
                }
            }
        }
    }

    static {
        TAG = HttpTools.class.getName();
    }

    public static void httpGetURL(String type, String url, VASTPlayer currentPlayer) {
        if (!TextUtils.isEmpty(url)) {
            new C10801(url, type, currentPlayer).start();
        }
    }
}
