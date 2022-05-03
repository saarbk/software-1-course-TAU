package il.ac.tau.cs.sw1.ex9.riddles.first;

public class B1 extends A1{
    C1 c = new C1();
    protected boolean foo() {
        return !c.foo();
    }
}
