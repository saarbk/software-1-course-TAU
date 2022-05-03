package il.ac.tau.cs.sw1.ex9.starfleet;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public abstract class MySpaceShip implements Spaceship {

    String name;
    int commissionYear;
    float maximalSpeed;
    Set<? extends CrewMember> crewMembers;
    final int BASE_FIRE_POWER = 10;
    String version;
    List<Weapon> weapons;

    public MySpaceShip(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMember) {
        this.name = name;
        this.commissionYear = commissionYear;
        this.maximalSpeed = maximalSpeed;
        this.crewMembers = crewMember;
    }
    public String getName() { return name;
    }

    public int getCommissionYear() { return commissionYear;
    }

    public float getMaximalSpeed() { return maximalSpeed; }

    public Set<? extends CrewMember> getCrewMembers() { return crewMembers; }

    public int getFirePower() { return BASE_FIRE_POWER;
    }

    public List<Weapon> getWeapons() { return this.weapons;
    }
    public int getWeaponsCost() {
        int n = 0;
        for (Weapon weapon : this.weapons) {
            n+=weapon.getAnnualMaintenanceCost();
        }
        return n;
    }
    @Override
    public String toString() {
        return "" + this.getClass().getSimpleName().toString() + "\n" +
                "\tName=" + this.name + "\n" +
                "\tCommissionYear=" + this.getCommissionYear() + "\n" +
                "\tMaximalSpeed=" + this.getMaximalSpeed() + "\n" +
                "\tFirePower=" + this.getFirePower() + "\n" +
                "\tCrewMembers=" + this.getCrewMembers().size();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MySpaceShip other = (MySpaceShip) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public int compareTo(Spaceship s2) {
        int fire1 = this.getFirePower();
        int fire2 = s2.getFirePower();
        if (fire1 != fire2) {
            return Integer.compare(fire2, fire1);
        }
        int comm1 = this.getCommissionYear();
        int comm2 = s2.getCommissionYear();
        if (comm1 != comm2) {
            return Integer.compare(comm2, comm1);
        }
        String name1 = this.getName();
        String name2 = s2.getName();

        return name1.compareTo(name2);
    }

}
