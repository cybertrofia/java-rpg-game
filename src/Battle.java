public class Battle {
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