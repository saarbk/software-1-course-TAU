package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends MySpaceShip{
	int cargoCapacity;
	int passengerCapacity;
	static final int BASE_COST = 3000;
	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return BASE_COST + (5*cargoCapacity) + (3*passengerCapacity);
	}
	public int getCargoCapacity() { return cargoCapacity; }

	public int getPassengerCapacity() { return passengerCapacity; }
	@Override
	public String toString() {
		String s = super.toString() + "\n"+
				"\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				"\tCargoCapacity=" + this.getCargoCapacity() + "\n" +
				"\tPassengerCapacity=" + this.getPassengerCapacity();
		return s;
	}
}

