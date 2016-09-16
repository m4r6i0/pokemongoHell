package rx.internal.util;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.producers.SingleProducer;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.observers.Subscribers;

public final class ScalarSynchronousObservable<T> extends Observable<T> {
    static final boolean STRONG_MODE;
    final T f919t;

    /* renamed from: rx.internal.util.ScalarSynchronousObservable.1 */
    class C14941 implements OnSubscribe<T> {
        final /* synthetic */ Object val$t;

        C14941(Object obj) {
            this.val$t = obj;
        }

        public void call(Subscriber<? super T> s) {
            s.setProducer(ScalarSynchronousObservable.createProducer(s, this.val$t));
        }
    }

    /* renamed from: rx.internal.util.ScalarSynchronousObservable.2 */
    class C14952 implements Func1<Action0, Subscription> {
        final /* synthetic */ EventLoopsScheduler val$els;

        C14952(EventLoopsScheduler eventLoopsScheduler) {
            this.val$els = eventLoopsScheduler;
        }

        public Subscription call(Action0 a) {
            return this.val$els.scheduleDirect(a);
        }
    }

    /* renamed from: rx.internal.util.ScalarSynchronousObservable.3 */
    class C14973 implements Func1<Action0, Subscription> {
        final /* synthetic */ Scheduler val$scheduler;

        /* renamed from: rx.internal.util.ScalarSynchronousObservable.3.1 */
        class C14961 implements Action0 {
            final /* synthetic */ Action0 val$a;
            final /* synthetic */ Worker val$w;

            C14961(Action0 action0, Worker worker) {
                this.val$a = action0;
                this.val$w = worker;
            }

            public void call() {
                try {
                    this.val$a.call();
                } finally {
                    this.val$w.unsubscribe();
                }
            }
        }

        C14973(Scheduler scheduler) {
            this.val$scheduler = scheduler;
        }

        public Subscription call(Action0 a) {
            Worker w = this.val$scheduler.createWorker();
            w.schedule(new C14961(a, w));
            return w;
        }
    }

    /* renamed from: rx.internal.util.ScalarSynchronousObservable.4 */
    class C14984 implements OnSubscribe<R> {
        final /* synthetic */ Func1 val$func;

        C14984(Func1 func1) {
            this.val$func = func1;
        }

        public void call(Subscriber<? super R> child) {
            Observable<? extends R> o = (Observable) this.val$func.call(ScalarSynchronousObservable.this.f919t);
            if (o instanceof ScalarSynchronousObservable) {
                child.setProducer(ScalarSynchronousObservable.createProducer(child, ((ScalarSynchronousObservable) o).f919t));
            } else {
                o.unsafeSubscribe(Subscribers.wrap(child));
            }
        }
    }

    static final class ScalarAsyncOnSubscribe<T> implements OnSubscribe<T> {
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        ScalarAsyncOnSubscribe(T value, Func1<Action0, Subscription> onSchedule) {
            this.value = value;
            this.onSchedule = onSchedule;
        }

        public void call(Subscriber<? super T> s) {
            s.setProducer(new ScalarAsyncProducer(s, this.value, this.onSchedule));
        }
    }

    static final class ScalarAsyncProducer<T> extends AtomicBoolean implements Producer, Action0 {
        private static final long serialVersionUID = -2466317989629281651L;
        final Subscriber<? super T> actual;
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        public ScalarAsyncProducer(Subscriber<? super T> actual, T value, Func1<Action0, Subscription> onSchedule) {
            this.actual = actual;
            this.value = value;
            this.onSchedule = onSchedule;
        }

        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            } else if (n != 0 && compareAndSet(false, true)) {
                this.actual.add((Subscription) this.onSchedule.call(this));
            }
        }

        public void call() {
            Subscriber<? super T> a = this.actual;
            if (!a.isUnsubscribed()) {
                T v = this.value;
                try {
                    a.onNext(v);
                    if (!a.isUnsubscribed()) {
                        a.onCompleted();
                    }
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, a, v);
                }
            }
        }

        public String toString() {
            return "ScalarAsyncProducer[" + this.value + ", " + get() + "]";
        }
    }

    static final class WeakSingleProducer<T> implements Producer {
        final Subscriber<? super T> actual;
        boolean once;
        final T value;

        public WeakSingleProducer(Subscriber<? super T> actual, T value) {
            this.actual = actual;
            this.value = value;
        }

        public void request(long n) {
            if (!this.once) {
                if (n < 0) {
                    throw new IllegalStateException("n >= required but it was " + n);
                } else if (n != 0) {
                    this.once = true;
                    Subscriber<? super T> a = this.actual;
                    if (!a.isUnsubscribed()) {
                        T v = this.value;
                        try {
                            a.onNext(v);
                            if (!a.isUnsubscribed()) {
                                a.onCompleted();
                            }
                        } catch (Throwable e) {
                            Exceptions.throwOrReport(e, a, v);
                        }
                    }
                }
            }
        }
    }

    static {
        STRONG_MODE = Boolean.valueOf(System.getProperty("rx.just.strong-mode", "false")).booleanValue();
    }

    static <T> Producer createProducer(Subscriber<? super T> s, T v) {
        if (STRONG_MODE) {
            return new SingleProducer(s, v);
        }
        return new WeakSingleProducer(s, v);
    }

    public static <T> ScalarSynchronousObservable<T> create(T t) {
        return new ScalarSynchronousObservable(t);
    }

    protected ScalarSynchronousObservable(T t) {
        super(new C14941(t));
        this.f919t = t;
    }

    public T get() {
        return this.f919t;
    }

    public Observable<T> scalarScheduleOn(Scheduler scheduler) {
        Func1<Action0, Subscription> onSchedule;
        if (scheduler instanceof EventLoopsScheduler) {
            onSchedule = new C14952((EventLoopsScheduler) scheduler);
        } else {
            onSchedule = new C14973(scheduler);
        }
        return Observable.create(new ScalarAsyncOnSubscribe(this.f919t, onSchedule));
    }

    public <R> Observable<R> scalarFlatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
        return Observable.create(new C14984(func));
    }
}
