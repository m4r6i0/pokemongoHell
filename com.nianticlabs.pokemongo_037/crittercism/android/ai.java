package crittercism.android;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import org.apache.http.util.CharArrayBuffer;

public final class ai extends af {
    private int f308d;

    public ai(af afVar) {
        super(afVar);
        this.f308d = -1;
    }

    public final af m302b() {
        int i = this.f308d;
        if (this.f308d == 0) {
            return new aq(this);
        }
        this.f300b.clear();
        return new ah(this, this.f308d);
    }

    public final af m303c() {
        return as.f318d;
    }

    public final boolean m301a(CharArrayBuffer charArrayBuffer) {
        int indexOf = charArrayBuffer.indexOf(59);
        int length = charArrayBuffer.length();
        if (indexOf <= 0) {
            indexOf = length;
        }
        try {
            this.f308d = Integer.parseInt(charArrayBuffer.substringTrimmed(0, indexOf), 16);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected final int m304d() {
        return 16;
    }

    protected final int m305e() {
        return AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
    }

    public final void m306f() {
        this.f299a.m327b(m275a());
        this.f299a.m323a(as.f318d);
    }
}
