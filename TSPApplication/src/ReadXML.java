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
    private int[] aListArray;
    public Order createOrder(String xml) {;
        try {
            File fXmlFile = new File(xml);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

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
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new Order();

    }


    public int[] getArtikelLijst() {
        return aListArray;
    }
}