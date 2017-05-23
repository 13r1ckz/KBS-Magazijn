import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

class Pakbon extends Canvas {
    private int H;
    private ArrayList<ArrayList<Integer>> art;
    private ArrayList<ArrayList<String>> user;
    private ArrayList<ArrayList<String>> orderL;
    Pakbon(int height, ArrayList<ArrayList<String>> userlist, ArrayList<ArrayList<String>> olist){
        orderL = olist;
        user = userlist;
        H = height;
        setPreferredSize(new Dimension(500,H));
    }
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Order nummer: ", 20 , 20);
        g.drawString("Voornaam: ", 20 , 40);
        g.drawString("Achternaam: ", 20 , 60);
        g.drawString("Straat: ", 20 , 80);
        g.drawString("Postcode: ", 20 , 100);
        g.drawString("Plaats: ", 20 , 120);
        g.drawString("datum: ", 20 , 140);
        for (int x = 1; x < user.size() + 1; x++) {
            if (x == 1) {
                g.drawString(String.valueOf(user.get(x - 1)), 130, 20 * x);
                System.out.println(user.get(x - 1));
            } else {
                g.drawString(String.valueOf(user.get(x - 1)), 130, 20 * x);
                System.out.println(user.get(x - 1));
            }
        }
        g.drawString("Product ID", 20, 180);
        g.drawString("Omschrijfing", 130, 180);
        g.drawString("Groote", 270, 180);
        g.drawLine(10,190, 450, 190);
        for(int x = 1; x < orderL.size()+1; x++){
            if (x == 1) {
                g.drawString(String.valueOf(orderL.get(x - 1).get(0)), 20, 190 + (20 * x));
                System.out.println(orderL.get(x - 1));
            } else {
                g.drawString(String.valueOf(orderL.get(x - 1).get(0)), 20, 190 + (20 * x));
                System.out.println(orderL.get(x - 1));
            }
        }

        for(int x = 1; x < orderL.size()+1; x++){
            if (x == 1) {
                g.drawString(String.valueOf(orderL.get(x - 1).get(1)), 130, 190 + (20 * x));
                System.out.println(orderL.get(x - 1));
            } else {
                g.drawString(String.valueOf(orderL.get(x - 1).get(1)), 130, 190 + (20 * x));
                System.out.println(orderL.get(x - 1));
            }
        }

        for(int x = 1; x < orderL.size()+1; x++){
            if (x == 1) {
                g.drawString(String.valueOf(orderL.get(x - 1).get(2)), 270, 190 + (20 * x));
                System.out.println(orderL.get(x - 1));
            } else {
                g.drawString(String.valueOf(orderL.get(x - 1).get(2)), 270, 190 + (20 * x));
                System.out.println(orderL.get(x - 1));
            }
        }
    }
}