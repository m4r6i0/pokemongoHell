package rx.schedulers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;

public class TestScheduler extends Scheduler {
    static long counter;
    final Queue<TimedAction> queue;
    long time;

    private static class CompareActionsByTime implements Comparator<TimedAction> {
        CompareActionsByTime() {
        }

        public int compare(TimedAction action1, TimedAction action2) {
            if (action1.time == action2.time) {
                if (action1.count < action2.count) {
                    return -1;
                }
                return action1.count > action2.count ? 1 : 0;
            } else if (action1.time >= action2.time) {
                return action1.time > action2.time ? 1 : 0;
            } else {
                return -1;
            }
        }
    }

    private final class InnerTestScheduler extends Worker {
        private final BooleanSubscription f923s;

        /* renamed from: rx.schedulers.TestScheduler.InnerTestScheduler.1 */
        class C15431 implements Action0 {
            final /* synthetic */ TimedAction val$timedAction;

            C15431(TimedAction timedAction) {
                this.val$timedAction = timedAction;
            }

            public void call() {
                TestScheduler.this.queue.remove(this.val$timedAction);
            }
        }

        /* renamed from: rx.schedulers.TestScheduler.InnerTestScheduler.2 */
        class C15442 implements Action0 {
            final /* synthetic */ TimedAction val$timedAction;

            C15442(TimedAction timedAction) {
                this.val$timedAction = timedAction;
            }

            public void call() {
                TestScheduler.this.queue.remove(this.val$timedAction);
            }
        }

        InnerTestScheduler() {
            this.f923s = new BooleanSubscription();
        }

        public void unsubscribe() {
            this.f923s.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.f923s.isUnsubscribed();
        }

        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            TimedAction timedAction = new TimedAction(this, TestScheduler.this.time + unit.toNanos(delayTime), action);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new C15431(timedAction));
        }

        public Subscription schedule(Action0 action) {
            TimedAction timedAction = new TimedAction(this, 0, action);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new C15442(timedAction));
        }

        public long now() {
            return TestScheduler.this.now();
        }
    }

    private static final class TimedAction {
        final Action0 action;
        private final long count;
        final Worker scheduler;
        final long time;

        TimedAction(Worker scheduler, long time, Action0 action) {
            long j = TestScheduler.counter;
            TestScheduler.counter = 1 + j;
            this.count = j;
            this.time = time;
            this.action = action;
            this.scheduler = scheduler;
        }

        public String toString() {
            return String.format("TimedAction(time = %d, action = %s)", new Object[]{Long.valueOf(this.time), this.action.toString()});
        }
    }

    public TestScheduler() {
        this.queue = new PriorityQueue(11, new CompareActionsByTime());
    }

    static {
        counter = 0;
    }

    public long now() {
        return TimeUnit.NANOSECONDS.toMillis(this.time);
    }

    public void advanceTimeBy(long delayTime, TimeUnit unit) {
        advanceTimeTo(this.time + unit.toNanos(delayTime), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long delayTime, TimeUnit unit) {
        triggerActions(unit.toNanos(delayTime));
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long targetTimeInNanos) {
        while (!this.queue.isEmpty()) {
            TimedAction current = (TimedAction) this.queue.peek();
            if (current.time > targetTimeInNanos) {
                break;
            }
            this.time = current.time == 0 ? this.time : current.time;
            this.queue.remove();
            if (!current.scheduler.isUnsubscribed()) {
                current.action.call();
            }
        }
        this.time = targetTimeInNanos;
    }

    public Worker createWorker() {
        return new InnerTestScheduler();
    }
}
