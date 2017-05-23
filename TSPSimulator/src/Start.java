import java.util.ArrayList;

public class Start {

    public static void main(String[] ag) {
        NearestNeighbour nn = new NearestNeighbour();
        NearestNeighbourReversed nnreversed = new NearestNeighbourReversed();
        BruteForce bruteforce = new BruteForce();

        TSPSimulatieFrame frame = new TSPSimulatieFrame(620, 800, bruteforce);

        ArrayList<ArrayList<Integer>> locatiesOngesorteerd = frame.GenerateRoute(4);

        frame.createDisplay(locatiesOngesorteerd);


       // System.out.println(bruteforce.berekenRoute(locatiesOngesorteerd) + " KEkekekrk ");

    }
}