import java.util.ArrayList;

public class Start {

    public static void main(String[] ag) {
        BruteForce bruteforce = new BruteForce();

        TSPSimulatieFrame frame = new TSPSimulatieFrame(620, 800, bruteforce);

        ArrayList<ArrayList<Integer>> locatiesOngesorteerd = frame.GenerateRoute(4);

        frame.createDisplay(locatiesOngesorteerd);


       // System.out.println(bruteforce.berekenRoute(locatiesOngesorteerd) + " KEkekekrk ");

//        TSPSimulatieFrame frame = new TSPSimulatieFrame(700, 1000);
//        frame.createDisplay();
        NearestNeighbour nn = new NearestNeighbour();
        System.out.println("gesorteerd - " + nn.generateRoute(5));
        System.out.println(nn.getTotalLength() + " = totale route nn");
        NearestNeighbourReversed nnreversed = new NearestNeighbourReversed();
        System.out.println("gesorteerd - " + nnreversed.generateRoute(5));
        System.out.println(nnreversed.getTotalLength() + " = totale route nn");
    }
}