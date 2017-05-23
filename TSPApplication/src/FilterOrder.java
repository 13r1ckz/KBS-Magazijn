import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gerjo on 5/23/2017.
 */
public class FilterOrder {
    ArrayList<ArrayList<Integer>> listOrder;
    ArrayList<Integer> xList = new ArrayList<>();
    ArrayList<Integer> yList = new ArrayList<>();

    public FilterOrder(ArrayList<ArrayList<Integer>> gesorteerd){
        listOrder = gesorteerd;
        for(int i = 0; i <= listOrder.size() - 1; i++){
            xList.add(listOrder.get(i).get(0));
            yList.add(listOrder.get(i).get(1));
        }
    }
    public void ArduinoSend() throws IOException {
        int x = 0;
        int y = 0;
        ArduinoConnect.writeData(String.valueOf(xList.size()));
        for(int i = 0; i < (xList.size() * 2); i++) {
            while (i < xList.size()) {
                ArduinoConnect.writeData(String.valueOf(xList.get(x)));
                x++;
                i++;
            }
            while (i >= xList.size() && i <= (xList.size() * 2)- 1){
                ArduinoConnect.writeData(String.valueOf(yList.get(y)));
                y++;
                i++;
            }
        }
    }
}
