package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class OperatorUnsubscribeOn<T> implements Operator<T, T> {
    final Scheduler scheduler;

    /* renamed from: rx.internal.operators.OperatorUnsubscribeOn.1 */
    class C14661 extends Subscriber<T> {
        final /* synthetic */ Subscriber val$subscriber;

        C14661(Subscriber subscriber) {
            this.val$subscriber = subscriber;
        }

        public void onCompleted() {
            this.val$subscriber.onCompleted();
        }

        public void onError(Throwable e) {
            this.val$subscriber.onError(e);
        }

        public void onNext(T t) {
            this.val$subscriber.onNext(t);
        }
    }

    /* renamed from: rx.internal.operators.OperatorUnsubscribeOn.2 */
    class C14682 implements Action0 {
        final /* synthetic */ Subscriber val$parent;

        /* renamed from: rx.internal.operators.OperatorUnsubscribeOn.2.1 */
        class C14671 implements Action0 {
            final /* synthetic */ Worker val$inner;

            C14671(Worker worker) {
                this.val$inner = worker;
            }

            public void call() {
                C14682.this.val$parent.unsubscribe();
                this.val$inner.unsubscribe();
            }
        }

        C14682(Subscriber subscriber) {
            this.val$parent = subscriber;
        }

        public void call() {
            Worker inner = OperatorUnsubscribeOn.this.scheduler.createWorker();
            inner.schedule(new C14671(inner));
        }
    }

    public OperatorUnsubscribeOn(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Subscriber<T> parent = new C14661(subscriber);
        subscriber.add(Subscriptions.create(new C14682(parent)));
        return parent;
    }
}
