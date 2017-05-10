import java.util.ArrayList;

public class Order {
    private ArrayList<ArrayList<Integer>> ongesorteerd;
    private ArrayList gesorteerd;

// klantgegevens

    public void setGesorteerd(ArrayList gesorteerd) {
        this.gesorteerd = gesorteerd;
    }

    public ArrayList getGesorteerd() {
        return gesorteerd;
    }

    public void setOngesorteerd(ArrayList<ArrayList<Integer>> ongesorteerd) {
        this.ongesorteerd = ongesorteerd;
    }

    public ArrayList<ArrayList<Integer>> getOngesorteerd() {
        return ongesorteerd;
    }
}
