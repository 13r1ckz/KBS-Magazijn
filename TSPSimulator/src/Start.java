import java.util.ArrayList;

public class Start {

    public static void main(String[] ag) {
        NearestNeighbourRework nn = new NearestNeighbourRework();
        NextFitUnwind nnrwrk = new NextFitUnwind();
        NearestNeighbourReversedRewrk nnreversed = new NearestNeighbourReversedRewrk();
        BruteForce bruteforce = new BruteForce();


        TSPSimulatieFrame frame = new TSPSimulatieFrame("TSP Simulator",620, 800);

        ArrayList<ArrayList<Integer>> locatiesOngesorteerd = frame.GenerateRoute(5);

        frame.createDisplay(locatiesOngesorteerd);
        System.out.println("________________________________________________");
//        NearestNeighbourRework nn = new NearestNeighbourRework(frame.GenerateRoute(4));
//        NearestNeighbourReversedRewrk  nnrev = new NearestNeighbourReversedRewrk(frame.GenerateRoute(4));
    }
}