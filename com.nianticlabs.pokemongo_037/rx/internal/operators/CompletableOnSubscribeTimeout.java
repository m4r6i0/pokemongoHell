package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Completable;
import rx.Completable.CompletableOnSubscribe;
import rx.Completable.CompletableSubscriber;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.CompositeSubscription;

public final class CompletableOnSubscribeTimeout implements CompletableOnSubscribe {
    final Completable other;
    final Scheduler scheduler;
    final Completable source;
    final long timeout;
    final TimeUnit unit;

    /* renamed from: rx.internal.operators.CompletableOnSubscribeTimeout.1 */
    class C13211 implements Action0 {
        final /* synthetic */ AtomicBoolean val$once;
        final /* synthetic */ CompletableSubscriber val$s;
        final /* synthetic */ CompositeSubscription val$set;

        /* renamed from: rx.internal.operators.CompletableOnSubscribeTimeout.1.1 */
        class C13201 implements CompletableSubscriber {
            C13201() {
            }

            public void onSubscribe(Subscription d) {
                C13211.this.val$set.add(d);
            }

            public void onError(Throwable e) {
                C13211.this.val$set.unsubscribe();
                C13211.this.val$s.onError(e);
            }

            public void onCompleted() {
                C13211.this.val$set.unsubscribe();
                C13211.this.val$s.onCompleted();
            }
        }

        C13211(AtomicBoolean atomicBoolean, CompositeSubscription compositeSubscription, CompletableSubscriber completableSubscriber) {
            this.val$once = atomicBoolean;
            this.val$set = compositeSubscription;
            this.val$s = completableSubscriber;
        }

        public void call() {
            if (this.val$once.compareAndSet(false, true)) {
                this.val$set.clear();
                if (CompletableOnSubscribeTimeout.this.other == null) {
                    this.val$s.onError(new TimeoutException());
                } else {
                    CompletableOnSubscribeTimeout.this.other.subscribe(new C13201());
                }
            }
        }
    }

    /* renamed from: rx.internal.operators.CompletableOnSubscribeTimeout.2 */
    class C13222 implements CompletableSubscriber {
        final /* synthetic */ AtomicBoolean val$once;
        final /* synthetic */ CompletableSubscriber val$s;
        final /* synthetic */ CompositeSubscription val$set;

        C13222(CompositeSubscription compositeSubscription, AtomicBoolean atomicBoolean, CompletableSubscriber completableSubscriber) {
            this.val$set = compositeSubscription;
            this.val$once = atomicBoolean;
            this.val$s = completableSubscriber;
        }

        public void onSubscribe(Subscription d) {
            this.val$set.add(d);
        }

        public void onError(Throwable e) {
            if (this.val$once.compareAndSet(false, true)) {
                this.val$set.unsubscribe();
                this.val$s.onError(e);
                return;
            }
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        }

        public void onCompleted() {
            if (this.val$once.compareAndSet(false, true)) {
                this.val$set.unsubscribe();
                this.val$s.onCompleted();
            }
        }
    }

    public CompletableOnSubscribeTimeout(Completable source, long timeout, TimeUnit unit, Scheduler scheduler, Completable other) {
        this.source = source;
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }

    public void call(CompletableSubscriber s) {
        CompositeSubscription set = new CompositeSubscription();
        s.onSubscribe(set);
        AtomicBoolean once = new AtomicBoolean();
        Worker w = this.scheduler.createWorker();
        set.add(w);
        w.schedule(new C13211(once, set, s), this.timeout, this.unit);
        this.source.subscribe(new C13222(set, once, s));
    }
}
