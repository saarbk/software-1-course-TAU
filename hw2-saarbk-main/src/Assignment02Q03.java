
public class Assignment02Q03 {

	public static void main(String[] args) {
		int numOfOdd = 0;
		int n = -1;
		// *** your code goes here below ***
		int count= Integer.parseInt(args[0]);
		int f1=1,f2=0,f3;
		String st="";
		n++;
		for (int i=0;i<count;i++){
			f3=f2+f1;
			f1=f2;
			f2=f3;
			   if (f2%2!=0){
				numOfOdd++;
			  }
            st+=f3+" ";
			n++;
		}
		
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		// *** your code goes here below ***
		System.out.println(st);

		System.out.println("The number of odd numbers is: "+numOfOdd);

	}

}
