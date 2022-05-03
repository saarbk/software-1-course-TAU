package il.ac.tau.cs.sw1.ex9.riddles.second;

import java.util.Random;

public class C2 {

	public static void main(String[] args) {
		String input = args[0];
		B2 b = new B2();
		Random random = new Random();
		boolean randomBool = random.nextBoolean();
		A2 a = b.getA(randomBool);
		
		if (randomBool) {
			if (!input.toUpperCase().equals(a.foo(input))) {
				return;
			}
		} else {
			if (!(input.toLowerCase()).equals(a.foo(input))) {
				return;
			}
		}
		System.out.println("success!");
	}
}
