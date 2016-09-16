package com.unity3d.player;

/* renamed from: com.unity3d.player.v */
final class C0886v {
    private static boolean f229a;
    private boolean f230b;
    private boolean f231c;
    private boolean f232d;
    private boolean f233e;

    static {
        f229a = false;
    }

    C0886v() {
        this.f230b = !C0878q.f202h;
        this.f231c = false;
        this.f232d = false;
        this.f233e = true;
    }

    static void m191a() {
        f229a = true;
    }

    static void m192b() {
        f229a = false;
    }

    static boolean m193c() {
        return f229a;
    }

    final void m194a(boolean z) {
        this.f231c = z;
    }

    final void m195b(boolean z) {
        this.f233e = z;
    }

    final void m196c(boolean z) {
        this.f232d = z;
    }

    final void m197d() {
        this.f230b = true;
    }

    final boolean m198e() {
        return this.f233e;
    }

    final boolean m199f() {
        return f229a && this.f231c && this.f230b && !this.f233e && !this.f232d;
    }

    final boolean m200g() {
        return this.f232d;
    }

    public final String toString() {
        return super.toString();
    }
}
