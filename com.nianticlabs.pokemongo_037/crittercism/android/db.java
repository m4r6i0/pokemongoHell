package crittercism.android;

import java.net.MalformedURLException;
import java.net.URL;

public final class db {
    private String f696a;
    private String f697b;

    public db(String str, String str2) {
        str.endsWith("/");
        str2.startsWith("/");
        this.f696a = str;
        this.f697b = str2;
    }

    public final URL m720a() {
        try {
            return new URL(this.f696a + this.f697b);
        } catch (MalformedURLException e) {
            new StringBuilder("Invalid url: ").append(this.f696a).append(this.f697b);
            URL url = null;
            dx.m778b();
            return url;
        }
    }
}
