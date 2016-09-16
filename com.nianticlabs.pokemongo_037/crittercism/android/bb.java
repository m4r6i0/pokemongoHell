package crittercism.android;

import com.crittercism.app.CrittercismConfig;
import java.util.List;

public final class bb extends CrittercismConfig {
    private String f399b;
    private bn f400c;

    public bb(bn bnVar, CrittercismConfig crittercismConfig) {
        super(crittercismConfig);
        this.f399b = "524c99a04002057fcd000001";
        this.f400c = bnVar;
    }

    public final List m466a() {
        List a = super.m33a();
        a.add(this.f400c.m556b());
        return a;
    }

    public final String m467b() {
        return this.f400c.m555a();
    }

    public final String m468c() {
        return this.f400c.m556b();
    }

    public final String m469d() {
        return this.f400c.m558d();
    }

    public final String m470e() {
        return this.f400c.m557c();
    }

    public final String m471f() {
        return this.f399b;
    }

    public final String m472g() {
        return this.a;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof bb)) {
            return false;
        }
        bb bbVar = (bb) o;
        return super.equals(o) && CrittercismConfig.m30a(this.f400c.m555a(), bbVar.f400c.m555a()) && CrittercismConfig.m30a(this.f400c.m556b(), bbVar.f400c.m556b()) && CrittercismConfig.m30a(this.f400c.m558d(), bbVar.f400c.m558d()) && CrittercismConfig.m30a(this.f400c.m557c(), bbVar.f400c.m557c()) && CrittercismConfig.m30a(this.f399b, bbVar.f399b);
    }

    public final int hashCode() {
        return (((((((((super.hashCode() * 31) + this.f400c.m555a().hashCode()) * 31) + this.f400c.m556b().hashCode()) * 31) + this.f400c.m558d().hashCode()) * 31) + this.f400c.m557c().hashCode()) * 31) + this.f399b.hashCode();
    }
}
