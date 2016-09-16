package crittercism.android;

public final class ao extends ak {
    private int f314g;

    public ao(af afVar, int i) {
        super(afVar);
        this.f314g = i;
    }

    protected final af m335g() {
        Object obj = (this.f299a.m328c().equals("HEAD") || ((this.f314g >= 100 && this.f314g <= 199) || this.f314g == 204 || this.f314g == 304)) ? 1 : null;
        if (obj != null) {
            this.f299a.m327b(m275a());
            return this.f299a.m326b();
        } else if (this.f311f) {
            return new ai(this);
        } else {
            if (this.f309d) {
                if (this.f310e > 0) {
                    return new ag(this, this.f310e);
                }
                this.f299a.m327b(m275a());
                return this.f299a.m326b();
            } else if (!this.f299a.m328c().equals("CONNECT")) {
                return new aj(this);
            } else {
                this.f299a.m327b(m275a());
                return this.f299a.m326b();
            }
        }
    }
}
