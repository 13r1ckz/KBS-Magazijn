import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BruteForce implements TSP {


    private double temp;
    private double terugweg;
    private double heenweg;
    private String[] kortsteIndex;
    private double shortestRoute = Integer.MAX_VALUE;
    private int teller;
    private int teller2;
    private ArrayList<ArrayList<Integer>> GesorteerdOuter = new ArrayList<>();
    private String[] s;
    private int firstIndex;
    private int lastIndex;



    public ArrayList<ArrayList<Integer>> permutation(ArrayList<ArrayList<Integer>> a) {
        if (teller2 == 0) {
            teller2 += 1;
            firstIndex = 0;
            s = new String[a.size()];
            int count = 0;
            for (int i = 0; i < a.size(); i++) {
                count += 1;
                String temp = String.valueOf(count);
                s[i] = temp;
            }
            lastIndex = s.length - 1;
        }
        if (lastIndex - firstIndex == 1) {
            selectShortest(s, a);
            swap(firstIndex, lastIndex, s);
            selectShortest(s, a);
            swap(firstIndex, lastIndex, s);
            teller2 += 1;
        } else {
            for (int i = firstIndex, j = 0; i <= lastIndex; i++, j++) {
                swap(firstIndex, firstIndex + j, s);
                firstIndex += 1;
                permutation(a);
                firstIndex -= 1;
                swap(firstIndex, firstIndex + j, s);
            }
        }
        return GesorteerdOuter;
    }

    private void selectShortest(String[] s, ArrayList<ArrayList<Integer>> a) {
        terugweg = 0;
        heenweg = 0;
        int startIndex = Integer.parseInt(s[0]) - 1;
        int startX = a.get(startIndex).get(0);
        int startY = a.get(startIndex).get(1);
        heenweg = calcPyth(startX, startY, 0, 0);

        for (int i = 0; i < a.size() - 1; i++) {
            int index1 = Integer.parseInt(s[i]) - 1;
            int index2 = Integer.parseInt(s[i + 1]) - 1;

            int locationX1 = a.get(index1).get(0);
            int locationX2 = a.get(index2).get(0);
            int locationY1 = a.get(index1).get(1);
            int locationY2 = a.get(index2).get(1);
            if (i == a.size() - 2) {
                terugweg = calcPyth(locationX2, locationY2, 0, 0);
            }
            temp += calcPyth(locationX1, locationY1, locationX2, locationY2);
        }
        temp = temp + heenweg + terugweg;
        if (temp < shortestRoute) {
            shortestRoute = temp;
            kortsteIndex = new String[a.size()];
            for (int i = 0; i < s.length; i++) {
                kortsteIndex[i] = s[i];
            }
        }
        for (int o = 0; o < kortsteIndex.length; o++) {
        }
        temp = 0;
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
            ArrayList<Integer> zeroList = new ArrayList<>();
            zeroList.add(0);
            zeroList.add(0);
            GesorteerdOuter.add(zeroList);
            Collections.reverse(GesorteerdOuter);
            GesorteerdOuter.add(zeroList);
            Collections.reverse(GesorteerdOuter);
            System.out.println(shortestRoute + " Dit is de kortste route");
        }
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
    public ArrayList<ArrayList<Integer>> berekenRoute(ArrayList<ArrayList<Integer>> locatiesOngesorteerd) {
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
