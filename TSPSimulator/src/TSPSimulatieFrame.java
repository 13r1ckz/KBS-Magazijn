import javax.swing.*;
import java.awt.*;

public class TSPSimulatieFrame extends JFrame {
    private JFrame frame;
    private String title;
    private int width, height;


    public TSPSimulatieFrame(int width, int height) {
        this("TSP simulator",width,height);
    }
    public TSPSimulatieFrame(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void createDisplay() {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setResizable(false);
        TSPSimulatiePanel panel = new TSPSimulatiePanel();
        frame.add(panel);
        repaint();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

