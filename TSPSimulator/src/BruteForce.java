import java.util.ArrayList;
import java.util.Random;

public class BruteForce implements TSP {
    private ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
    private ArrayList<Integer> inner = new ArrayList<>();
    private int routeLength = 100;
    private double temp;
    private double terugweg;
    private double heenweg;
    private String[] kortsteIndex;
    private double shortestRoute = Integer.MAX_VALUE;
    private int teller;
    private ArrayList<ArrayList<Integer>> GesorteerdOuter = new ArrayList<>();


    public ArrayList<ArrayList<Integer>> GenerateRoute(int aantal) {
        Random r = new Random();
        for (int x = 0; x < aantal; x++) {
            int randX = 1 + r.nextInt(5);
            int randY = 1 + r.nextInt(5);
            for (int i = 0; i < outer.size(); i++) {
                if (outer.get(i).get(0) == randX && outer.get(i).get(1) == randY) {
                    randX = 1 + r.nextInt(5);
                    randY = 1 + r.nextInt(5);
                    i = -1;
                }
            }
            inner.add(randX);
            inner.add(randY);
            outer.add(inner);
            inner = new ArrayList<>();
        }

        System.out.println("Random Locations:");
        for (int y = 0; y < outer.size(); y++) {
            System.out.println("x = " + outer.get(y).get(0) + ", y = " + outer.get(y).get(1));
            double length = calcPyth(outer.get(y).get(0), outer.get(y).get(1), 0, 0);
        }
        System.out.println("_________________________________________________________________");
        return outer;
    }

    public void printCombinations(ArrayList<ArrayList<Integer>> a) {
        System.out.println("Calculating shortest route...");
        String[] s = new String[a.size()];
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
            count += 1;
            String temp = String.valueOf(count);
            s[i] = temp;
        }
        permutation(s, 0, s.length - 1, a);

    }

    private void permutation(String[] s, int firstIndex, int lastIndex, ArrayList<ArrayList<Integer>> a) {
        if (lastIndex - firstIndex == 1) {
            print(s);
            selectShortest(s, a);
            swap(firstIndex, lastIndex, s);
            print(s);
            selectShortest(s, a);
            swap(firstIndex, lastIndex, s);
        } else {
            for (int i = firstIndex, j = 0; i <= lastIndex; i++, j++) {
                swap(firstIndex, firstIndex + j, s);
                /*With current initial String(s) find combinations for rest of the strings */
                permutation(s, firstIndex + 1, lastIndex, a);
                /*restore the order in string array */
                swap(firstIndex, firstIndex + j, s);
            }
        }
    }

    private void print(String[] s) {
        for (int i = 0; i < s.length; i++) {
//            System.out.println(Integer.parseInt(s[i]) - 1 + "volgorde ");
        }
//        System.out.println();
    }

    private ArrayList<ArrayList<Integer>> selectShortest(String[] s, ArrayList<ArrayList<Integer>> a) {
        terugweg = 0;
        heenweg = 0;
        int startIndex = Integer.parseInt(s[0]) - 1;
        int startX = a.get(startIndex).get(0);
        int startY = a.get(startIndex).get(1);
        heenweg = calcPyth(startX, startY, 0, 0);
//        System.out.println("van punt 0.0 naar 1e locatie " + heenweg);
        for (int i = 0; i < a.size() - 1; i++) {
            int index1 = Integer.parseInt(s[i]) - 1;
            int index2 = Integer.parseInt(s[i + 1]) - 1;
//            System.out.println();
            int locationX1 = a.get(index1).get(0);
            int locationX2 = a.get(index2).get(0);
            int locationY1 = a.get(index1).get(1);
            int locationY2 = a.get(index2).get(1);
//            System.out.println(calcPyth(locationX1, locationY1, locationX2, locationY2) + " afstand tussen punten : " + i + " en "+ Integer.valueOf(i + 1) );
            if (i == a.size() - 2) {
                terugweg = calcPyth(locationX2, locationY2, 0, 0);
//                System.out.println(terugweg + " van punt laatste tot 0");
            }
            temp += calcPyth(locationX1, locationY1, locationX2, locationY2);
        }
        temp = temp + heenweg + terugweg;
//        System.out.println(temp + " Dit is de totale lengte van de route.");
        if (temp < shortestRoute) {
            shortestRoute = temp;
            kortsteIndex = new String[a.size()];
            for (int i = 0; i < s.length; i++) {
                kortsteIndex[i] = s[i];
            }
        }
        for (int o = 0; o < kortsteIndex.length; o++) {
//            System.out.println(Integer.parseInt(kortsteIndex[o] )-1 + " So far shortest route.");
        }
        temp = 0;
//        System.out.println(shortestRoute + " Dit is de kortste route");
        teller += 1;
        if (teller == calcFactorial(kortsteIndex.length)) {
            GesorteerdOuter = new ArrayList<>();
            ArrayList<Integer> GesorteerdInner = new ArrayList<>();

            for (int i = 0; i < kortsteIndex.length; i++) {
                GesorteerdInner.add(a.get(Integer.parseInt(kortsteIndex[i]) - 1).get(0));
                GesorteerdInner.add(a.get(Integer.parseInt(kortsteIndex[i]) - 1).get(1));
                GesorteerdOuter.add(GesorteerdInner);
                GesorteerdInner = new ArrayList<>();
            }
            //System.out.println(GesorteerdOuter);
            return GesorteerdOuter;

        }
        return null;
//        System.out.println("______________________________________________________");

    }

    private static void swap(int firstIndex, int lastIndex, String[] s) {
        String temp = s[lastIndex];
        s[lastIndex] = s[firstIndex];
        s[firstIndex] = temp;
    }

    private int calcFactorial(int nummer) {
        int factorial = 1;
        for (int i = 0; i < nummer; nummer--) {
            factorial = factorial * nummer;
        }
        return factorial;
    }

    public ArrayList<ArrayList<Integer>> getGesorteerdOuter() {
        return GesorteerdOuter;
    }

    @Override
    public ArrayList<ArrayList<Integer>> berekenRoute() {
        return null;
    }

    @Override
    public double calcPyth(int beginX, int beginY, int eindX, int eindY) {
        int verschilX;
        int verschilY;
        int overstaandeZijde = beginX - eindX;
        int aanliggendeZijde = beginY - eindY;
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
