package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorDelay<T> implements Operator<T, T> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    /* renamed from: rx.internal.operators.OperatorDelay.1 */
    class C13671 extends Subscriber<T> {
        boolean done;
        final /* synthetic */ Subscriber val$child;
        final /* synthetic */ Worker val$worker;

        /* renamed from: rx.internal.operators.OperatorDelay.1.1 */
        class C13641 implements Action0 {
            C13641() {
            }

            public void call() {
                if (!C13671.this.done) {
                    C13671.this.done = true;
                    C13671.this.val$child.onCompleted();
                }
            }
        }

        /* renamed from: rx.internal.operators.OperatorDelay.1.2 */
        class C13652 implements Action0 {
            final /* synthetic */ Throwable val$e;

            C13652(Throwable th) {
                this.val$e = th;
            }

            public void call() {
                if (!C13671.this.done) {
                    C13671.this.done = true;
                    C13671.this.val$child.onError(this.val$e);
                    C13671.this.val$worker.unsubscribe();
                }
            }
        }

        /* renamed from: rx.internal.operators.OperatorDelay.1.3 */
        class C13663 implements Action0 {
            final /* synthetic */ Object val$t;

            C13663(Object obj) {
                this.val$t = obj;
            }

            public void call() {
                if (!C13671.this.done) {
                    C13671.this.val$child.onNext(this.val$t);
                }
            }
        }

        C13671(Subscriber x0, Worker worker, Subscriber subscriber) {
            this.val$worker = worker;
            this.val$child = subscriber;
            super(x0);
        }

        public void onCompleted() {
            this.val$worker.schedule(new C13641(), OperatorDelay.this.delay, OperatorDelay.this.unit);
        }

        public void onError(Throwable e) {
            this.val$worker.schedule(new C13652(e));
        }

        public void onNext(T t) {
            this.val$worker.schedule(new C13663(t), OperatorDelay.this.delay, OperatorDelay.this.unit);
        }
    }

    public OperatorDelay(long delay, TimeUnit unit, Scheduler scheduler) {
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        Worker worker = this.scheduler.createWorker();
        child.add(worker);
        return new C13671(child, worker, child);
    }
}
