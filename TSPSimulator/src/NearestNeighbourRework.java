import java.util.ArrayList;

public class NearestNeighbourRework implements TSP {
    private ArrayList<Integer> sOrder = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> gesorteerd = new ArrayList<ArrayList<Integer>>();
    private int antalOrder;

    public NearestNeighbourRework(ArrayList<ArrayList<Integer>> ongesorteerd) {
        int arrayListSize = ongesorteerd.size();
        int currentX = 0;
        int currentY = 0;
        double totaleAfstand= 0;
        int startX = 0;
        int startY = 0;


        for (int x = 0; x < arrayListSize; x++) {
            double afstand = 100;
            int kortste = 0;

            for (int i = 0; i < ongesorteerd.size(); i++) {




                if (afstand > calcPyth(currentX, currentY , ongesorteerd.get(i).get(0), ongesorteerd.get(i).get(1))){
                    afstand = calcPyth(currentX, currentY , ongesorteerd.get(i).get(0), ongesorteerd.get(i).get(1));

                    kortste = i;
                }
            }
            if (x == 0){
                totaleAfstand += calcPyth(0,0,ongesorteerd.get(kortste).get(0), ongesorteerd.get(kortste).get(1));
            }
            if (x == arrayListSize-1){
                totaleAfstand += calcPyth(0,0,ongesorteerd.get(kortste).get(0), ongesorteerd.get(kortste).get(1));
            }


            System.out.println(ongesorteerd.get(kortste).get(0) +" " + ongesorteerd.get(kortste).get(1));

            int kortsteX = ongesorteerd.get(kortste).get(0);
            int kortsteY = ongesorteerd.get(kortste).get(1);
            totaleAfstand += afstand;
            currentX = kortsteX;
            currentY = kortsteY;
            ongesorteerd.remove(kortste);
        }




    }



//    @Override
//    public ArrayList<ArrayList<Integer>> berekenRoute() {
//        return gesorteerd;
//    }



    @Override
    public ArrayList<ArrayList<Integer>> berekenRoute(ArrayList<ArrayList<Integer>> locatiesOngesorteerd) {
        return null;
    }

    @Override
    public double calcPyth(int beginX, int beginY, int eindX, int eindY) {
        int verschilX;
        int verschilY;
        if (beginX != eindX) {
            verschilX = Math.max(beginX, eindX) - Math.min(beginX, eindX);
        } else {
            verschilX = 0;
        }
        if (beginY != eindY) {
            verschilY = Math.max(beginY, eindY) - Math.min(beginY, eindY);
        } else {
            verschilY = 0;
        }
        double i = Math.pow(verschilX, 2) + Math.pow(verschilY, 2);
        return Math.sqrt(i);
    }
}

