import java.util.Scanner;

public class Battle {
    public static void startBattle(Character pl, Character opp) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- A BATTLE WITH " + opp.name + " HAS STARTED ---\n");
        while (pl.isAlive() && opp.isAlive()) {
            System.out.println("\nWhat will you do...\n 1. Basic\n 2. Special\nChoice:");
            int choice = scanner.nextInt();

            if (pl.spdStat > opp.spdStat) { // attack and be attacked cycle (spd differentiator)
                if (choice == 1) {
                    pl.attack(opp);
                }
                else {
                    pl.specialAttack(opp);
                }
                System.out.println("\nYou attacked first!\n");
            }
            else {
                opp.attack(pl);
                System.out.println("\nYou're slower!\n");
            }
            
            pl.displayStatus();
            opp.displayStatus();

            if (!opp.isAlive()) {
                System.out.println("\nBattle has ended: You Won!");
            }
            if (!pl.isAlive()) {
                System.out.println("\nBattle has ended: You lost...");
            }
        }
        scanner.close();
    }    
}

// public static void choice() {if (choice == 1) {pl.attack(opp);}else {pl.specialAttack(opp);}} 
