abstract class Character {
    protected String name;
    protected float hpStat;
    protected float dmgStat;
    protected float spdStat;
    public Character(String name, float hpStat, float dmgStat, float spdStat) {
        this.name = name;
        this.hpStat = hpStat;
        this.dmgStat = dmgStat;
        this.spdStat = spdStat;
    }
    public boolean isAlive() {
        return this.hpStat > 0;                       
    }
    public void attack(Character target) {
        target.takeDamage(this.dmgStat);
    }
    public void takeDamage(float damage) {
        this.hpStat -= damage;
        if (this.hpStat < 0) {
        this.hpStat = 0;
        }
    }
    public abstract void displayStatus(); 
}

class Tank extends Character {
    protected float armorStat;
    public Tank(String name, float hpStat, float dmgStat, float spdStat, float armorStat) {
        super(name, hpStat, dmgStat, spdStat);
        this.armorStat = armorStat;
    }
    @Override
    public void takeDamage(float damage) {
        float effectiveDamage = Math.max(0, damage - this.armorStat);
        this.hpStat -= effectiveDamage;
        if (this.armorStat != 0) {
            --this.armorStat;
        }
        if (this.hpStat < 0) {
            this.hpStat = 0;
        }
    }
    public void displayStatus() {
        System.out.println(name + " | HP: " + this.hpStat + " ARMOR: " + this.armorStat);
    }
}

class Mage extends Character {
    protected float manaStat;
    public Mage(String name, float hpStat, float dmgStat, float spdStat, float manaStat) {
        super(name, hpStat, dmgStat, spdStat);
        this.manaStat = manaStat;
    }

    public void castSpell(Character target) {
        if (this.manaStat <= 0) {
            takeDamage(5);
        }
        else {
            target.takeDamage(dmgStat*2);
            --this.manaStat;
        }
    }
    @Override
    public void attack(Character target) {
        if (this.manaStat > 0) {
            castSpell(target);
        }
        else{
            super.attack(target);
        }
    }
    public void displayStatus() {
    System.out.println(name + " | HP: " + this.hpStat + " MANA: " + this.manaStat);
    }
}

class Battle {
    public static void startBattle(Character p1, Character p2) {
        int round = 1;
        while (p1.isAlive() && p2.isAlive()) {
            System.out.println("\n--- ROUND " + round + " ---");

            Character firstAttacker;
            Character secondAttacker;

            if (p1.spdStat > p2.spdStat) {
                firstAttacker = p1;
                secondAttacker = p2;
            }
            else {
                secondAttacker = p1;
                firstAttacker = p2;
            }

            System.out.println(firstAttacker.name + " attacks first.\n");
            firstAttacker.attack(secondAttacker);
            firstAttacker.displayStatus();

            if (secondAttacker.isAlive()) {
            secondAttacker.attack(firstAttacker);
            secondAttacker.displayStatus();
            } 
            else {
            System.out.println("\n" + secondAttacker.name + " has been defeated.\n" + firstAttacker.name + " won! ");
            }
            round++;
        }
    }    
}

public class App {
    public static void main(String[] args) throws Exception {
    Mage p1 = new Mage("Joe", 14, 6, 6, 2);
    Tank p2 = new Tank("Oej", 24, 4, 2, 4);
    Battle.startBattle(p1, p2);
    }
}
