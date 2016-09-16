package crittercism.android;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.voxelbusters.nativeplugins.defines.Keys.WebView;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.util.CharArrayBuffer;

public abstract class ak extends af {
    boolean f309d;
    int f310e;
    boolean f311f;
    private boolean f312g;
    private boolean f313h;

    protected abstract af m320g();

    public ak(af afVar) {
        super(afVar);
        this.f309d = false;
        this.f312g = false;
        this.f313h = false;
        this.f311f = false;
    }

    public final af m316b() {
        if (this.f313h) {
            return m320g();
        }
        this.f300b.clear();
        return this;
    }

    public final af m317c() {
        this.f300b.clear();
        return new ar(this);
    }

    public final boolean m315a(CharArrayBuffer charArrayBuffer) {
        int length = this.f300b.length();
        length = (length == 0 || (length == 1 && this.f300b.charAt(0) == '\r')) ? true : 0;
        if (length != 0) {
            this.f313h = true;
            return true;
        }
        try {
            Header parseHeader = BasicLineParser.DEFAULT.parseHeader(charArrayBuffer);
            if (!this.f309d && parseHeader.getName().equalsIgnoreCase("content-length")) {
                length = Integer.parseInt(parseHeader.getValue());
                if (length < 0) {
                    return false;
                }
                this.f309d = true;
                this.f310e = length;
                return true;
            } else if (parseHeader.getName().equalsIgnoreCase("transfer-encoding")) {
                this.f311f = parseHeader.getValue().equalsIgnoreCase("chunked");
                return true;
            } else if (this.f312g || !parseHeader.getName().equalsIgnoreCase(WebView.HOST)) {
                return true;
            } else {
                String value = parseHeader.getValue();
                if (value == null) {
                    return true;
                }
                this.f312g = true;
                this.f299a.m324a(value);
                return true;
            }
        } catch (ParseException e) {
            return false;
        } catch (NumberFormatException e2) {
            return false;
        }
    }

    protected final int m318d() {
        return 32;
    }

    protected final int m319e() {
        return AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
    }
}
