package il.ac.tau.cs.sw1.ex9.starfleet;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;


public class StarfleetManager {
	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		List<Spaceship> temp = new ArrayList<Spaceship>();
		for (Spaceship s : fleet) {
			temp.add(s);
		}
		Collections.sort(temp, new MySpaceshipComp());

		List<String> res = new ArrayList<String>();
		for (Spaceship s : temp) {
			res.add(s.toString());
		}
		return res;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String, Integer> temp= new  HashMap<String, Integer>();
		for (Spaceship s : fleet) {
			String sClass = s.getClass().getSimpleName();
			if (temp.containsKey(sClass)) {
				temp.put(sClass, temp.get(sClass)+1);
			}
			else {
				temp.put(sClass, 1); }
		}return temp;
	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int n = 0;
		for (Spaceship s : fleet) {
			n += s.getAnnualMaintenanceCost(); }
		return n;
	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> temp = new HashSet<String>();
		for (Spaceship s : fleet) {
			if (s.getWeapons()!=null) {
				for (Weapon w : s.getWeapons()) {
					temp.add(w.getName()); } } }
		return temp;
	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int n = 0;
		for (Spaceship s : fleet) {
			n += s.getCrewMembers().size();
		}
		return n;
	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
	float sumAge = 0,count=0;
		for (Spaceship s : fleet) {
		Set<? extends CrewMember> crew = s.getCrewMembers();
		for (CrewMember m : crew) {
			if (m.getClass().getSimpleName().equals("Officer")) {
				sumAge += m.getAge();
				count++; } } }
		return sumAge/count;}
	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> temp = new HashMap<Officer, Spaceship>();
		for (Spaceship s : fleet) {
			List<Officer> officers = new ArrayList<>();
			for (CrewMember m : s.getCrewMembers()) {
				if (m.getClass().getSimpleName().equals("Officer")) {
					officers.add((Officer) m); } }
			if (officers.size() > 0) {
				officers.sort((Officer o1, Officer o2) -> o2.getRank().compareTo(o1.getRank()));
				temp.put(officers.get(0), s); } }
		return temp;
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		Map<OfficerRank, Integer> rankCount = new HashMap<OfficerRank, Integer>();
		for (Spaceship s : fleet) {
			for (CrewMember m : s.getCrewMembers()) {
				if (m.getClass().getSimpleName().equals("Officer")) {
					Officer o = (Officer)m;
					OfficerRank currRank = o.getRank();
					if (rankCount.containsKey(currRank)) {
						rankCount.put(currRank, rankCount.get(currRank)+1);
					} else { // Rank doesn't in map
						rankCount.put(currRank, 1);
					}
				}
			}
		}
		List<Map.Entry<OfficerRank, Integer>> res = new ArrayList<Map.Entry<OfficerRank, Integer>>();
		for (Map.Entry<OfficerRank,Integer> entry : rankCount.entrySet()) {
			res.add(entry); }
		res.sort(new MyOfficerComp());
		return res;
	}
}
 class MyOfficerComp implements Comparator<Map.Entry<OfficerRank,Integer>>{
	@Override
	public int compare(Entry<OfficerRank, Integer> o1, Entry<OfficerRank, Integer> o2) {
		return(!o1.getValue().equals(o2.getValue())) ? Integer.compare(o1.getValue(), o2.getValue()): o1.getKey().compareTo(o2.getKey()); }
}

class MySpaceshipComp implements Comparator<Spaceship> {
	@Override
	public int compare(Spaceship s1, Spaceship s2) {
		int temp1 = s1.getFirePower();
		int temp2 = s2.getFirePower();
		if (temp1 != temp2) { return Integer.compare(temp2, temp1); }
		temp1 = s1.getCommissionYear();
		temp2 = s2.getCommissionYear();
		if (temp1 != temp2) {
			return Integer.compare(temp2, temp1);
		}
		String name1 = s1.getName();
		String name2 = s2.getName();
		return name1.compareTo(name2);
	}

}