import javax.swing.*;
import java.util.ArrayList;

public class Start {
    public static void main(String[] ag) {
        try {
            //In deze form is te zien hoe je precies data verstuurd naar Arduino. Dit is met de functie
            //ArduinoConnect.writeData(String); De form is uitgecomment omdat de form niet echt meer gebruikt wordt.
            // Hij kan wel aangepast worden op een manier waarvan je de pannel erin maakt.

//            Form f = new Form();


            //Hier wordt een ReadXML object aangemaakt, deze heeft de functie createOrder, hiermee lees je de XML in,
            //en de getter van artikellijst.
            ReadXML read = new ReadXML();

            //arduinoconnect object aangemaakt.
            ArduinoConnect obj = new ArduinoConnect();
            obj.initialize();

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
            System.out.println("artikellengte " + o.getOngesorteerd().size());
            //Er wordt hier een nieuwe instantie van TSPNN aangemaakt waaraan de ongesorteerdelijst mee wordt gegeven.


            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //HIER MOETEN DE GEGEVENS DIE MOMENTEEL GEPRPINT WORDEN NOG IN EEN GESORTEERDE LIJST GEZET WORDEN
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            TSPNN algoritme = new TSPNN(o.getOngesorteerd());
            System.out.println("artikellengte " + o.getOngesorteerd().size());
            //De berekende route wordt gelijkgezet aan de route zodat deze hier ook te accessen is.
            ArrayList<ArrayList<Integer>> route = algoritme.berekenRoute();

            TSPPanel Jpan = new TSPPanel(o.getOngesorteerd().size());
            Jpan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // IN TSPNN.JAVA WORDT ARRAYLIST GESORTEERD GERETURNED. GESORTEERD IS NOG LEEG EN MOET AAN ONGESORTEERDE ARRAYLIST DIE GESORTEERD MOETEN WORDEN GEZET.
            String inputLine = ArduinoConnect.input.readLine();
            System.out.println(inputLine);
            obj.close();
        }
        catch(Exception e) {

        }
    }
}
