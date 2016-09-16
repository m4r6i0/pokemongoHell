package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public class OperatorCast<T, R> implements Operator<R, T> {
    final Class<R> castClass;

    /* renamed from: rx.internal.operators.OperatorCast.1 */
    class C13591 extends Subscriber<T> {
        final /* synthetic */ Subscriber val$o;

        C13591(Subscriber x0, Subscriber subscriber) {
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
                this.val$o.onNext(OperatorCast.this.castClass.cast(t));
            } catch (Throwable e) {
                Exceptions.throwOrReport(e, this, t);
            }
        }
    }

    public OperatorCast(Class<R> castClass) {
        this.castClass = castClass;
    }

    public Subscriber<? super T> call(Subscriber<? super R> o) {
        return new C13591(o, o);
    }
}
