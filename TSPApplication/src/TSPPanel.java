import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TSPPanel extends JFrame {
    public TSPPanel(ArrayList<ArrayList<Integer>> artikelCount) {
        ArrayList<ArrayList<Integer>> artlist = artikelCount;
        //System.out.println(artlist + "tsp panel");
        setTitle("TSPapp");
        setSize(1100, 800);
        setLayout(new FlowLayout());

        // hier komen de controls
        TekenPanel grid = new TekenPanel(901, 701, 5, 5, artlist);
        add(grid);
        setVisible(true); // toont het venster
    }

}
