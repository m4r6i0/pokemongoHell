package rx.observers;

import rx.Observer;
import rx.Subscriber;

public class SerializedSubscriber<T> extends Subscriber<T> {
    private final Observer<T> f922s;

    public SerializedSubscriber(Subscriber<? super T> s) {
        this(s, true);
    }

    public SerializedSubscriber(Subscriber<? super T> s, boolean shareSubscriptions) {
        super(s, shareSubscriptions);
        this.f922s = new SerializedObserver(s);
    }

    public void onCompleted() {
        this.f922s.onCompleted();
    }

    public void onError(Throwable e) {
        this.f922s.onError(e);
    }

    public void onNext(T t) {
        this.f922s.onNext(t);
    }
}
