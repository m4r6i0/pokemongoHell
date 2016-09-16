package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ConstructorConstructor {
    private final Map<Type, InstanceCreator<?>> instanceCreators;

    /* renamed from: com.google.gson.internal.ConstructorConstructor.14 */
    class AnonymousClass14 implements ObjectConstructor<T> {
        private final UnsafeAllocator unsafeAllocator;
        final /* synthetic */ Class val$rawType;
        final /* synthetic */ Type val$type;

        AnonymousClass14(Class cls, Type type) {
            this.val$rawType = cls;
            this.val$type = type;
            this.unsafeAllocator = UnsafeAllocator.create();
        }

        public T construct() {
            try {
                return this.unsafeAllocator.newInstance(this.val$rawType);
            } catch (Exception e) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.val$type + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", e);
            }
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.1 */
    class C06681 implements ObjectConstructor<T> {
        final /* synthetic */ Type val$type;
        final /* synthetic */ InstanceCreator val$typeCreator;

        C06681(InstanceCreator instanceCreator, Type type) {
            this.val$typeCreator = instanceCreator;
            this.val$type = type;
        }

        public T construct() {
            return this.val$typeCreator.createInstance(this.val$type);
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.2 */
    class C06692 implements ObjectConstructor<T> {
        final /* synthetic */ InstanceCreator val$rawTypeCreator;
        final /* synthetic */ Type val$type;

        C06692(InstanceCreator instanceCreator, Type type) {
            this.val$rawTypeCreator = instanceCreator;
            this.val$type = type;
        }

        public T construct() {
            return this.val$rawTypeCreator.createInstance(this.val$type);
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.3 */
    class C06703 implements ObjectConstructor<T> {
        final /* synthetic */ Constructor val$constructor;

        C06703(Constructor constructor) {
            this.val$constructor = constructor;
        }

        public T construct() {
            try {
                return this.val$constructor.newInstance(null);
            } catch (InstantiationException e) {
                throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", e2.getTargetException());
            } catch (IllegalAccessException e3) {
                throw new AssertionError(e3);
            }
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.4 */
    class C06714 implements ObjectConstructor<T> {
        C06714() {
        }

        public T construct() {
            return new TreeSet();
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.5 */
    class C06725 implements ObjectConstructor<T> {
        final /* synthetic */ Type val$type;

        C06725(Type type) {
            this.val$type = type;
        }

        public T construct() {
            if (this.val$type instanceof ParameterizedType) {
                Type elementType = ((ParameterizedType) this.val$type).getActualTypeArguments()[0];
                if (elementType instanceof Class) {
                    return EnumSet.noneOf((Class) elementType);
                }
                throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.6 */
    class C06736 implements ObjectConstructor<T> {
        C06736() {
        }

        public T construct() {
            return new LinkedHashSet();
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.7 */
    class C06747 implements ObjectConstructor<T> {
        C06747() {
        }

        public T construct() {
            return new LinkedList();
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.8 */
    class C06758 implements ObjectConstructor<T> {
        C06758() {
        }

        public T construct() {
            return new ArrayList();
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor.9 */
    class C06769 implements ObjectConstructor<T> {
        C06769() {
        }

        public T construct() {
            return new ConcurrentSkipListMap();
        }
    }

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> instanceCreators) {
        this.instanceCreators = instanceCreators;
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        InstanceCreator<T> typeCreator = (InstanceCreator) this.instanceCreators.get(type);
        if (typeCreator != null) {
            return new C06681(typeCreator, type);
        }
        InstanceCreator<T> rawTypeCreator = (InstanceCreator) this.instanceCreators.get(rawType);
        if (rawTypeCreator != null) {
            return new C06692(rawTypeCreator, type);
        }
        ObjectConstructor<T> defaultConstructor = newDefaultConstructor(rawType);
        if (defaultConstructor != null) {
            return defaultConstructor;
        }
        ObjectConstructor<T> defaultImplementation = newDefaultImplementationConstructor(type, rawType);
        if (defaultImplementation != null) {
            return defaultImplementation;
        }
        return newUnsafeAllocator(type, rawType);
    }

    private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> rawType) {
        try {
            Constructor<? super T> constructor = rawType.getDeclaredConstructor(new Class[0]);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return new C06703(constructor);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private <T> ObjectConstructor<T> newDefaultImplementationConstructor(Type type, Class<? super T> rawType) {
        if (Collection.class.isAssignableFrom(rawType)) {
            if (SortedSet.class.isAssignableFrom(rawType)) {
                return new C06714();
            }
            if (EnumSet.class.isAssignableFrom(rawType)) {
                return new C06725(type);
            }
            if (Set.class.isAssignableFrom(rawType)) {
                return new C06736();
            }
            if (Queue.class.isAssignableFrom(rawType)) {
                return new C06747();
            }
            return new C06758();
        } else if (!Map.class.isAssignableFrom(rawType)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(rawType)) {
                return new C06769();
            }
            if (ConcurrentMap.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    public T construct() {
                        return new ConcurrentHashMap();
                    }
                };
            }
            if (SortedMap.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    public T construct() {
                        return new TreeMap();
                    }
                };
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(TypeToken.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType())) {
                return new ObjectConstructor<T>() {
                    public T construct() {
                        return new LinkedTreeMap();
                    }
                };
            }
            return new ObjectConstructor<T>() {
                public T construct() {
                    return new LinkedHashMap();
                }
            };
        }
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(Type type, Class<? super T> rawType) {
        return new AnonymousClass14(rawType, type);
    }

    public String toString() {
        return this.instanceCreators.toString();
    }
}
