import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
public class ReadXML {
    private Document doc;
    private NodeList aList;
    private NodeList ULis;
    private int[] aListArray;
    private ArrayList uData;
    public Order createOrder(String xml) {;
        try {
            File fXmlFile = new File(xml);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            uData = new ArrayList();

            NodeList nList = doc.getElementsByTagName("bestelling");
            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                aList = doc.getElementsByTagName("artikelnr");

                aListArray = new int[aList.getLength()];
                for (int i=0; i< aList.getLength(); i++){
                    aListArray[i] = Integer.parseInt(eElement.getElementsByTagName("artikelnr").item(i).getTextContent());
                }
            }
            NodeList UList = doc.getElementsByTagName("bestelling");
            Node UNode = UList.item(0);
            Element UElement = (Element) UNode;
            ULis = doc.getElementsByTagName("ordernummer");
            uData.add(String.valueOf(UElement.getElementsByTagName("ordernummer").item(0).getTextContent()));
            ULis = doc.getElementsByTagName("klant");
            uData.add(String.valueOf(UElement.getElementsByTagName("voornaam").item(0).getTextContent()));
            uData.add(String.valueOf(UElement.getElementsByTagName("achternaam").item(0).getTextContent()));
            uData.add(String.valueOf(UElement.getElementsByTagName("adres").item(0).getTextContent()));
            uData.add(String.valueOf(UElement.getElementsByTagName("postcode").item(0).getTextContent()));
            uData.add(String.valueOf(UElement.getElementsByTagName("plaats").item(0).getTextContent()));
            ULis = doc.getElementsByTagName("bestelling");
            uData.add(String.valueOf(UElement.getElementsByTagName("datum").item(0).getTextContent()));


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Order();

    }


    public int[] getArtikelLijst() {
        return aListArray;
    }

    public ArrayList getuData() {
        return uData;
    }
}