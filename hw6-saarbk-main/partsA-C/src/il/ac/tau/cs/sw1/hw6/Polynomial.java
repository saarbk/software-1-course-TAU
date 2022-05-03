package il.ac.tau.cs.sw1.hw6;

public class Polynomial {

	private double[] p;

	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial()
	{
		this.p= new double[0];

	}
	public Polynomial(Polynomial other)
	{
		this.p= other.p;

	}
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		this.p=coefficients;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int sizeP1=this.p.length;
		int sizeP2=polynomial.p.length;
		int sizemin=Math.min(sizeP1,sizeP2);
		int sizeMax=Math.max(sizeP1,sizeP2);
		Boolean P1isBigger= sizeP1>sizeP2;
		double[] newPoly = new double[sizeMax];

		for (int i = 0; i < sizemin;i++) {
			newPoly[i] = this.p[i] + polynomial.p[i];
		}

		for (int i=sizemin;i<sizeMax;i++)
		{
			newPoly[i]= P1isBigger ? this.p[i] : polynomial.p[i];
		}
		return new Polynomial(newPoly);
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double[] newpoly = new double[this.p.length];

		for (int i = 0; i < this.p.length; i++) {
			newpoly[i] = this.p[i]*a;
		}
		return new Polynomial(newpoly);
		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{

		int i = this.p.length-1;
		while (i>0) {
			if (this.p[i] != 0){
				return i;}
			i--;
		}
		return Math.max(i, 0);
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		return (n <= this.p.length - 1) && n >= 0 ? p[n] : 0.0;
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		if (n < this.p.length - 1) {
			this.p[n] = c;
		}
		else {
			double[] newpPoly = new double[n + 1];
			newpPoly[n] = c;
			System.arraycopy(this.p, 0, newpPoly, 0, p.length);
			this.p = newpPoly;
		}
	}

	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		if(this.p.length==0)
		{return this;}
		double[] newpoly = new double[this.p.length-1];
		for (int i=0;i<newpoly.length;i++) {
			newpoly[i]=(i+1)*this.p[i+1];
		}
		return new Polynomial(newpoly);
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		double sum = 0.0;
		for (int i=0;i<this.p.length;i++) {
			sum+=this.p[i]*Math.pow(x, i);
		}
		return sum;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial first = this.getFirstDerivation();
		Polynomial second = first.getFirstDerivation();
		return first.computePolynomial(x)==0.0 && second.computePolynomial(x) != 0.0;
	}








}
