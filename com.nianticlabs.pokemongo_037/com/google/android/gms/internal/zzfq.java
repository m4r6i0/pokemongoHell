package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.voxelbusters.nativeplugins.defines.Keys.Mime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzgr
public class zzfq implements zzfo {
    private final Context mContext;
    final Set<WebView> zzCr;

    /* renamed from: com.google.android.gms.internal.zzfq.1 */
    class C05151 implements Runnable {
        final /* synthetic */ String zzCs;
        final /* synthetic */ String zzCt;
        final /* synthetic */ zzfq zzCu;

        /* renamed from: com.google.android.gms.internal.zzfq.1.1 */
        class C05141 extends WebViewClient {
            final /* synthetic */ C05151 zzCv;
            final /* synthetic */ WebView zzsk;

            C05141(C05151 c05151, WebView webView) {
                this.zzCv = c05151;
                this.zzsk = webView;
            }

            public void onPageFinished(WebView view, String url) {
                zzb.zzaF("Loading assets have finished");
                this.zzCv.zzCu.zzCr.remove(this.zzsk);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                zzb.zzaH("Loading assets have failed.");
                this.zzCv.zzCu.zzCr.remove(this.zzsk);
            }
        }

        C05151(zzfq com_google_android_gms_internal_zzfq, String str, String str2) {
            this.zzCu = com_google_android_gms_internal_zzfq;
            this.zzCs = str;
            this.zzCt = str2;
        }

        public void run() {
            WebView zzfh = this.zzCu.zzfh();
            zzfh.setWebViewClient(new C05141(this, zzfh));
            this.zzCu.zzCr.add(zzfh);
            zzfh.loadDataWithBaseURL(this.zzCs, this.zzCt, Mime.HTML_TEXT, "UTF-8", null);
            zzb.zzaF("Fetching assets finished.");
        }
    }

    public zzfq(Context context) {
        this.zzCr = Collections.synchronizedSet(new HashSet());
        this.mContext = context;
    }

    public void zza(String str, String str2, String str3) {
        zzb.zzaF("Fetching assets for the given html");
        zzid.zzIE.post(new C05151(this, str2, str3));
    }

    public WebView zzfh() {
        WebView webView = new WebView(this.mContext);
        webView.getSettings().setJavaScriptEnabled(true);
        return webView;
    }
}
