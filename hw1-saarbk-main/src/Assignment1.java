

public class Assignment1 {
	public static void main(String[] args){

		int sum=(Integer.parseInt(args[0])+Integer.parseInt(args[1])+Integer.parseInt(args[2]));
		double average=sum;
		average=average/3;

		System.out.println("Sum is " + sum + ".");
		System.out.println("Average is " + String.format("%.2f", average) + ".");

	}
}
