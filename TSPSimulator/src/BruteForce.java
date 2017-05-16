import java.util.ArrayList;
import java.util.Random;

public class BruteForce implements TSP {
    private ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
    private ArrayList<Integer> inner = new ArrayList<>();



    public ArrayList<ArrayList<Integer>> GenerateRoute(int aantal) {
        //start at zero

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

    public static void printCombinations(ArrayList<ArrayList<Integer>> a) {
        String[] s = new String[a.size() ];
        int count = 0;
        for (int i =0; i < a.size() ; i++){
            System.out.println("ree");
            count+=1;
            String temp = String.valueOf(count);

            s[i] = temp;
        }


        permutation(s, 0, s.length - 1);
    }

    private static void permutation(String[] s, int firstIndex, int lastIndex) {

        if (lastIndex - firstIndex == 1) {
            print(s);
            swap(firstIndex, lastIndex, s);
            print(s);
			/*restore the order in string array*/
            swap(firstIndex, lastIndex, s);


        } else {
            for (int i = firstIndex, j = 0; i <= lastIndex; i++, j++) {
                swap(firstIndex, firstIndex + j, s);
				/*With current initial String(s) find combinations for rest of the strings */
                permutation(s, firstIndex + 1, lastIndex);
				/*restore the order in string array */
                swap(firstIndex, firstIndex + j, s);
            }
        }

    }

    private static void print(String[] s) {
        for (int i = 0; i < s.length; i++) {
            System.out.println(Integer.parseInt(s[i]) - 1);
        }
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
        if (beginX != eindX){
            verschilX = Math.max(beginX, eindX) - Math.min(beginX, eindX);
        }
        else{
            verschilX = 0;
        }
        if (beginY != eindY ){
            verschilY = Math.max(beginY, eindY) - Math.min(beginY, eindY);
        }
        else {
            verschilY = 0;
        }
        double i = Math.pow(verschilX,2) + Math.pow(verschilY, 2);
        double j = Math.sqrt(i);
        return j;
    }
}
