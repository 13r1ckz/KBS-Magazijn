import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by jaspe on 19-5-2017.
 */
public class NextFitUnwind implements TSP {

    private static HashMap<String, ArrayList<Integer>> coordinates = new HashMap<>();
    private static ArrayList<Integer> coordinate = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> gesorteerdCoordinates = new ArrayList<>();
    private Random r = new Random();
    private double totalLength = 0;

    @Override
    public ArrayList<ArrayList<Integer>> generateRoute(int aantal) {
        System.out.println("Unwind:");
        System.out.print("ongesorteerd - ");
        generateRandomNumbers(aantal);
        System.out.println();
        ArrayList<String> keys = new ArrayList<>();

        final int[] currX = {0};
        final int[] currY = {0};

        /* Nearest Neighbor */
        coordinates.forEach((String key, ArrayList<Integer> coord) -> {
            int index = -1;
            double oldLength = Integer.MAX_VALUE;

            /* For each thing in the coordinate list */
            for (int y = 0; y < coordinates.size(); y++) {
                /* Calculate the path length between the X and Y and the OTHER x and Y. */
                double length = calcPyth(currX[0], currY[0], coordinates.get("i" + y).get(0), coordinates.get("i" + y).get(1));
                if ((oldLength > length) && (keys.indexOf("i" + y) == -1)) {
                    index = y;
                    oldLength = length;
                }
            }

            /* Swap the current X and Y */
            currX[0] = coordinates.get("i" + index).get(0);
            currY[0] = coordinates.get("i" + index).get(1);

            gesorteerdCoordinates.add(coordinates.get("i" + index));
            keys.add("i" + index);
            this.totalLength += oldLength;
        });



        aantal = aantal - 1;
        this.totalLength += calcPyth(coordinates.get("i" + aantal).get(0), coordinates.get("i" + aantal).get(1), 0, 0);
        //add 0,0 coordinates to beginning and end.
        coordinate = new ArrayList<Integer>();
        coordinate.add(0);
        coordinate.add(0);
        gesorteerdCoordinates.add(coordinate);
        Collections.reverse(gesorteerdCoordinates);
        gesorteerdCoordinates.add(coordinate);
        Collections.reverse(gesorteerdCoordinates);

        /* Unwinded list.
           get the values of A,B,C,D
           and test if A,C,B,D is shorter.
         */
        ArrayList<ArrayList<Integer>> finalList = new ArrayList<>();
        for (int y = 0; y < gesorteerdCoordinates.size(); y++){
            finalList.add(gesorteerdCoordinates.get(y));
        }

        System.out.print("Nearest Neighbor : ");
        for(int i = 0; i < gesorteerdCoordinates.size(); i++) {
            System.out.print(gesorteerdCoordinates.get(i));
        }
        System.out.println();

        for (int y = 0; y < gesorteerdCoordinates.size(); y++) {

            try {
                double total_length = 0.0;
                total_length += calcPyth(gesorteerdCoordinates.get(y).get(0), gesorteerdCoordinates.get(y).get(1), gesorteerdCoordinates.get(y+1).get(0), gesorteerdCoordinates.get(y+1).get(1));
                total_length += calcPyth(gesorteerdCoordinates.get(y+1).get(0), gesorteerdCoordinates.get(y+1).get(1), gesorteerdCoordinates.get(y+2).get(0), gesorteerdCoordinates.get(y+2).get(1));
                total_length += calcPyth(gesorteerdCoordinates.get(y+2).get(0), gesorteerdCoordinates.get(y+2).get(1), gesorteerdCoordinates.get(y+3).get(0), gesorteerdCoordinates.get(y+3).get(1));
                System.out.println(y + " - " + (y+1) + " - " + (y+2) + " - " + (y+3) + ": " + total_length);
                double total_length_swapped = 0.0;
                total_length_swapped += calcPyth(gesorteerdCoordinates.get(y).get(0), gesorteerdCoordinates.get(y).get(1), gesorteerdCoordinates.get(y+2).get(0), gesorteerdCoordinates.get(y+2).get(1));
                total_length_swapped += calcPyth(gesorteerdCoordinates.get(y+2).get(0), gesorteerdCoordinates.get(y+2).get(1), gesorteerdCoordinates.get(y+1).get(0), gesorteerdCoordinates.get(y+1).get(1));
                total_length_swapped += calcPyth(gesorteerdCoordinates.get(y+1).get(0), gesorteerdCoordinates.get(y+1).get(1), gesorteerdCoordinates.get(y+3).get(0), gesorteerdCoordinates.get(y+3).get(1));
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

        return finalList;
    }

    private void generateRandomNumbers(int aantal)
    {
        /* Start node */
        System.out.print("[[0, 0], ");

        /* Do this aantal times */
        for (int x = 0; x < aantal; x++) {

            /* Random locations */
            int randX = 1 + r.nextInt(5);
            int randY = 1 + r.nextInt(5);
            for (int y = 0; y < coordinates.size(); y++) {

                /* Check if this location is taken */
                if (coordinates.get("i" + y).get(0) == randX && coordinates.get("i" + y).get(1) == randY) {
                    randX = 1 + r.nextInt(5);
                    randY = 1 + r.nextInt(5);
                    y = -1;
                }
            }

            /* Print the location */
            System.out.print("[" + randX + ", " + randY + "], ");
            coordinate.add(randX);
            coordinate.add(randY);
            coordinates.put("i" + x, coordinate);
            coordinate = new ArrayList<Integer>();
        }

        /* Final node */
        System.out.print("[0, 0]]");
    }

    @Override
    public double getTotalLength()
    {
        return this.totalLength;
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
}
