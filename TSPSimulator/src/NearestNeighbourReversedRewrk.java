import java.util.ArrayList;
import java.util.Collections;

public class NearestNeighbourReversedRewrk implements TSP {
    private ArrayList<ArrayList<Integer>> gesorteerd = new ArrayList<ArrayList<Integer>>();
    private double totaleAfstand = 0;

    @Override
    public ArrayList<ArrayList<Integer>> berekenRoute(ArrayList<ArrayList<Integer>> locatiesOngesorteerd) {
        ArrayList<ArrayList<Integer>> rekenRoute = new ArrayList<>(locatiesOngesorteerd);
        int arrayListSize = rekenRoute.size();
        int currentX = 0;
        int currentY = 0;
        int startX = 0;
        int startY = 0;


        for (int x = 0; x < arrayListSize; x++) {

            if (x != 0) {


                double afstand = 100;
                int kortste = 0;

                for (int i = 0; i < rekenRoute.size(); i++) {
                    if (afstand > calcPyth(currentX, currentY, rekenRoute.get(i).get(0), rekenRoute.get(i).get(1))) {
                        afstand = calcPyth(currentX, currentY, rekenRoute.get(i).get(0), rekenRoute.get(i).get(1));
                        kortste = i;
                    }
                }
                if (x == 0) {
                    totaleAfstand += calcPyth(0, 0, rekenRoute.get(kortste).get(0), rekenRoute.get(kortste).get(1));
                }
                if (x == arrayListSize - 1) {
                    totaleAfstand += calcPyth(0, 0, rekenRoute.get(kortste).get(0), rekenRoute.get(kortste).get(1));
                }


//                System.out.println(rekenRoute.get(kortste).get(0) + " " + rekenRoute.get(kortste).get(1));
                ArrayList<Integer> innerGesorteerd;
                innerGesorteerd = new ArrayList<>();
                innerGesorteerd.add(rekenRoute.get(kortste).get(0));
                innerGesorteerd.add(rekenRoute.get(kortste).get(1));
                gesorteerd.add(innerGesorteerd);

                int kortsteX = rekenRoute.get(kortste).get(0);
                int kortsteY = rekenRoute.get(kortste).get(1);
                totaleAfstand += afstand;
                currentX = kortsteX;
                currentY = kortsteY;
                rekenRoute.remove(kortste);
            }

            if ( x == 0 ){
                double afstand = 0;
                int langste = 0;

                for (int i = 0; i < rekenRoute.size(); i++) {

                    if (afstand < calcPyth(currentX, currentY, rekenRoute.get(i).get(0), rekenRoute.get(i).get(1))) {
                        afstand = calcPyth(currentX, currentY, rekenRoute.get(i).get(0), rekenRoute.get(i).get(1));

                        langste = i;
                    }
                }

                totaleAfstand += calcPyth(0, 0, rekenRoute.get(langste).get(0), rekenRoute.get(langste).get(1));

                ArrayList<Integer> innerGesorteerd;
                innerGesorteerd = new ArrayList<>();
                innerGesorteerd.add(rekenRoute.get(langste).get(0));
                innerGesorteerd.add(rekenRoute.get(langste).get(1));
                gesorteerd.add(innerGesorteerd);


//                System.out.println(rekenRoute.get(langste).get(0) + " " + rekenRoute.get(langste).get(1));

                int kortsteX = rekenRoute.get(langste).get(0);
                int kortsteY = rekenRoute.get(langste).get(1);
                totaleAfstand += afstand;
                currentX = kortsteX;
                currentY = kortsteY;
                rekenRoute.remove(langste);
            }

        }
        ArrayList<Integer> zeroList = new ArrayList<>();
        zeroList.add(0);
        zeroList.add(0);
        gesorteerd.add(zeroList);
        Collections.reverse(gesorteerd);
        gesorteerd.add(zeroList);
        Collections.reverse(gesorteerd);
        return gesorteerd;
    }
    @Override
    public double getTotaleAfstand() {
        return totaleAfstand;
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

