import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
public class NearestNeighbourUntangle implements TSP {
    private static HashMap<String, ArrayList<Integer>> coordinates = new HashMap<>();
    private static ArrayList<Integer> coordinate = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> gesorteerd = new ArrayList<ArrayList<Integer>>();
    private Random r = new Random();
    private double totalLength = 0;

    @Override
    public ArrayList<ArrayList<Integer>> berekenRoute(ArrayList<ArrayList<Integer>> locatiesOngesorteerd) {
        ArrayList<ArrayList<Integer>> rekenRoute = new ArrayList<>(locatiesOngesorteerd);
        int arrayListSize = rekenRoute.size();
        int currentX = 0;
        int currentY = 0;

        for (int x = 0; x < arrayListSize; x++) {
            double afstand = 100;
            int kortste = 0;
            for (int i = 0; i < rekenRoute.size(); i++) {
                if (afstand > calcPyth(currentX, currentY , rekenRoute.get(i).get(0), rekenRoute.get(i).get(1))){
                    afstand = calcPyth(currentX, currentY , rekenRoute.get(i).get(0), rekenRoute.get(i).get(1));
                    kortste = i;
                }
            }
            if (x == 0){
                calcPyth(0, 0, rekenRoute.get(kortste).get(0), rekenRoute.get(kortste).get(1));
            }
            if (x == arrayListSize-1){
                calcPyth(0, 0, rekenRoute.get(kortste).get(0), rekenRoute.get(kortste).get(1));
            }
            System.out.println(rekenRoute.get(kortste).get(0) +" " + rekenRoute.get(kortste).get(1));
            ArrayList<Integer> inner = new ArrayList<>();
            inner.add(rekenRoute.get(kortste).get(0));
            inner.add(rekenRoute.get(kortste).get(1));
            gesorteerd.add(inner);
            int kortsteX = rekenRoute.get(kortste).get(0);
            int kortsteY = rekenRoute.get(kortste).get(1);
            currentX = kortsteX;
            currentY = kortsteY;
            rekenRoute.remove(kortste);
        }
        ArrayList<Integer> zeroList = new ArrayList<>();
        zeroList.add(0);
        zeroList.add(0);
        gesorteerd.add(zeroList);
        System.out.println(gesorteerd);
        Collections.reverse(gesorteerd);
        gesorteerd.add(zeroList);
        Collections.reverse(gesorteerd);

        /* Unwinded list.
           get the values of A,B,C,D
           and test if A,C,B,D is shorter.
         */
        ArrayList<ArrayList<Integer>> finalList = new ArrayList<>();
        for (int y = 0; y < gesorteerd.size(); y++){
            finalList.add(gesorteerd.get(y));
        }

        System.out.print("Nearest Neighbor : ");
        for(int i = 0; i < gesorteerd.size(); i++) {
            System.out.print(gesorteerd.get(i));
        }
        System.out.println();

        /* Sort everything using UNWIND.*/
        for (int y = 0; y < gesorteerd.size(); y++) {

            try {
                double total_length = 0.0;
                total_length += calcPyth(gesorteerd.get(y).get(0), gesorteerd.get(y).get(1), gesorteerd.get(y+1).get(0), gesorteerd.get(y+1).get(1));
                total_length += calcPyth(gesorteerd.get(y+1).get(0), gesorteerd.get(y+1).get(1), gesorteerd.get(y+2).get(0), gesorteerd.get(y+2).get(1));
                total_length += calcPyth(gesorteerd.get(y+2).get(0), gesorteerd.get(y+2).get(1), gesorteerd.get(y+3).get(0), gesorteerd.get(y+3).get(1));
                System.out.println(y + " - " + (y+1) + " - " + (y+2) + " - " + (y+3) + ": " + total_length);
                double total_length_swapped = 0.0;
                total_length_swapped += calcPyth(gesorteerd.get(y).get(0), gesorteerd.get(y).get(1), gesorteerd.get(y+2).get(0), gesorteerd.get(y+2).get(1));
                total_length_swapped += calcPyth(gesorteerd.get(y+2).get(0), gesorteerd.get(y+2).get(1), gesorteerd.get(y+1).get(0), gesorteerd.get(y+1).get(1));
                total_length_swapped += calcPyth(gesorteerd.get(y+1).get(0), gesorteerd.get(y+1).get(1), gesorteerd.get(y+3).get(0), gesorteerd.get(y+3).get(1));
                System.out.println(y + " - " + (y+2) + " - " + (y+1) + " - " + (y+3) + ": " + total_length_swapped);
                System.out.println();
                if(total_length_swapped < total_length){
                    /* swap the values! */
                    ArrayList<Integer> ACC = new ArrayList<>();
                    ACC = finalList.get(y+1);
                    finalList.set((y+1),finalList.get(y+2));
                    finalList.set((y+2),ACC);
                }
            }
            catch (Exception nx){}
        }
        for(int i = 0; i < finalList.size()-1; i++){
            totalLength += this.calcPyth(finalList.get(i).get(0),finalList.get(i).get(1), finalList.get(i+1).get(0),finalList.get(i+1).get(1));
        }
        return finalList;
    }

    @Override
    public double calcPyth(int beginX, int beginY, int eindX, int eindY)
    {
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

    @Override
    public double getTotaleAfstand() {
        return this.totalLength;
    }
}
