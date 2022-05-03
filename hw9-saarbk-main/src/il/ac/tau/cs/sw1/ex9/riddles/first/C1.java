package il.ac.tau.cs.sw1.ex9.riddles.first;

public class C1 {
	
	private static boolean secret = false;

	public boolean foo() {
		secret = !secret;
		return secret;
	}

	public static void main(String[] args) {
		C1 c = new C1();
		for (int i = 0; i < args.length; i++) {
			c.foo();
		}
		A1 a = new B1();
		if (a.foo() == c.foo()) {
			System.out.println("success!");
		}
	}
}
