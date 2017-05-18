import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class NearestNeighbourReversed {
    private static HashMap<String, ArrayList<Integer>> coordinates = new HashMap<>();
    private static ArrayList<Integer> coordinate = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> gesorteerdCoordinates = new ArrayList<>();

    ArrayList<ArrayList<Integer>> generateRoute(int aantal) {
        System.out.println("Nearest neighbour reversed:");
        System.out.print("ongesorteerd - ");
        generateRandomNumbers(aantal);
        System.out.println();

        ArrayList<String> keys = new ArrayList<>();

        final int[] currX = {0};
        final int[] currY = {0};

        double longestLength = 0;
        for (int y = 0; y < coordinates.size(); y++) {
            double length = calcPyth(0, 0, coordinates.get("i" + y).get(0), coordinates.get("i" + y).get(1));

            if (longestLength < length) {
                longestLength = length;

                currX[0] = coordinates.get("i" + y).get(0);
                currY[0] = coordinates.get("i" + y).get(1);
            }

        }

        coordinates.forEach((String key, ArrayList<Integer> coord) -> {
            int index = -1;
            double oldLength = Integer.MAX_VALUE;

            for (int y = 0; y < coordinates.size(); y++) {
                double length = calcPyth(currX[0], currY[0], coordinates.get("i" + y).get(0), coordinates.get("i" + y).get(1));

                if ((oldLength > length) && (keys.indexOf("i" + y) == -1)) {
                    index = y;
                    oldLength = length;
                }
            }

            currX[0] = coordinates.get("i" + index).get(0);
            currY[0] = coordinates.get("i" + index).get(1);

            gesorteerdCoordinates.add(coordinates.get("i" + index));
            keys.add("i" + index);
        });
        return gesorteerdCoordinates;
    }
    public void generateRandomNumbers(int aantal) {
        Random r = new Random();
        for (int x = 0; x < aantal; x++) {
            int randX = 1 + r.nextInt(5);
            int randY = 1 + r.nextInt(5);
            for (int y = 0; y < coordinates.size(); y++) {
                if (coordinates.get("i" + y).get(0) == randX && coordinates.get("i" + y).get(1) == randY) {
                    randX = 1 + r.nextInt(5);
                    randY = 1 + r.nextInt(5);
                    y = 0;
                }
            }

            System.out.print("[" + randX + ", " + randY + "], ");
            coordinate.add(randX);
            coordinate.add(randY);
            coordinates.put("i" + x, coordinate);
            coordinate = new ArrayList<Integer>();
        }
    }

    private static double calcPyth(int beginX, int beginY, int eindX, int eindY) {
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