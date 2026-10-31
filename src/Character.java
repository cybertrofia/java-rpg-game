public abstract class Character {
    protected String name;
    protected float hpStat;
    protected float dmgStat;
    protected float spdStat;
    public Character(String name, float hpStat, float dmgStat, float spdStat) { // basic Character constructor
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
    public abstract void displayStatus(); // specific stat status display method (HP and unique stats/modifiers)
    public abstract void specialAttack(Character target); // unique class attack
}
class Tank extends Character {
    private float resistStat; // reduces damage taken && specialAttack resource
    public Tank(String name, float hpStat, float dmgStat, float spdStat, float resistStat) {
        super(name, hpStat, dmgStat, spdStat);
        this.resistStat = resistStat;
    }
    @Override
    public void takeDamage(float damage) {
        if (this.resistStat != 0) {
        float effectiveDamage = Math.max(0, damage - this.resistStat);
        this.hpStat -= effectiveDamage;            
            --this.resistStat;
        }
        else {
            this.hpStat -= damage;
        }
        if (this.hpStat < 0) {
            this.hpStat = 0;
        }
    }
    public void displayStatus() {
        System.out.println(name + " | HP: " + this.hpStat + " ARMOR: " + this.resistStat);
    }
    public void specialAttack(Character target){ // specialAttack for Tank class (placeholder)
        if (this.resistStat <= 0) {
            takeDamage(5);
        }
        else {
            target.takeDamage(dmgStat + 5); // placeholder modifier
            --this.resistStat;
        }
    }
}
class Mage extends Character {
    protected float manaStat; // specialAttack resource
    public Mage(String name, float hpStat, float dmgStat, float spdStat, float manaStat) {
        super(name, hpStat, dmgStat, spdStat);
        this.manaStat = manaStat;
    }
    @Override
    public void specialAttack(Character target) { //specialAttack for Mage class (castSpell)
        if (this.manaStat <= 0) {
            takeDamage(5);
        }
        else {
            target.takeDamage(dmgStat*2);
            --this.manaStat;
        }
    }
    public void displayStatus() {
    System.out.println(name + " | HP: " + this.hpStat + " MANA: " + this.manaStat);
    }
}
class Thief extends Character {
    protected float stealthStat;
    public Thief(String name, float hpStat, float dmgStat, float spdStat, float stealthStat) {
        super(name, hpStat, dmgStat, spdStat);
        this.stealthStat = stealthStat;
    }
    @Override
    public void specialAttack(Character target) {
        if (this.stealthStat <= 0) {
            takeDamage(5);
        }
        else {
            target.takeDamage(dmgStat + spdStat);
            this.stealthStat = 0;
        }
    }
    public void displayStatus() {
        System.out.println(name + " | HP: " + this.hpStat + " STEALTH: " + this.stealthStat);
    }
    public void takeDamage(float damage) {
        if (this.stealthStat != 0) {
            damage = 0;
            this.stealthStat -= 5;
        }
        this.hpStat -= damage;
        if (this.hpStat < 0) {
            this.hpStat = 0;
        }
    }    
}