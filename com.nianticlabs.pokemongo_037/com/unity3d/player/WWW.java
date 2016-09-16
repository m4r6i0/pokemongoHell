package com.unity3d.player;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.mopub.volley.DefaultRetryPolicy;
import com.upsight.mediation.vast.VASTPlayer;
import com.voxelbusters.nativeplugins.defines.Keys.Scheme;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.IOUtils;
import spacemadness.com.lunarconsole.BuildConfig;

class WWW extends Thread {
    private int f135a;
    private int f136b;
    private String f137c;
    private byte[] f138d;
    private Map f139e;

    WWW(int i, String str, byte[] bArr, Map map) {
        this.f136b = i;
        this.f137c = str;
        this.f138d = bArr;
        this.f139e = map;
        this.f135a = 0;
    }

    private static native void doneCallback(int i);

    private static native void errorCallback(int i, String str);

    private static native boolean headerCallback(int i, String str);

    private static native void progressCallback(int i, float f, float f2, double d, int i2);

    private static native boolean readCallback(int i, byte[] bArr, int i2);

    protected boolean headerCallback(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(str2);
        stringBuilder.append("\n\r");
        return headerCallback(this.f136b, stringBuilder.toString());
    }

    protected boolean headerCallback(Map map) {
        if (map == null || map.size() == 0) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            for (String str : (List) entry.getValue()) {
                stringBuilder.append((String) entry.getKey());
                stringBuilder.append(": ");
                stringBuilder.append(str);
                stringBuilder.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            }
            if (entry.getKey() == null) {
                for (String str2 : (List) entry.getValue()) {
                    stringBuilder.append("Status: ");
                    stringBuilder.append(str2);
                    stringBuilder.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                }
            }
        }
        return headerCallback(this.f136b, stringBuilder.toString());
    }

    protected void progressCallback(int i, int i2, int i3, int i4, long j, long j2) {
        float f;
        float f2;
        double d;
        if (i4 > 0) {
            f = ((float) i3) / ((float) i4);
            f2 = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
            double max = ((double) Math.max(i4 - i3, 0)) / ((1000.0d * ((double) i3)) / Math.max((double) (j - j2), 0.1d));
            if (Double.isInfinite(max) || Double.isNaN(max)) {
                max = 0.0d;
            }
            d = max;
        } else if (i2 > 0) {
            f = 0.0f;
            f2 = (float) (i / i2);
            d = 0.0d;
        } else {
            return;
        }
        progressCallback(this.f136b, f2, f, d, i4);
    }

    protected boolean readCallback(byte[] bArr, int i) {
        return readCallback(this.f136b, bArr, i);
    }

    public void run() {
        try {
            runSafe();
        } catch (Throwable th) {
            errorCallback(this.f136b, "Error: " + th.toString());
        }
    }

    public void runSafe() {
        int i = this.f135a + 1;
        this.f135a = i;
        if (i > 5) {
            errorCallback(this.f136b, "Too many redirects");
            return;
        }
        try {
            URL url = new URL(this.f137c);
            URLConnection openConnection = url.openConnection();
            if (!url.getProtocol().equalsIgnoreCase(Scheme.FILE) || url.getHost() == null || url.getHost().length() == 0) {
                int min;
                HttpURLConnection httpURLConnection;
                if (this.f139e != null) {
                    for (Entry entry : this.f139e.entrySet()) {
                        openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (this.f138d != null) {
                    openConnection.setDoOutput(true);
                    try {
                        OutputStream outputStream = openConnection.getOutputStream();
                        int i2 = 0;
                        while (i2 < this.f138d.length) {
                            min = Math.min(1428, this.f138d.length - i2);
                            outputStream.write(this.f138d, i2, min);
                            i2 += min;
                            progressCallback(i2, this.f138d.length, 0, 0, 0, 0);
                        }
                    } catch (Exception e) {
                        errorCallback(this.f136b, e.toString());
                        return;
                    }
                }
                if (openConnection instanceof HttpURLConnection) {
                    httpURLConnection = (HttpURLConnection) openConnection;
                    try {
                        min = httpURLConnection.getResponseCode();
                        Map headerFields = httpURLConnection.getHeaderFields();
                        if (headerFields != null && (min == VASTPlayer.ERROR_WRAPPER_TIMEOUT || min == VASTPlayer.ERROR_EXCEEDED_WRAPPER_LIMIT)) {
                            List list = (List) headerFields.get("Location");
                            if (!(list == null || list.isEmpty())) {
                                httpURLConnection.disconnect();
                                this.f137c = (String) list.get(0);
                                run();
                                return;
                            }
                        }
                    } catch (IOException e2) {
                        errorCallback(this.f136b, e2.toString());
                        return;
                    }
                }
                Map headerFields2 = openConnection.getHeaderFields();
                boolean headerCallback = headerCallback(headerFields2);
                if ((headerFields2 == null || !headerFields2.containsKey("content-length")) && openConnection.getContentLength() != -1) {
                    headerCallback = headerCallback || headerCallback("content-length", String.valueOf(openConnection.getContentLength()));
                }
                if ((headerFields2 == null || !headerFields2.containsKey("content-type")) && openConnection.getContentType() != null) {
                    headerCallback = headerCallback || headerCallback("content-type", openConnection.getContentType());
                }
                if (headerCallback) {
                    errorCallback(this.f136b, this.f137c + " aborted");
                    return;
                }
                int contentLength = openConnection.getContentLength() > 0 ? openConnection.getContentLength() : 0;
                i = (url.getProtocol().equalsIgnoreCase(Scheme.FILE) || url.getProtocol().equalsIgnoreCase("jar")) ? contentLength == 0 ? AccessibilityNodeInfoCompat.ACTION_PASTE : Math.min(contentLength, AccessibilityNodeInfoCompat.ACTION_PASTE) : 1428;
                int i3 = 0;
                try {
                    String str;
                    InputStream inputStream;
                    Object obj;
                    InputStream inputStream2;
                    long currentTimeMillis = System.currentTimeMillis();
                    byte[] bArr = new byte[i];
                    String str2 = BuildConfig.FLAVOR;
                    if (openConnection instanceof HttpURLConnection) {
                        httpURLConnection = (HttpURLConnection) openConnection;
                        InputStream errorStream = httpURLConnection.getErrorStream();
                        str = httpURLConnection.getResponseCode() + ": " + httpURLConnection.getResponseMessage();
                        inputStream = errorStream;
                    } else {
                        str = str2;
                        inputStream = null;
                    }
                    if (inputStream == null) {
                        obj = null;
                        inputStream2 = openConnection.getInputStream();
                    } else {
                        inputStream2 = inputStream;
                        i = 1;
                    }
                    for (min = 0; min != -1; min = inputStream2.read(bArr)) {
                        if (readCallback(bArr, min)) {
                            errorCallback(this.f136b, this.f137c + " aborted");
                            return;
                        }
                        if (obj == null) {
                            i3 += min;
                            progressCallback(0, 0, i3, contentLength, System.currentTimeMillis(), currentTimeMillis);
                        }
                    }
                    if (obj != null) {
                        errorCallback(this.f136b, str);
                    }
                    progressCallback(0, 0, i3, i3, 0, 0);
                    doneCallback(this.f136b);
                    return;
                } catch (Exception e3) {
                    errorCallback(this.f136b, e3.toString());
                    return;
                }
            }
            errorCallback(this.f136b, url.getHost() + url.getFile() + " is not an absolute path!");
        } catch (MalformedURLException e4) {
            errorCallback(this.f136b, e4.toString());
        } catch (IOException e22) {
            errorCallback(this.f136b, e22.toString());
        }
    }
}
