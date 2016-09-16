package crittercism.android;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class bs {
    public final File f524a;
    public String f525b;
    public List f526c;
    private cj f527d;
    private int f528e;
    private int f529f;
    private int f530g;
    private C1124a f531h;
    private boolean f532i;

    /* renamed from: crittercism.android.bs.a */
    public static class C1124a {
        int f523a;

        public C1124a(int i) {
            this.f523a = i;
        }
    }

    public bs(Context context, br brVar) {
        this(new File(context.getFilesDir().getAbsolutePath() + "//com.crittercism//" + brVar.m560a()), brVar.m562c(), brVar.m563d(), brVar.m564e(), brVar.m561b(), brVar.m565f());
    }

    private bs(File file, C1124a c1124a, cj cjVar, int i, int i2, String str) {
        this.f532i = false;
        this.f531h = c1124a;
        this.f527d = cjVar;
        this.f530g = i;
        this.f529f = i2;
        this.f525b = str;
        this.f524a = file;
        file.mkdirs();
        m567d();
        this.f528e = m571h().length;
        this.f526c = new LinkedList();
    }

    public final bs m573a(Context context) {
        return new bs(new File(context.getFilesDir().getAbsolutePath() + "//com.crittercism/pending/" + (this.f524a.getName() + "_" + UUID.randomUUID().toString())), this.f531h, this.f527d, this.f530g, this.f529f, this.f525b);
    }

    private boolean m567d() {
        if (!this.f524a.isDirectory()) {
            this.f532i = true;
            String absolutePath = this.f524a.getAbsolutePath();
            if (this.f524a.exists()) {
                IOException iOException = new IOException(absolutePath + " is not a directory");
            } else {
                FileNotFoundException fileNotFoundException = new FileNotFoundException(absolutePath + " does not exist");
            }
        }
        if (this.f532i) {
            return false;
        }
        return true;
    }

    public final synchronized boolean m577a(ch chVar) {
        boolean z = false;
        synchronized (this) {
            if (m567d()) {
                if (this.f528e >= this.f530g) {
                    dx.m778b();
                } else {
                    int b = m578b();
                    if (b != m572i() || m569f()) {
                        if (b > m572i()) {
                            this.f532i = true;
                        } else {
                            boolean c = m566c(chVar);
                            if (c) {
                                this.f528e++;
                            }
                            synchronized (this.f526c) {
                                for (bt c2 : this.f526c) {
                                    c2.m527c();
                                }
                            }
                            z = c;
                        }
                    }
                }
            }
        }
        return z;
    }

    public final synchronized boolean m579b(ch chVar) {
        boolean c;
        if (m567d()) {
            new File(this.f524a, chVar.m490e()).delete();
            c = m566c(chVar);
        } else {
            c = false;
        }
        return c;
    }

    private boolean m566c(ch chVar) {
        File file = new File(this.f524a, chVar.m490e());
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            new StringBuilder("Could not open output stream to : ").append(file);
            dx.m773a();
        }
        try {
            chVar.m489a(outputStream);
            return true;
        } catch (Throwable e2) {
            file.delete();
            dx.m776a("Unable to write to " + file.getAbsolutePath(), e2);
            return false;
        } finally {
            try {
                outputStream.close();
            } catch (Throwable e22) {
                file.delete();
                dx.m776a("Unable to close " + file.getAbsolutePath(), e22);
                return false;
            }
        }
    }

    private void m568e() {
        while (m578b() > m572i()) {
            if (!m569f()) {
                return;
            }
        }
    }

    private boolean m569f() {
        C1124a c1124a = this.f531h;
        if (this.f531h == null) {
            return false;
        }
        C1124a c1124a2 = this.f531h;
        File[] g = m570g();
        File file = null;
        if (g.length > c1124a2.f523a) {
            file = g[c1124a2.f523a];
        }
        if (file == null || !file.delete()) {
            return false;
        }
        return true;
    }

    public final synchronized void m574a() {
        if (m567d()) {
            File[] h = m571h();
            for (File delete : h) {
                delete.delete();
            }
        }
    }

    private File[] m570g() {
        File[] h = m571h();
        Arrays.sort(h);
        return h;
    }

    private File[] m571h() {
        File[] listFiles = this.f524a.listFiles();
        if (listFiles == null) {
            return new File[0];
        }
        return listFiles;
    }

    public final synchronized int m578b() {
        return m571h().length;
    }

    private synchronized int m572i() {
        return this.f529f;
    }

    public final synchronized void m576a(String str) {
        if (m567d() && str != null) {
            File file = new File(this.f524a.getAbsolutePath(), str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public final void m575a(bs bsVar) {
        if (bsVar != null) {
            int compareTo = this.f524a.getName().compareTo(bsVar.f524a.getName());
            if (compareTo != 0) {
                bs bsVar2;
                bs bsVar3;
                if (compareTo < 0) {
                    bsVar2 = bsVar;
                    bsVar3 = this;
                } else {
                    bsVar2 = this;
                    bsVar3 = bsVar;
                }
                synchronized (r2) {
                    synchronized (r1) {
                        if (m567d() && bsVar.m567d()) {
                            File[] g = m570g();
                            for (compareTo = 0; compareTo < g.length; compareTo++) {
                                g[compareTo].renameTo(new File(bsVar.f524a, g[compareTo].getName()));
                            }
                            bsVar.m568e();
                            for (bt d : this.f526c) {
                                d.m528d();
                            }
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    public final synchronized List m580c() {
        List arrayList;
        arrayList = new ArrayList();
        if (m567d()) {
            cj cjVar = this.f527d;
            File[] g = m570g();
            for (File a : g) {
                arrayList.add(this.f527d.m652a(a));
            }
        }
        return arrayList;
    }
}
