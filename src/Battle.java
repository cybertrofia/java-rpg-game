import java.util.Scanner;

public class Battle {
    public static void startBattle(Character pl, Character opp) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- A BATTLE WITH " + opp.name + " HAS STARTED ---\n");
        while (pl.isAlive() && opp.isAlive()) { // WHILE cycle
            System.out.println("\nWhat will you do...\n 1. Basic\n 2. Special\nChoice:");
            int choice = scanner.nextInt();

            if (pl.spdStat > opp.spdStat) { // attack and be attacked cycle (spd differentiator)
                System.out.println("\nYou attacked first!\n");
                if (choice == 1) {
                    pl.attack(opp);
                }
                else if (choice == 2) {
                    pl.specialAttack(opp);
                }
                else {
                    System.out.println("\nNot valid. Default option 'Basic' chosen.");
                    pl.attack(opp);
                }
                if (opp.isAlive()) {
                    opp.attack(pl);
                }
                
            }
            else {
                System.out.println("\nYou're slower!\n");
                opp.attack(pl);
                if (pl.isAlive()){
                    if (choice == 1) {
                        pl.attack(opp);
                    }
                    else if (choice == 2) {
                        pl.specialAttack(opp);
                    }
                    else {
                        System.out.println("\nNot valid. Default option 'Basic' chosen.");
                        pl.attack(opp);
                    }
                }
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