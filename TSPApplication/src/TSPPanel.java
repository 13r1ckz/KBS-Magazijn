import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TSPPanel extends JFrame {
    public TSPPanel(ArrayList<ArrayList<Integer>> artikelCount, ArrayList<ArrayList<String>> ArticleList, ArrayList<ArrayList<String>> orderData) {
        ArrayList<ArrayList<Integer>> artlist = artikelCount;
        ArrayList<ArrayList<String>> Ulist = ArticleList;
        ArrayList<ArrayList<String>> Olist = orderData;
        //System.out.println(artlist + "tsp panel");
        setTitle("TSPapp");
        FlowLayout layout = new FlowLayout();
        setLayout(layout);

        // grid panel
        TekenPanel grid = new TekenPanel(901, 701, 5, 5, artlist);
        add(grid);
        // pakbon
        System.out.println("2" + Olist);
        System.out.println("2" + Ulist);
        Pakbon pak = new Pakbon(701, Ulist, Olist);
        add(pak);
        pak.repaint();
        this.pack();
        setVisible(true); // toont het venster
    }

}
