public abstract class Character {
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