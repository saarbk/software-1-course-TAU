package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends MySpaceShip{
	private int techCount;
	static final int BASE_COST = 5000;

	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;
		this.techCount = numberOfTechnicians;
	}

	public int getNumberOfTechnicians() {
		return this.techCount;
	}
	public int getFirePower() {
		int n = super.getFirePower();
		for (Weapon w : weapons) {
			n += w.getFirePower();
		}
		return n;
	}
	public int getAnnualMaintenanceCost() {
		int cost = BASE_COST;
		int n = 0;
		for (Weapon w : weapons) {
			n += w.getAnnualMaintenanceCost();	}
		n = (int)Math.round((n) *(1 - (0.1)*techCount));
		cost += n;
		return cost;
	}
		public String toString() {
			String s = super.toString() + "\n" +
					"\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
					"\tWeaponArray=" + this.getWeapons() + "\n" +
					"\tNumberOfTechnicians=" + this.getNumberOfTechnicians();
			return s;
		}

		@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + techCount;
		return result;
	}

}