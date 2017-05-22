import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TSPPanel extends JFrame {
    public TSPPanel(ArrayList<ArrayList<Integer>> artikelCount, ArrayList<ArrayList<String>> ArticleList, ArrayList<ArrayList<String>> orderData, ArrayList<ArrayList<Integer>> route ) {
        ArrayList<ArrayList<Integer>> artlist = artikelCount;
        ArrayList<ArrayList<String>> Ulist = ArticleList;
        ArrayList<ArrayList<String>> Olist = orderData;
        setTitle("TSPapp");
        FlowLayout layout = new FlowLayout();
        setLayout(layout);

        // grid panel
        TekenPanel grid = new TekenPanel(1081, 701, 5, 6, artlist, route);
        add(grid);
        // pakbon
        Pakbon pak = new Pakbon(701, Ulist, Olist);
        add(pak);
        pak.repaint();
        this.pack();
        setVisible(true); // toont het venster
    }

}
