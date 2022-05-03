package il.ac.tau.cs.sw1.ex8.histogram;

public class IllegalItemException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalItemException(){
		super("The item you are trying to remove is illegal");
	}
}