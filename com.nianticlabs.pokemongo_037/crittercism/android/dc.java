package crittercism.android;

import com.crittercism.app.CrittercismConfig;
import com.mopub.volley.DefaultRetryPolicy;
import com.voxelbusters.nativeplugins.defines.Keys.Mime;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public final class dc {
    private static SSLSocketFactory f698a;
    private URL f699b;
    private Map f700c;
    private int f701d;
    private boolean f702e;
    private boolean f703f;
    private String f704g;
    private boolean f705h;
    private int f706i;

    static {
        f698a = null;
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, null, null);
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            if (socketFactory == null) {
                return;
            }
            if (socketFactory instanceof ab) {
                f698a = ((ab) socketFactory).m261a();
            } else {
                f698a = socketFactory;
            }
        } catch (GeneralSecurityException e) {
            f698a = null;
        }
    }

    public dc(URL url) {
        this.f700c = new HashMap();
        this.f701d = 0;
        this.f702e = true;
        this.f703f = true;
        this.f704g = "POST";
        this.f705h = false;
        this.f706i = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        this.f699b = url;
        this.f700c.put("User-Agent", Arrays.asList(new String[]{CrittercismConfig.API_VERSION}));
        this.f700c.put("Content-Type", Arrays.asList(new String[]{"application/json"}));
        this.f700c.put("Accept", Arrays.asList(new String[]{Mime.PLAIN_TEXT, "application/json"}));
    }

    public final HttpURLConnection m721a() {
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.f699b.openConnection();
        for (Entry entry : this.f700c.entrySet()) {
            for (String addRequestProperty : (List) entry.getValue()) {
                httpURLConnection.addRequestProperty((String) entry.getKey(), addRequestProperty);
            }
        }
        httpURLConnection.setConnectTimeout(this.f706i);
        httpURLConnection.setReadTimeout(this.f706i);
        httpURLConnection.setDoInput(this.f702e);
        httpURLConnection.setDoOutput(this.f703f);
        if (this.f705h) {
            httpURLConnection.setChunkedStreamingMode(this.f701d);
        }
        httpURLConnection.setRequestMethod(this.f704g);
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            if (f698a != null) {
                httpsURLConnection.setSSLSocketFactory(f698a);
            } else {
                throw new GeneralSecurityException();
            }
        }
        return httpURLConnection;
    }
}
