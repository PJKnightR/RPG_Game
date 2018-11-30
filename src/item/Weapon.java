/*package item;

public class Weapon  { // extends Item
/**
    private int dmg;

    public Weapon(String name, String description, int dmg) {
        super(name, description);
        this.dmg = dmg;
    }

    public int getDmg() {
        return this.dmg;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Weapon other = (Weapon) o;
        return (this.name.equals(other.name)
                && this.description.equals(other.description)
                && this.dmg == other.dmg);
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 37 * result + name.hashCode();
        result = 37 * result + description.hashCode();
        result = 37 * result + dmg;
        // TODO: check that the hash of dmg is correct.
        return result;
    }
<<<<<<< Updated upstream

}*/

