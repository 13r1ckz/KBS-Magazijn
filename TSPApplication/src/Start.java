import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Start {
    public static void main(String[] ag) {
        ArrayList<String> oud = new ArrayList<>();
        oud.add("Test 1");
        ArrayList<String> nieuw = new ArrayList<>(oud);

        try {
            //In deze form is te zien hoe je precies data verstuurd naar Arduino. Dit is met de functie
            //ArduinoConnect.writeData(String); De form is uitgecomment omdat de form niet echt meer gebruikt wordt.
            // Hij kan wel aangepast worden op een manier waarvan je de pannel erin maakt.



            //Hier wordt een ReadXML object aangemaakt, deze heeft de functie createOrder, hiermee lees je de XML in,
            //en de getter van artikellijst.
            ReadXML read = new ReadXML();


            //Hier maak je een Order aan van de createOrder functie van ReadXML.
            Order o = read.createOrder("bestelling.xml");

            //Er wordt hier een database connectie gemaakt. de getter van ReadXML geeft de arraylijst met artikelen uit de XML mee.
            DBConnect DBConnection = new DBConnect(read.getArtikelLijst());

            //Er wordt een nieuwe arraylist aangemaakt waarin een arraylist zit.
            //in de binnenste arraylist wordt er per product een artikelnummer + X + Y meegegeven.
            //en in de buitenste arraylist worden de binnenste arraylists meegegeven, zodat er een duidelijk
            //overzicht van elk artikel waarbij de X, Y en artikelnummer mee wordt gegeven.
            ArrayList<ArrayList<Integer>> artikelsOngesorteerd = DBConnection.getLocaties();
            //Hij zet de ongesorteerde artikellijst gelijk aan de ongesorteerde artikelen in de artikelen.
            o.setOngesorteerd(artikelsOngesorteerd);
            o.setoOrder(artikelsOngesorteerd);
            //Er wordt hier een nieuwe instantie van TSPNN aangemaakt waaraan de ongesorteerdelijst mee wordt gegeven.
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //HIER MOETEN DE GEGEVENS DIE MOMENTEEL GEPRPINT WORDEN NOG IN EEN GESORTEERDE LIJST GEZET WORDEN
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            TSPNN algoritme = new TSPNN(o.getOngesorteerd());
            //De berekende route wordt gelijkgezet aan de route zodat deze hier ook te accessen is.

            ArrayList<ArrayList<Integer>> route = algoritme.berekenRoute();


            TSPPanel Jpan = new TSPPanel(o.getoOrder(), read.getuData(), DBConnection.getProductList(), algoritme.getGesorteerd());
            Jpan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //arduinoconnect object aangemaakt.
            ArduinoConnect obj = new ArduinoConnect(o.getoOrder().size(), o.getoOrder(),Jpan);
            obj.initialize();
            System.out.println(algoritme.getGesorteerd() + " he test");
            // IN TSPNN.JAVA WORDT ARRAYLIST GESORTEERD GERETURNED. GESORTEERD IS NOG LEEG EN MOET AAN ONGESORTEERDE ARRAYLIST DIE GESORTEERD MOETEN WORDEN GEZET.
            FilterOrder ordersend = new FilterOrder(algoritme.getGesorteerd());
            ordersend.ArduinoSend();
            String inputLine = ArduinoConnect.input.readLine();
            //System.out.println(inputLine);
            obj.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
