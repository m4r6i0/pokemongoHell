package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.SerialSubscription;

public final class OperatorOnErrorResumeNextViaFunction<T> implements Operator<T, T> {
    final Func1<Throwable, ? extends Observable<? extends T>> resumeFunction;

    /* renamed from: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.1 */
    static class C13941 implements Func1<Throwable, Observable<? extends T>> {
        final /* synthetic */ Func1 val$resumeFunction;

        C13941(Func1 func1) {
            this.val$resumeFunction = func1;
        }

        public Observable<? extends T> call(Throwable t) {
            return Observable.just(this.val$resumeFunction.call(t));
        }
    }

    /* renamed from: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.2 */
    static class C13952 implements Func1<Throwable, Observable<? extends T>> {
        final /* synthetic */ Observable val$other;

        C13952(Observable observable) {
            this.val$other = observable;
        }

        public Observable<? extends T> call(Throwable t) {
            return this.val$other;
        }
    }

    /* renamed from: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.3 */
    static class C13963 implements Func1<Throwable, Observable<? extends T>> {
        final /* synthetic */ Observable val$other;

        C13963(Observable observable) {
            this.val$other = observable;
        }

        public Observable<? extends T> call(Throwable t) {
            if (t instanceof Exception) {
                return this.val$other;
            }
            return Observable.error(t);
        }
    }

    /* renamed from: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.4 */
    class C13984 extends Subscriber<T> {
        private boolean done;
        long produced;
        final /* synthetic */ Subscriber val$child;
        final /* synthetic */ ProducerArbiter val$pa;
        final /* synthetic */ SerialSubscription val$ssub;

        /* renamed from: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.4.1 */
        class C13971 extends Subscriber<T> {
            C13971() {
            }

            public void onNext(T t) {
                C13984.this.val$child.onNext(t);
            }

            public void onError(Throwable e) {
                C13984.this.val$child.onError(e);
            }

            public void onCompleted() {
                C13984.this.val$child.onCompleted();
            }

            public void setProducer(Producer producer) {
                C13984.this.val$pa.setProducer(producer);
            }
        }

        C13984(Subscriber subscriber, ProducerArbiter producerArbiter, SerialSubscription serialSubscription) {
            this.val$child = subscriber;
            this.val$pa = producerArbiter;
            this.val$ssub = serialSubscription;
        }

        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                this.val$child.onCompleted();
            }
        }

        public void onError(Throwable e) {
            if (this.done) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
                return;
            }
            this.done = true;
            try {
                unsubscribe();
                Subscriber<T> next = new C13971();
                this.val$ssub.set(next);
                long p = this.produced;
                if (p != 0) {
                    this.val$pa.produced(p);
                }
                ((Observable) OperatorOnErrorResumeNextViaFunction.this.resumeFunction.call(e)).unsafeSubscribe(next);
            } catch (Throwable e2) {
                Exceptions.throwOrReport(e2, this.val$child);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                this.produced++;
                this.val$child.onNext(t);
            }
        }

        public void setProducer(Producer producer) {
            this.val$pa.setProducer(producer);
        }
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withSingle(Func1<Throwable, ? extends T> resumeFunction) {
        return new OperatorOnErrorResumeNextViaFunction(new C13941(resumeFunction));
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withOther(Observable<? extends T> other) {
        return new OperatorOnErrorResumeNextViaFunction(new C13952(other));
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withException(Observable<? extends T> other) {
        return new OperatorOnErrorResumeNextViaFunction(new C13963(other));
    }

    public OperatorOnErrorResumeNextViaFunction(Func1<Throwable, ? extends Observable<? extends T>> f) {
        this.resumeFunction = f;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        ProducerArbiter pa = new ProducerArbiter();
        SerialSubscription ssub = new SerialSubscription();
        Subscriber<T> parent = new C13984(child, pa, ssub);
        ssub.set(parent);
        child.add(ssub);
        child.setProducer(pa);
        return parent;
    }
}
