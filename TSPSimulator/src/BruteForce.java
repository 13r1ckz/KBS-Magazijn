
import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

public class BruteForce implements TSP {
    private ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
    private ArrayList<Integer> inner = new ArrayList<>();
    private int routeLength = 100;
    private double temp;
    private int path;



    public ArrayList<ArrayList<Integer>> GenerateRoute(int aantal) {

        Random r = new Random();
        for (int x = 0; x < aantal; x++) {
            int randX = r.nextInt(5);
            int randY = r.nextInt(5);
            inner.add(randX);
            inner.add(randY);
            outer.add(inner);
            inner = new ArrayList<Integer>();
        }

        int shortestIndex = 0;
        double shortestRoute = 100.0;

        for (int y = 0; y < outer.size(); y++) {
            System.out.println("x = " + outer.get(y).get(0) + ", y = " + outer.get(y).get(1));

            double length = calcPyth(outer.get(y).get(0), outer.get(y).get(1), 0, 0);

        }
        return outer;
    }

    public void printCombinations(ArrayList<ArrayList<Integer>> a) {
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
            /*restore the order in string array*/
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
            System.out.println(Integer.parseInt(s[i]) - 1 + " volgorde");
        }
        System.out.println();
    }

    private void selectShortest(String[] s, ArrayList<ArrayList<Integer>> a) {
        ArrayList tijdelijkA = new ArrayList<Integer>();
        for (int i =0 ; i <s.length ; i++){

            tijdelijkA.add(s[i]);
        }
        tijdelijkA.add(0);
        Collections.reverse(tijdelijkA);
        tijdelijkA.add(0);
        Collections.reverse(tijdelijkA);
        String[] s2 = new String[a.size() + 2];
        for (int i = 0 ; i < tijdelijkA.size(); i++){
            s2[i] = String.valueOf(tijdelijkA.get(i));
        }





        for (int i = 0; i < a.size() - 1; i++) {

            int index1 = Integer.parseInt(s[i]) - 1;
            int index2 = Integer.parseInt(s[i + 1]) - 1;
            System.out.println();
            int locationX1 = a.get(index1).get(0);
            int locationX2 = a.get(index2).get(0);
            int locationY1 = a.get(index1).get(1);
            int locationY2 = a.get(index2).get(1);
            System.out.println(calcPyth(locationX1, locationY1, locationX2, locationY2) + " afstand tussen punten.");
            temp += calcPyth(locationX1, locationY1, locationX2, locationY2);

        }

        System.out.println(temp+ " totale lengte van de route.");
        temp=0;
        System.out.println();
    }


    private static void swap(int firstIndex, int lastIndex, String[] s) {
        String temp = s[lastIndex];
        s[lastIndex] = s[firstIndex];
        s[firstIndex] = temp;
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
        double j = Math.sqrt(i);
        return j;
    }
}
