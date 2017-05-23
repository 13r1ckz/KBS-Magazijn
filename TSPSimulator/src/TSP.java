import java.util.ArrayList;
public interface TSP {
    ArrayList<ArrayList<Integer>> generateRoute(int aantal);
    double getTotalLength();
    double calcPyth(int beginX, int beginY, int eindX, int eindY);
}
