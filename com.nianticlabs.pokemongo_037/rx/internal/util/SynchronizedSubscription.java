package rx.internal.util;

import rx.Subscription;

public class SynchronizedSubscription implements Subscription {
    private final Subscription f920s;

    public SynchronizedSubscription(Subscription s) {
        this.f920s = s;
    }

    public synchronized void unsubscribe() {
        this.f920s.unsubscribe();
    }

    public synchronized boolean isUnsubscribed() {
        return this.f920s.isUnsubscribed();
    }
}
