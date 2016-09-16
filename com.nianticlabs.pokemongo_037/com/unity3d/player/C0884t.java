package com.unity3d.player;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.unity3d.player.t */
final class C0884t {
    public static C0884t f223a;
    private final ViewGroup f224b;
    private Set f225c;
    private View f226d;
    private View f227e;

    C0884t(ViewGroup viewGroup) {
        this.f225c = new HashSet();
        this.f224b = viewGroup;
        f223a = this;
    }

    private void m183e(View view) {
        this.f224b.addView(view, this.f224b.getChildCount());
    }

    private void m184f(View view) {
        this.f224b.removeView(view);
        this.f224b.requestLayout();
    }

    public final Context m185a() {
        return this.f224b.getContext();
    }

    public final void m186a(View view) {
        this.f225c.add(view);
        if (this.f226d != null) {
            m183e(view);
        }
    }

    public final void m187b(View view) {
        this.f225c.remove(view);
        if (this.f226d != null) {
            m184f(view);
        }
    }

    public final void m188c(View view) {
        if (this.f226d != view) {
            this.f226d = view;
            this.f224b.addView(view);
            for (View e : this.f225c) {
                m183e(e);
            }
            if (this.f227e != null) {
                this.f227e.setVisibility(4);
            }
        }
    }

    public final void m189d(View view) {
        if (this.f226d == view) {
            for (View f : this.f225c) {
                m184f(f);
            }
            this.f224b.removeView(view);
            this.f226d = null;
            if (this.f227e != null) {
                this.f227e.setVisibility(0);
            }
        }
    }
}
