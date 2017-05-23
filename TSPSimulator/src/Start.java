import java.util.ArrayList;

public class Start {

    public static void main(String[] ag) {


//       NearestNeighbourReversed nnreversed = new NearestNeighbourReversed();
        NearestNeighbourRework nn = new NearestNeighbourRework();
        BruteForce bruteforce = new BruteForce();
        NearestNeighbourReversedRewrk nnr = new NearestNeighbourReversedRewrk();

        TSPSimulatieFrame frame = new TSPSimulatieFrame("TSP Simulator" , 620, 800, bruteforce, nn, nnr);

        ArrayList<ArrayList<Integer>> locatiesOngesorteerd = frame.GenerateRoute(4);

        frame.createDisplay(locatiesOngesorteerd);
        System.out.println("________________________________________________");




    }
}