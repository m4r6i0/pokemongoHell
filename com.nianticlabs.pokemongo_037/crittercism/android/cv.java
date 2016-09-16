package crittercism.android;

public final class cv {
    private long f693a;
    private long f694b;

    public cv(long j) {
        this.f693a = 0;
        this.f694b = j;
    }

    public final synchronized boolean m715a() {
        return System.nanoTime() - this.f693a > this.f694b;
    }

    public final synchronized void m716b() {
        this.f693a = System.nanoTime();
    }
}
