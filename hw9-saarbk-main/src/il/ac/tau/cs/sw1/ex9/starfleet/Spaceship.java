package il.ac.tau.cs.sw1.ex9.starfleet;
import java.util.List;
import java.util.Set;

public interface Spaceship {
    int getFirePower();
    int getCommissionYear();
    String getName();
    int getAnnualMaintenanceCost();
    List <Weapon> getWeapons();
    Set<? extends CrewMember> getCrewMembers();
}
