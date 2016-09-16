package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

public final class OperatorMap<T, R> implements Operator<R, T> {
    final Func1<? super T, ? extends R> transformer;

    /* renamed from: rx.internal.operators.OperatorMap.1 */
    class C13821 extends Subscriber<T> {
        final /* synthetic */ Subscriber val$o;

        C13821(Subscriber x0, Subscriber subscriber) {
            this.val$o = subscriber;
            super(x0);
        }

        public void onCompleted() {
            this.val$o.onCompleted();
        }

        public void onError(Throwable e) {
            this.val$o.onError(e);
        }

        public void onNext(T t) {
            try {
                this.val$o.onNext(OperatorMap.this.transformer.call(t));
            } catch (Throwable e) {
                Exceptions.throwOrReport(e, this, t);
            }
        }
    }

    public OperatorMap(Func1<? super T, ? extends R> transformer) {
        this.transformer = transformer;
    }

    public Subscriber<? super T> call(Subscriber<? super R> o) {
        return new C13821(o, o);
    }
}
