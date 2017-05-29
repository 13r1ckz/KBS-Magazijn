import java.util.ArrayList;

public class TSPNN implements TSPAlgoritme {
    private int i;
    private int artikelnr;
    private int arrayListSize;
    private ArrayList<ArrayList<Integer>> gesorteerd = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> sortInner = new ArrayList<Integer>();
    private int currentX = 1;
    private int currentY = 0;

    public TSPNN(ArrayList<ArrayList<Integer>> ongesorteerd) {


        arrayListSize = ongesorteerd.size();
        //this ArrayList loops through the different products.
        for (int x = 0; x < arrayListSize; x++) {


            double afstand = Integer.MAX_VALUE;
            int kortste = 0;


            //This loop selects the shortest route between products.
            for (i = 0; i < ongesorteerd.size(); i++) {


                if (afstand > calcPyth(currentX, currentY, ongesorteerd.get(i).get(1), ongesorteerd.get(i).get(2))) {
                    afstand = calcPyth(currentX, currentY, ongesorteerd.get(i).get(1), ongesorteerd.get(i).get(2));
                    kortste = i;
                    artikelnr = ongesorteerd.get(i).get(0);
                }
            }
            //adds the sorted products to an arraylist, and sets the current position to the position of the last retrieved product.
            int kortsteX = ongesorteerd.get(kortste).get(1);
            int kortsteY = ongesorteerd.get(kortste).get(2);
            currentX = kortsteX;
            currentY = kortsteY;
            sortInner.add(currentX);
            sortInner.add(currentY);
            gesorteerd.add(sortInner);
            sortInner = new ArrayList<Integer>();
            ongesorteerd.remove(kortste);
        }
    }

    //this function calculates the distance between two points on the raster, using pythagoras.
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

    //This function calculates the shortest route.
    @Override
    public ArrayList<ArrayList<Integer>> berekenRoute() {
        return gesorteerd;
    }


    public ArrayList<ArrayList<Integer>> getGesorteerd() {
        return gesorteerd;
    }

}