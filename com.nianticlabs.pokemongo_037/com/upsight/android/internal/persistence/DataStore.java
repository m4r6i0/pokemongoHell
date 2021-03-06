package com.upsight.android.internal.persistence;

import android.content.Context;
import android.text.TextUtils;
import com.squareup.otto.Bus;
import com.upsight.android.UpsightException;
import com.upsight.android.internal.persistence.storable.StorableIdFactory;
import com.upsight.android.internal.persistence.storable.StorableInfo;
import com.upsight.android.internal.persistence.storable.StorableInfoCache;
import com.upsight.android.internal.persistence.storable.Storables;
import com.upsight.android.internal.persistence.subscription.Subscriptions;
import com.upsight.android.internal.util.LoggingListener;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightDataStoreListener;
import com.upsight.android.persistence.UpsightStorableSerializer;
import com.upsight.android.persistence.UpsightSubscription;
import com.upsight.android.persistence.annotation.UpsightStorableType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

class DataStore implements UpsightDataStore {
    private final Bus mBus;
    private final Context mContext;
    private final StorableIdFactory mIdFactory;
    private final StorableInfoCache mInfoCache;
    private final Scheduler mObserveOnScheduler;
    private final Scheduler mSubscribeOnScheduler;

    /* renamed from: com.upsight.android.internal.persistence.DataStore.10 */
    class AnonymousClass10 implements Action1<Throwable> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        AnonymousClass10(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(Throwable throwable) {
            this.val$listener.onFailure(new UpsightException(throwable));
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.12 */
    class AnonymousClass12 implements Func1<Storable, Observable<Storable>> {
        final /* synthetic */ boolean val$hasID;

        AnonymousClass12(boolean z) {
            this.val$hasID = z;
        }

        public Observable<Storable> call(Storable upsightStorable) {
            if (this.val$hasID) {
                return ContentObservables.update(DataStore.this.mContext, upsightStorable);
            }
            return ContentObservables.insert(DataStore.this.mContext, upsightStorable);
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.1 */
    class C09521 implements Action1<List<T>> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09521(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(List<T> ts) {
            this.val$listener.onSuccess(new HashSet(ts));
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.2 */
    class C09532 implements Action1<Throwable> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09532(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(Throwable throwable) {
            this.val$listener.onFailure(new UpsightException(throwable));
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.3 */
    class C09543 implements Action1<List<T>> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09543(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(List<T> ts) {
            this.val$listener.onSuccess(new HashSet(ts));
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.4 */
    class C09554 implements Action1<Throwable> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09554(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(Throwable throwable) {
            this.val$listener.onFailure(new UpsightException(throwable));
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.5 */
    class C09565 implements Action1<List<T>> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09565(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(List<T> ts) {
            this.val$listener.onSuccess(new HashSet(ts));
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.6 */
    class C09576 implements Action1<Throwable> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09576(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(Throwable throwable) {
            this.val$listener.onFailure(new UpsightException(throwable));
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.7 */
    class C09587 implements Action1<T> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09587(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(T t) {
            this.val$listener.onSuccess(t);
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.8 */
    class C09598 implements Action1<Throwable> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09598(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(Throwable throwable) {
            this.val$listener.onFailure(new UpsightException(throwable));
        }
    }

    /* renamed from: com.upsight.android.internal.persistence.DataStore.9 */
    class C09609 implements Action1<T> {
        final /* synthetic */ UpsightDataStoreListener val$listener;

        C09609(UpsightDataStoreListener upsightDataStoreListener) {
            this.val$listener = upsightDataStoreListener;
        }

        public void call(T t) {
            this.val$listener.onSuccess(t);
        }
    }

    DataStore(Context context, StorableInfoCache infoCache, StorableIdFactory idFactory, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler, Bus bus) {
        this.mContext = context;
        this.mInfoCache = infoCache;
        this.mIdFactory = idFactory;
        this.mSubscribeOnScheduler = subscribeOnScheduler;
        this.mObserveOnScheduler = observeOnScheduler;
        this.mBus = bus;
    }

    public <T> UpsightSubscription fetch(Class<T> clazz, UpsightDataStoreListener<Set<T>> listener) {
        if (clazz == null) {
            throw new IllegalArgumentException("A non null class must be specified for fetch(..)");
        } else if (listener != null) {
            return Subscriptions.from(fetchObservable(clazz).toList().subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new C09521(listener), new C09532(listener)));
        } else {
            throw new IllegalArgumentException("A non null listener must be specified for fetch(..)");
        }
    }

    public <T> UpsightSubscription fetch(Class<T> clazz, Set<String> ids, UpsightDataStoreListener<Set<T>> listener) {
        return Subscriptions.from(fetchObservable(clazz, (String[]) ids.toArray(new String[ids.size()])).toList().subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new C09543(listener), new C09554(listener)));
    }

    public <T> UpsightSubscription remove(Class<T> clazz, Set<String> ids, UpsightDataStoreListener<Set<T>> listener) {
        return Subscriptions.from(removeObservable(clazz, (String[]) ids.toArray(new String[ids.size()])).toList().subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new C09565(listener), new C09576(listener)));
    }

    public <T> UpsightSubscription remove(Class<T> clazz, Set<String> ids) {
        return remove(clazz, ids, new LoggingListener());
    }

    public <T> UpsightSubscription store(T object, UpsightDataStoreListener<T> listener) {
        return Subscriptions.from(storeObservable(object).subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new C09587(listener), new C09598(listener)));
    }

    public <T> UpsightSubscription store(T object) {
        return store(object, new LoggingListener());
    }

    public <T> UpsightSubscription remove(T object, UpsightDataStoreListener<T> listener) {
        if (listener != null) {
            return Subscriptions.from(removeObservable(object).subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new C09609(listener), new AnonymousClass10(listener)));
        }
        throw new IllegalArgumentException("Listener can not be null.");
    }

    public <T> UpsightSubscription remove(T object) {
        return remove((Object) object, new LoggingListener());
    }

    public UpsightSubscription subscribe(Object object) {
        return Subscriptions.from(Subscriptions.toObservable(this.mBus).observeOn(this.mObserveOnScheduler).subscribe(Subscriptions.create(object)));
    }

    public <T> Observable<T> fetchObservable(Class<T> clazz) {
        try {
            StorableInfo<T> info = this.mInfoCache.get(clazz);
            if (info == null) {
                throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", new Object[]{clazz.getName(), Storable.class.getSimpleName()}));
            }
            String type = info.getStorableTypeAccessor().getType();
            if (!TextUtils.isEmpty(type)) {
                return ContentObservables.fetch(this.mContext, type).lift(Storables.deserialize(info));
            }
            throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", new Object[]{clazz.getSimpleName(), UpsightStorableType.class.getSimpleName()}));
        } catch (UpsightException e) {
            return Observable.error(e);
        }
    }

    public <T> Observable<T> fetchObservable(Class<T> clazz, String... ids) {
        try {
            StorableInfo<T> info = this.mInfoCache.get(clazz);
            if (info == null) {
                throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", new Object[]{clazz.getName(), Storable.class.getSimpleName()}));
            }
            return ContentObservables.fetch(this.mContext, info.getStorableTypeAccessor().getType(), ids).lift(Storables.deserialize(info));
        } catch (UpsightException e) {
            return Observable.error(e);
        }
    }

    public <T> void setSerializer(Class<T> clazz, UpsightStorableSerializer<T> serializer) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class can not be null.");
        } else if (serializer == null) {
            throw new IllegalArgumentException("UpsightStorableSerializer can not be null.");
        } else {
            this.mInfoCache.setSerializer(clazz, serializer);
        }
    }

    public <T> Observable<T> storeObservable(T object) {
        boolean hasID = true;
        if (object == null) {
            throw new IllegalArgumentException("Attempting to store null object.");
        }
        try {
            StorableInfo<T> info = this.mInfoCache.get(object.getClass());
            if (info == null) {
                throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", new Object[]{clazz.getName(), Storable.class.getSimpleName()}));
            }
            if (TextUtils.isEmpty(info.getIdentifierAccessor().getId(object))) {
                hasID = false;
            }
            if (!hasID) {
                info.getIdentifierAccessor().setId(object, this.mIdFactory.createObjectID());
            }
            Observable sourceObservable = Observable.just(object);
            Observable<T> observable = sourceObservable.lift(Storables.serialize(info, this.mIdFactory)).flatMap(new AnonymousClass12(hasID)).zipWith(sourceObservable, new Func2<Storable, T, T>() {
                public T call(Storable storable, T t) {
                    return t;
                }
            });
            String type = info.getStorableTypeAccessor().getType(object);
            if (hasID) {
                return observable.doOnNext(Subscriptions.publishUpdated(this.mBus, type));
            }
            return observable.doOnNext(Subscriptions.publishCreated(this.mBus, type));
        } catch (UpsightException e) {
            return Observable.error(e);
        }
    }

    public <T> Observable<T> removeObservable(T object) {
        boolean hasID = true;
        if (object == null) {
            throw new IllegalArgumentException("Object can not be null.");
        }
        try {
            StorableInfo<T> info = this.mInfoCache.get(object.getClass());
            if (info != null) {
                if (TextUtils.isEmpty(info.getIdentifierAccessor().getId(object))) {
                    hasID = false;
                }
                if (!hasID) {
                    return Observable.error(new UpsightException("Object must be stored before removal.", new Object[0]));
                }
                String type = info.getStorableTypeAccessor().getType(object);
                Observable sourceObservable = Observable.just(object);
                return sourceObservable.lift(Storables.serialize(info, this.mIdFactory)).flatMap(new Func1<Storable, Observable<Storable>>() {
                    public Observable<Storable> call(Storable storable) {
                        return ContentObservables.remove(DataStore.this.mContext, storable);
                    }
                }).zipWith(sourceObservable, new Func2<Storable, T, T>() {
                    public T call(Storable storable, T t) {
                        return t;
                    }
                }).doOnNext(Subscriptions.publishRemoved(this.mBus, type));
            }
            return Observable.error(new UpsightException("Class %s must be annotated with @%s", clazz.getName(), Storable.class.getSimpleName()));
        } catch (UpsightException e) {
            return Observable.error(e);
        }
    }

    public <T> Observable<T> removeObservable(Class<T> clazz, String... ids) {
        try {
            StorableInfo<T> info = this.mInfoCache.get(clazz);
            if (info == null) {
                throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", new Object[]{clazz.getName(), Storable.class.getSimpleName()}));
            }
            return ContentObservables.fetch(this.mContext, info.getStorableTypeAccessor().getType(), ids).lift(Storables.deserialize(info)).flatMap(new Func1<T, Observable<T>>() {
                public Observable<T> call(T t) {
                    return DataStore.this.removeObservable(t);
                }
            });
        } catch (UpsightException e) {
            return Observable.error(e);
        }
    }
}
