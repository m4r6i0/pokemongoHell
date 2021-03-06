package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.SingleDelayedProducer;

public final class OperatorAny<T> implements Operator<Boolean, T> {
    final Func1<? super T, Boolean> predicate;
    final boolean returnOnEmpty;

    /* renamed from: rx.internal.operators.OperatorAny.1 */
    class C13501 extends Subscriber<T> {
        boolean done;
        boolean hasElements;
        final /* synthetic */ Subscriber val$child;
        final /* synthetic */ SingleDelayedProducer val$producer;

        C13501(SingleDelayedProducer singleDelayedProducer, Subscriber subscriber) {
            this.val$producer = singleDelayedProducer;
            this.val$child = subscriber;
        }

        public void onNext(T t) {
            this.hasElements = true;
            try {
                if (((Boolean) OperatorAny.this.predicate.call(t)).booleanValue() && !this.done) {
                    this.done = true;
                    this.val$producer.setValue(Boolean.valueOf(!OperatorAny.this.returnOnEmpty));
                    unsubscribe();
                }
            } catch (Throwable e) {
                Exceptions.throwOrReport(e, this, t);
            }
        }

        public void onError(Throwable e) {
            this.val$child.onError(e);
        }

        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                if (this.hasElements) {
                    this.val$producer.setValue(Boolean.valueOf(false));
                } else {
                    this.val$producer.setValue(Boolean.valueOf(OperatorAny.this.returnOnEmpty));
                }
            }
        }
    }

    public OperatorAny(Func1<? super T, Boolean> predicate, boolean returnOnEmpty) {
        this.predicate = predicate;
        this.returnOnEmpty = returnOnEmpty;
    }

    public Subscriber<? super T> call(Subscriber<? super Boolean> child) {
        SingleDelayedProducer<Boolean> producer = new SingleDelayedProducer(child);
        Subscriber<T> s = new C13501(producer, child);
        child.add(s);
        child.setProducer(producer);
        return s;
    }
}
