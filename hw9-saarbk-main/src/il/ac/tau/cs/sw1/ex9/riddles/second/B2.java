package il.ac.tau.cs.sw1.ex9.riddles.second;
public class B2 extends A2{
    static boolean myBool;
    public B2 getA(boolean randBool) {
        myBool = randBool;
        return new B2();
    }

    public String foo(String s) {
        if (myBool) {
            return s.toUpperCase();
        }
        return s.toLowerCase();
    }
}