package crittercism.android;

import crittercism.android.C1153c.C1152a;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: crittercism.android.e */
public final class C1171e {
    List f765a;
    final Set f766b;
    final Set f767c;
    private Executor f768d;

    /* renamed from: crittercism.android.e.a */
    class C1170a implements Runnable {
        final /* synthetic */ C1171e f763a;
        private C1153c f764b;

        private C1170a(C1171e c1171e, C1153c c1153c) {
            this.f763a = c1171e;
            this.f764b = c1153c;
        }

        public final void run() {
            if (!m783a(this.f764b)) {
                String a = this.f764b.m656a();
                if (m784a(a)) {
                    int indexOf = a.indexOf("?");
                    if (indexOf != -1) {
                        this.f764b.m662a(a.substring(0, indexOf));
                    }
                }
                synchronized (this.f763a.f765a) {
                    for (C1110f a2 : this.f763a.f765a) {
                        a2.m399a(this.f764b);
                    }
                }
            }
        }

        private boolean m783a(C1153c c1153c) {
            String a = c1153c.m656a();
            synchronized (this.f763a.f766b) {
                for (String contains : this.f763a.f766b) {
                    if (a.contains(contains)) {
                        return true;
                    }
                }
                return false;
            }
        }

        private boolean m784a(String str) {
            synchronized (this.f763a.f767c) {
                for (String contains : this.f763a.f767c) {
                    if (str.contains(contains)) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    private C1171e(Executor executor, List list, List list2) {
        this.f765a = new LinkedList();
        this.f766b = new HashSet();
        this.f767c = new HashSet();
        this.f768d = executor;
        m788a(list);
        m789b(list2);
    }

    public C1171e(Executor executor) {
        this(executor, new LinkedList(), new LinkedList());
    }

    public final void m787a(C1110f c1110f) {
        synchronized (this.f765a) {
            this.f765a.add(c1110f);
        }
    }

    public final void m788a(List list) {
        synchronized (this.f766b) {
            this.f766b.addAll(list);
            this.f766b.remove(null);
        }
    }

    public final void m789b(List list) {
        synchronized (this.f767c) {
            this.f767c.addAll(list);
            this.f767c.remove(null);
        }
    }

    @Deprecated
    public final void m785a(C1153c c1153c) {
        m786a(c1153c, C1152a.LEGACY_JAVANET);
    }

    public final void m786a(C1153c c1153c, C1152a c1152a) {
        if (!c1153c.f577b) {
            c1153c.f577b = true;
            c1153c.f578c = c1152a;
            this.f768d.execute(new C1170a(c1153c, (byte) 0));
        }
    }
}
