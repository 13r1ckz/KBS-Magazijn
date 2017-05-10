import java.util.ArrayList;

public class Start {
    public static void main(String[] ag) {
        try {

            ReadXML read = new ReadXML();

            ArduinoConnect obj = new ArduinoConnect();
            obj.initialize();
            Order o = read.createOrder("bestelling.xml");
            DBConnect DBConnection = new DBConnect(read.getArtikelLijst());
            ArrayList<ArrayList<Integer>> artikelsOngesorteerd = DBConnection.getLocaties();
            o.setOngesorteerd(artikelsOngesorteerd);
            TSPNN algoritme = new TSPNN(o.getOngesorteerd());
            ArrayList<ArrayList<Integer>> route =  algoritme.berekenRoute();
            // IN TSPNN.JAVA WORDT ARRAYLIST GESORTEERD GERETURNED. GESORTEERD IS NOG LEEG EN MOET AAN ONGESORTEERDE ARRAYLIST DIE GESORTEERD MOETEN WORDEN GEZET.
            String inputLine = ArduinoConnect.input.readLine();
            System.out.println(inputLine);
            obj.close();
        }
        catch(Exception e) {

        }
    }
}
