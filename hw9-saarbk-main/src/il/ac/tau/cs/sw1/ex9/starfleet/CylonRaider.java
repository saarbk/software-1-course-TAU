package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider  extends Fighter{
	static final int BASE_COST = 3500;

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int totalCost = BASE_COST;
		for (Weapon w:weapons) {
			totalCost += w.getAnnualMaintenanceCost();
		}
		totalCost+=500*crewMembers.size();
		totalCost+=(int)Math.round(1200 * maximalSpeed);
		return totalCost;
	}


}
