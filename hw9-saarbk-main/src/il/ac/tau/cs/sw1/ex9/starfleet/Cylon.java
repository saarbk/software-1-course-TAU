package il.ac.tau.cs.sw1.ex9.starfleet;

public class Cylon extends CrewWoman {
	int model;
	public Cylon(String name, int age, int yearsInService, int modelNumber) {
		super(age, yearsInService, name);
		this.model = modelNumber;
	}
	public int getModel(){ return model;}
}
