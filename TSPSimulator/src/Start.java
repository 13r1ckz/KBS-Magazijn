import java.util.ArrayList;

public class Start {
    public static void main(String[] ag) {
        TSPSimulatieFrame frame = new TSPSimulatieFrame(700, 1000);
        //frame.createDisplay();

        BruteForce bruteforce = new BruteForce();
        bruteforce.printCombinations(bruteforce.GenerateRoute(7));

        //limitatie:
        // 7 locaties is insta
        // 8 locaties duurt 2 seconden
        // 9 locaties duurt 20 seconden

    }


}