import javax.swing.*;
import java.util.ArrayList;

public class Start {
    public static void main(String[] ag) {
        ArrayList<String> oud = new ArrayList<>();
        oud.add("Test 1");
        ArrayList<String> nieuw = new ArrayList<>(oud);

        try {
            //creates an instance of of READXML
            ReadXML read = new ReadXML();
            //uses the READXML instance to create an order of the given XML file.
            Order o = read.createOrder("bestelling.xml");
            //connects to a database.
            DBConnect DBConnection = new DBConnect(read.getArtikelLijst());

            //retrieves the locations of the different products
            ArrayList<ArrayList<Integer>> artikelsOngesorteerd = DBConnection.getLocaties();
            o.setOngesorteerd(artikelsOngesorteerd);
            o.setoOrder(artikelsOngesorteerd);

            //a new instance of the algoritm is created here.
            TSPNN algoritme = new TSPNN(o.getOngesorteerd());

            TSPPanel Jpan = new TSPPanel(o.getoOrder(), read.getuData(), DBConnection.getProductList(), algoritme.getGesorteerd());

            Jpan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ArduinoConnect obj = new ArduinoConnect(o.getoOrder().size(), o.getoOrder(),Jpan);
            //initialises the arduino object, preparing it for use.
            obj.initialize();


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
