package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorSubscribeOn<T> implements OnSubscribe<T> {
    final Scheduler scheduler;
    final Observable<T> source;

    /* renamed from: rx.internal.operators.OperatorSubscribeOn.1 */
    class C14361 implements Action0 {
        final /* synthetic */ Worker val$inner;
        final /* synthetic */ Subscriber val$subscriber;

        /* renamed from: rx.internal.operators.OperatorSubscribeOn.1.1 */
        class C14351 extends Subscriber<T> {
            final /* synthetic */ Thread val$t;

            /* renamed from: rx.internal.operators.OperatorSubscribeOn.1.1.1 */
            class C14341 implements Producer {
                final /* synthetic */ Producer val$p;

                /* renamed from: rx.internal.operators.OperatorSubscribeOn.1.1.1.1 */
                class C14331 implements Action0 {
                    final /* synthetic */ long val$n;

                    C14331(long j) {
                        this.val$n = j;
                    }

                    public void call() {
                        C14341.this.val$p.request(this.val$n);
                    }
                }

                C14341(Producer producer) {
                    this.val$p = producer;
                }

                public void request(long n) {
                    if (C14351.this.val$t == Thread.currentThread()) {
                        this.val$p.request(n);
                    } else {
                        C14361.this.val$inner.schedule(new C14331(n));
                    }
                }
            }

            C14351(Subscriber x0, Thread thread) {
                this.val$t = thread;
                super(x0);
            }

            public void onNext(T t) {
                C14361.this.val$subscriber.onNext(t);
            }

            public void onError(Throwable e) {
                try {
                    C14361.this.val$subscriber.onError(e);
                } finally {
                    C14361.this.val$inner.unsubscribe();
                }
            }

            public void onCompleted() {
                try {
                    C14361.this.val$subscriber.onCompleted();
                } finally {
                    C14361.this.val$inner.unsubscribe();
                }
            }

            public void setProducer(Producer p) {
                C14361.this.val$subscriber.setProducer(new C14341(p));
            }
        }

        C14361(Subscriber subscriber, Worker worker) {
            this.val$subscriber = subscriber;
            this.val$inner = worker;
        }

        public void call() {
            OperatorSubscribeOn.this.source.unsafeSubscribe(new C14351(this.val$subscriber, Thread.currentThread()));
        }
    }

    public OperatorSubscribeOn(Observable<T> source, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.source = source;
    }

    public void call(Subscriber<? super T> subscriber) {
        Worker inner = this.scheduler.createWorker();
        subscriber.add(inner);
        inner.schedule(new C14361(subscriber, inner));
    }
}
