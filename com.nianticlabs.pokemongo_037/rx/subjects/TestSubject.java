package rx.subjects;

import java.util.concurrent.TimeUnit;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Scheduler.Worker;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.schedulers.TestScheduler;

public final class TestSubject<T> extends Subject<T, T> {
    private final Worker innerScheduler;
    private final SubjectSubscriptionManager<T> state;

    /* renamed from: rx.subjects.TestSubject.1 */
    static class C15571 implements Action1<SubjectObserver<T>> {
        final /* synthetic */ SubjectSubscriptionManager val$state;

        C15571(SubjectSubscriptionManager subjectSubscriptionManager) {
            this.val$state = subjectSubscriptionManager;
        }

        public void call(SubjectObserver<T> o) {
            o.emitFirst(this.val$state.getLatest(), this.val$state.nl);
        }
    }

    /* renamed from: rx.subjects.TestSubject.2 */
    class C15582 implements Action0 {
        C15582() {
        }

        public void call() {
            TestSubject.this._onCompleted();
        }
    }

    /* renamed from: rx.subjects.TestSubject.3 */
    class C15593 implements Action0 {
        final /* synthetic */ Throwable val$e;

        C15593(Throwable th) {
            this.val$e = th;
        }

        public void call() {
            TestSubject.this._onError(this.val$e);
        }
    }

    /* renamed from: rx.subjects.TestSubject.4 */
    class C15604 implements Action0 {
        final /* synthetic */ Object val$v;

        C15604(Object obj) {
            this.val$v = obj;
        }

        public void call() {
            TestSubject.this._onNext(this.val$v);
        }
    }

    public static <T> TestSubject<T> create(TestScheduler scheduler) {
        SubjectSubscriptionManager<T> state = new SubjectSubscriptionManager();
        state.onAdded = new C15571(state);
        state.onTerminated = state.onAdded;
        return new TestSubject(state, state, scheduler);
    }

    protected TestSubject(OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> state, TestScheduler scheduler) {
        super(onSubscribe);
        this.state = state;
        this.innerScheduler = scheduler.createWorker();
    }

    public void onCompleted() {
        onCompleted(0);
    }

    void _onCompleted() {
        if (this.state.active) {
            for (SubjectObserver<T> bo : this.state.terminate(NotificationLite.instance().completed())) {
                bo.onCompleted();
            }
        }
    }

    public void onCompleted(long delayTime) {
        this.innerScheduler.schedule(new C15582(), delayTime, TimeUnit.MILLISECONDS);
    }

    public void onError(Throwable e) {
        onError(e, 0);
    }

    void _onError(Throwable e) {
        if (this.state.active) {
            for (SubjectObserver<T> bo : this.state.terminate(NotificationLite.instance().error(e))) {
                bo.onError(e);
            }
        }
    }

    public void onError(Throwable e, long delayTime) {
        this.innerScheduler.schedule(new C15593(e), delayTime, TimeUnit.MILLISECONDS);
    }

    public void onNext(T v) {
        onNext(v, 0);
    }

    void _onNext(T v) {
        for (Observer<? super T> o : this.state.observers()) {
            o.onNext(v);
        }
    }

    public void onNext(T v, long delayTime) {
        this.innerScheduler.schedule(new C15604(v), delayTime, TimeUnit.MILLISECONDS);
    }

    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }
}
