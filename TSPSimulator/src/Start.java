import java.util.ArrayList;

public class Start {

    public static void main(String[] ag) {

        TSPSimulatieFrame frame = new TSPSimulatieFrame(700, 1000);

        ArrayList<ArrayList<Integer>> locatiesOngesorteerd = frame.GenerateRoute(3);

        frame.createDisplay(locatiesOngesorteerd);


       BruteForce bruteforce = new BruteForce();
//
     System.out.println(bruteforce.permutation(locatiesOngesorteerd));
        System.out.println(frame.getAantalLocaties());

    }
}