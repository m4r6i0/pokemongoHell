package com.upsight.mediation.mraid.internal;

import com.upsight.mediation.log.FuseLog;

public class MRAIDLog {
    private static final String TAG = "MRAID";

    public static void m242d(String msg) {
        FuseLog.m235d(TAG, msg);
    }

    public static void m244e(String msg) {
        FuseLog.m236e(TAG, msg);
    }

    public static void m246i(String msg) {
        FuseLog.m238i(TAG, msg);
    }

    public static void m248v(String msg) {
        FuseLog.m239v(TAG, msg);
    }

    public static void m250w(String msg) {
        FuseLog.m240w(TAG, msg);
    }

    public static void m243d(String subTag, String msg) {
        FuseLog.m235d(TAG, "[" + subTag + "] " + msg);
    }

    public static void m245e(String subTag, String msg) {
        FuseLog.m236e(TAG, "[" + subTag + "] " + msg);
    }

    public static void m247i(String subTag, String msg) {
        FuseLog.m238i(TAG, "[" + subTag + "] " + msg);
    }

    public static void m249v(String subTag, String msg) {
        FuseLog.m239v(TAG, "[" + subTag + "] " + msg);
    }

    public static void m251w(String subTag, String msg) {
        FuseLog.m240w(TAG, "[" + subTag + "] " + msg);
    }
}
