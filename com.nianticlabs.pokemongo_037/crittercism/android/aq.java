package crittercism.android;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import org.apache.http.util.CharArrayBuffer;

public final class aq extends af {
    private boolean f316d;

    public aq(af afVar) {
        super(afVar);
        this.f316d = false;
    }

    public final af m342b() {
        if (this.f316d) {
            this.f299a.m327b(m275a());
            return this.f299a.m326b();
        }
        this.f300b.clear();
        return this;
    }

    public final af m343c() {
        this.f300b.clear();
        return new ar(this);
    }

    public final boolean m341a(CharArrayBuffer charArrayBuffer) {
        boolean z = false;
        if (charArrayBuffer.substringTrimmed(0, charArrayBuffer.length()).length() == 0) {
            z = true;
        }
        this.f316d = z;
        return true;
    }

    protected final int m344d() {
        return 8;
    }

    protected final int m345e() {
        return AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
    }
}
