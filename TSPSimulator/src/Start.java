import java.util.ArrayList;

public class Start {

    public static void main(String[] ag) {
        TSPSimulatieFrame frame = new TSPSimulatieFrame("TSP Simulator",620, 800);
        ArrayList<ArrayList<Integer>> locatiesOngesorteerd = frame.GenerateRoute(5);
        frame.createDisplay(locatiesOngesorteerd);
    }
}