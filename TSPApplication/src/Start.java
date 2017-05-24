import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Start {
    public static void main(String[] ag) {
        ArrayList<String> oud = new ArrayList<>();
        oud.add("Test 1");
        ArrayList<String> nieuw = new ArrayList<>(oud);

        try {
            ReadXML read = new ReadXML();
            Order o = read.createOrder("bestelling.xml");
            DBConnect DBConnection = new DBConnect(read.getArtikelLijst());
            ArrayList<ArrayList<Integer>> artikelsOngesorteerd = DBConnection.getLocaties();
            o.setOngesorteerd(artikelsOngesorteerd);
            o.setoOrder(artikelsOngesorteerd);
            TSPNN algoritme = new TSPNN(o.getOngesorteerd());
            TSPPanel Jpan = new TSPPanel(o.getoOrder(), read.getuData(), DBConnection.getProductList(), algoritme.getGesorteerd());
            Jpan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ArduinoConnect obj = new ArduinoConnect(o.getoOrder().size(), o.getoOrder(),Jpan);
            obj.initialize();
            System.out.println(algoritme.getGesorteerd() + " he test");
            FilterOrder ordersend = new FilterOrder(algoritme.getGesorteerd());
            ordersend.ArduinoSend();
            String inputLine = ArduinoConnect.input.readLine();
            obj.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
