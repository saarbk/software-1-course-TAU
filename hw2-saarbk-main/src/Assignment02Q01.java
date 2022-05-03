
public class Assignment02Q01 {

	public static void main(String[] args) {

		for (String s : args) {
				char c = s.charAt(0);
				if ((int) c % 5 == 0) {
					System.out.println(c); }
		}
	}
}
