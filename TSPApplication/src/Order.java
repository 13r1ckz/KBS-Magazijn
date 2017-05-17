import java.util.ArrayList;

public class Order {
    private ArrayList<ArrayList<Integer>> ongesorteerd;
    private ArrayList<ArrayList<Integer>> oOrder;
    private ArrayList gesorteerd;

// klantgegevens

    public Order(){

    }
    public Order(ArrayList<ArrayList<Integer>> ongesorteerd, ArrayList<ArrayList<Integer>> oOrder, ArrayList gesorteerd) {
        this.ongesorteerd = ongesorteerd;
        this.oOrder = oOrder;
        this.gesorteerd = gesorteerd;
    }

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

    public ArrayList<ArrayList<Integer>> getoOrder() {
        return oOrder;
    }

    public void setoOrder(ArrayList<ArrayList<Integer>> oOrder) {
        this.oOrder = oOrder;
    }
}
