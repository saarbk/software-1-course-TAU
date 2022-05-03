package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Fighter  {
	static final int BASE_COST = 4000;

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}
	@Override
	public int getAnnualMaintenanceCost() {
		int n = BASE_COST;
		for (Weapon w : weapons) {
			n += w.getAnnualMaintenanceCost();
		}
		n+=500*crewMembers.size();
		n+= (int)Math.round(500*maximalSpeed);
		return n;
	}
}
