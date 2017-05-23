//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Random;
//
//public class NearestNeighbour implements TSP{
//    private static HashMap<String, ArrayList<Integer>> coordinates = new HashMap<>();
//    private static ArrayList<Integer> coordinate = new ArrayList<>();
//    private static ArrayList<ArrayList<Integer>> gesorteerdCoordinates = new ArrayList<>();
//    private Random r = new Random();
//    private double totalLength = 0;
//
//
//
////    @Override
////    public double getTotalLength() {
////        return this.totalLength;
////    }
//
//    private void generateRandomNumbers(int aantal) {
////        System.out.print("[[0, 0], ");
////        for (int x = 0; x < aantal; x++) {
////            int randX = 1 + r.nextInt(5);
////            int randY = 1 + r.nextInt(5);
////            for (int y = 0; y < coordinates.size(); y++) {
////                if (coordinates.get("i" + y).get(0) == randX && coordinates.get("i" + y).get(1) == randY) {
////                    randX = 1 + r.nextInt(5);
////                    randY = 1 + r.nextInt(5);
////                    y = -1;
////                }
////            }
////            System.out.print("[" + randX + ", " + randY + "], ");
////            coordinate.add(randX);
////            coordinate.add(randY);
////            coordinates.put("i" + x, coordinate);
////            coordinate = new ArrayList<Integer>();
////        }
////        System.out.print("[0, 0]]");
//    }
//
//    @Override
//    public ArrayList<ArrayList<Integer>> berekenRoute(ArrayList<ArrayList<Integer>> locatiesOngesorteerd) {
//        System.out.println("Nearest neighbour:");
//        System.out.print("ongesorteerd - ");
//        int aantal = locatiesOngesorteerd.size();
//
//        final int[] currX = {0};
//        final int[] currY = {0};
//
//        coordinates.forEach((String key, ArrayList<Integer> coord) -> {
//            int index = 0;
//            double oldLength = Integer.MAX_VALUE;
//            for (int y = 0; y < coordinates.size(); y++) {
//                double length = calcPyth(currX[0], currY[0], locatiesOngesorteerd.get(y).get(0), locatiesOngesorteerd.get(y).get(1));
//                if((oldLength > length)) {
//                    index = y;
//                    oldLength = length;
//                }
//            }
//
//            currX[0] = locatiesOngesorteerd.get(index).get(0);
//            currY[0] = locatiesOngesorteerd.get(index).get(1);
//
//            gesorteerdCoordinates.add(locatiesOngesorteerd.get(index));
//            System.out.println(gesorteerdCoordinates);
//            this.totalLength += oldLength;
//        });
//        aantal = aantal - 1;
//        this.totalLength += calcPyth(locatiesOngesorteerd.get(aantal).get(0), locatiesOngesorteerd.get(aantal).get(1), 0, 0);
//        //add 0,0 coordinates to beginning and end.
//        coordinate = new ArrayList<Integer>();
//        coordinate.add(0);
//        coordinate.add(0);
//        gesorteerdCoordinates.add(coordinate);
//        Collections.reverse(gesorteerdCoordinates);
//        gesorteerdCoordinates.add(coordinate);
//        Collections.reverse(gesorteerdCoordinates);
//        System.out.println(gesorteerdCoordinates +  " H e  y   :]");
//        return gesorteerdCoordinates;
//    }
//
//    public double calcPyth(int beginX, int beginY, int eindX, int eindY) {
//        int verschilX;
//        int verschilY;
//        if (beginX != eindX) {
//            verschilX = Math.max(beginX, eindX) - Math.min(beginX, eindX);
//        } else {
//            verschilX = 0;
//        }
//        if (beginY != eindY) {
//            verschilY = Math.max(beginY, eindY) - Math.min(beginY, eindY);
//        } else {
//            verschilY = 0;
//        }
//        double i = Math.pow(verschilX, 2) + Math.pow(verschilY, 2);
//        return Math.sqrt(i);
//    }
//}
