public class App {
    public static void main(String[] args) throws Exception {
        Mage p1 = new Mage("Joe", 14, 6, 6, 2);
        Tank p2 = new Tank("Oej", 24, 4, 2, 4);
        Battle.startBattle(p1, p2);
    }
}