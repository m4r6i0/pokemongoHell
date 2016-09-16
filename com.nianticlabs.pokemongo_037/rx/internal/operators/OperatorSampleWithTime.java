package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.Operator;
import rx.Observer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;

public final class OperatorSampleWithTime<T> implements Operator<T, T> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    static final class SamplerSubscriber<T> extends Subscriber<T> implements Action0 {
        private static final Object EMPTY_TOKEN;
        private final Subscriber<? super T> subscriber;
        final AtomicReference<Object> value;

        static {
            EMPTY_TOKEN = new Object();
        }

        public SamplerSubscriber(Subscriber<? super T> subscriber) {
            this.value = new AtomicReference(EMPTY_TOKEN);
            this.subscriber = subscriber;
        }

        public void onStart() {
            request(Long.MAX_VALUE);
        }

        public void onNext(T t) {
            this.value.set(t);
        }

        public void onError(Throwable e) {
            this.subscriber.onError(e);
            unsubscribe();
        }

        public void onCompleted() {
            emitIfNonEmpty();
            this.subscriber.onCompleted();
            unsubscribe();
        }

        public void call() {
            emitIfNonEmpty();
        }

        private void emitIfNonEmpty() {
            T localValue = this.value.getAndSet(EMPTY_TOKEN);
            if (localValue != EMPTY_TOKEN) {
                try {
                    this.subscriber.onNext(localValue);
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, (Observer) this);
                }
            }
        }
    }

    public OperatorSampleWithTime(long time, TimeUnit unit, Scheduler scheduler) {
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        SerializedSubscriber<T> s = new SerializedSubscriber(child);
        Worker worker = this.scheduler.createWorker();
        child.add(worker);
        SamplerSubscriber<T> sampler = new SamplerSubscriber(s);
        child.add(sampler);
        worker.schedulePeriodically(sampler, this.time, this.time, this.unit);
        return sampler;
    }
}
