package il.ac.tau.cs.sw1.ex9.starfleet;

public class Officer extends CrewWoman{
	OfficerRank rank;
	public Officer(String name, int age, int yearsInService, OfficerRank rank) {
		super(age, yearsInService, name);
		this.rank = rank;
	}
	OfficerRank getRank() {
		return rank;
	}
	
	
}
