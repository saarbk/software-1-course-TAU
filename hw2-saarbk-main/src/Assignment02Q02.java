

public class Assignment02Q02 {

	public static void main(String[] args) {
		// do not change this part below
		double piEstimation = 0.0;
		
		// *** your code goes here below ***
		int n= Integer.parseInt(args[0]);
		double temp=1;
		double sign=1;
		do{
			piEstimation+=(sign*(1/temp));
			temp+=2;
			sign=-sign;
			n--;
		}while (n>0);
		piEstimation=4*piEstimation;
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}

}
