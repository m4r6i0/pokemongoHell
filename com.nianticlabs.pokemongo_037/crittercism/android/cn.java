package crittercism.android;

public final class cn {
    public int f655a;
    public int f656b;

    public cn(Throwable th) {
        this.f655a = co.Android.ordinal();
        this.f656b = cm.OK.ordinal();
        if (th != null) {
            this.f655a = co.m692a(th);
            if (this.f655a == co.Android.ordinal()) {
                this.f656b = cm.m689a(th).m691a();
            } else {
                this.f656b = Integer.parseInt(th.getMessage());
            }
        }
    }
}
