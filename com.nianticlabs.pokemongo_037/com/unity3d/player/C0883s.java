package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/* renamed from: com.unity3d.player.s */
public final class C0883s extends Dialog implements TextWatcher, OnClickListener {
    private static int f219c;
    private static int f220d;
    private Context f221a;
    private UnityPlayer f222b;

    /* renamed from: com.unity3d.player.s.1 */
    class C08801 implements OnFocusChangeListener {
        final /* synthetic */ C0883s f216a;

        C08801(C0883s c0883s) {
            this.f216a = c0883s;
        }

        public final void onFocusChange(View view, boolean z) {
            if (z) {
                this.f216a.getWindow().setSoftInputMode(5);
            }
        }
    }

    /* renamed from: com.unity3d.player.s.2 */
    class C08812 extends EditText {
        final /* synthetic */ C0883s f217a;

        C08812(C0883s c0883s, Context context) {
            this.f217a = c0883s;
            super(context);
        }

        public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i != 4) {
                return i != 84 ? super.onKeyPreIme(i, keyEvent) : true;
            } else {
                this.f217a.m180a(this.f217a.m176a(), true);
                return true;
            }
        }

        public final void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z) {
                ((InputMethodManager) this.f217a.f221a.getSystemService("input_method")).showSoftInput(this, 0);
            }
        }
    }

    /* renamed from: com.unity3d.player.s.3 */
    class C08823 implements OnEditorActionListener {
        final /* synthetic */ C0883s f218a;

        C08823(C0883s c0883s) {
            this.f218a = c0883s;
        }

        public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6) {
                this.f218a.m180a(this.f218a.m176a(), false);
            }
            return false;
        }
    }

    static {
        f219c = 1627389952;
        f220d = -1;
    }

    public C0883s(Context context, UnityPlayer unityPlayer, String str, int i, boolean z, boolean z2, boolean z3, String str2) {
        super(context);
        this.f221a = null;
        this.f222b = null;
        this.f221a = context;
        this.f222b = unityPlayer;
        getWindow().setGravity(80);
        getWindow().requestFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(createSoftInputView());
        getWindow().setLayout(-1, -2);
        getWindow().clearFlags(2);
        EditText editText = (EditText) findViewById(1057292289);
        Button button = (Button) findViewById(1057292290);
        m178a(editText, str, i, z, z2, z3, str2);
        button.setOnClickListener(this);
        editText.setOnFocusChangeListener(new C08801(this));
    }

    private static int m175a(int i, boolean z, boolean z2, boolean z3) {
        int i2 = 0;
        int i3 = (z2 ? AccessibilityNodeInfoCompat.ACTION_SET_SELECTION : 0) | (z ? AccessibilityNodeInfoCompat.ACTION_PASTE : 0);
        if (z3) {
            i2 = AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
        }
        i2 |= i3;
        return (i < 0 || i > 7) ? i2 : i2 | new int[]{1, 16385, 12290, 17, 2, 3, 97, 33}[i];
    }

    private String m176a() {
        EditText editText = (EditText) findViewById(1057292289);
        return editText == null ? null : editText.getText().toString().trim();
    }

    private void m178a(EditText editText, String str, int i, boolean z, boolean z2, boolean z3, String str2) {
        editText.setImeOptions(6);
        editText.setText(str);
        editText.setHint(str2);
        editText.setHintTextColor(f219c);
        editText.setInputType(C0883s.m175a(i, z, z2, z3));
        editText.addTextChangedListener(this);
        editText.setClickable(true);
        if (!z2) {
            editText.selectAll();
        }
    }

    private void m180a(String str, boolean z) {
        Selection.removeSelection(((EditText) findViewById(1057292289)).getEditableText());
        this.f222b.reportSoftInputStr(str, 1, z);
    }

    public final void m182a(String str) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
    }

    public final void afterTextChanged(Editable editable) {
        this.f222b.reportSoftInputStr(editable.toString(), 0, false);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected final View createSoftInputView() {
        View relativeLayout = new RelativeLayout(this.f221a);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(f220d);
        View c08812 = new C08812(this, this.f221a);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        c08812.setLayoutParams(layoutParams);
        c08812.setId(1057292289);
        relativeLayout.addView(c08812);
        c08812 = new Button(this.f221a);
        c08812.setText(this.f221a.getResources().getIdentifier("ok", "string", "android"));
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(11);
        c08812.setLayoutParams(layoutParams);
        c08812.setId(1057292290);
        c08812.setBackgroundColor(0);
        relativeLayout.addView(c08812);
        ((EditText) relativeLayout.findViewById(1057292289)).setOnEditorActionListener(new C08823(this));
        relativeLayout.setPadding(16, 16, 16, 16);
        return relativeLayout;
    }

    public final void onBackPressed() {
        m180a(m176a(), true);
    }

    public final void onClick(View view) {
        m180a(m176a(), false);
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
