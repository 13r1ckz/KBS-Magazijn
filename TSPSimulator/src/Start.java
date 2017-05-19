import java.util.ArrayList;

public class Start {

    public static void main(String[] ag) {
        TSPSimulatieFrame frame = new TSPSimulatieFrame(700, 1000);
        frame.createDisplay();
        BruteForce bruteforce = new BruteForce();
        ArrayList<ArrayList<Integer>> locatiesOngesorteerd = bruteforce.GenerateRoute(3);
        bruteforce.permutation(locatiesOngesorteerd);
        System.out.println();




    }


}