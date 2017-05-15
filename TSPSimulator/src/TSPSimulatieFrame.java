import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TSPSimulatieFrame extends JFrame implements ActionListener {
    private JFrame frame;
    private String title;
    private int width, height;
    private JButton btnBerekenRoute, btnNearestNeighbour, btnAntColony, btnUnwindNN, btnBruteForce;
    private JTextField jTextFieldAantalLocaties, jTextFieldAantalTesten ;
    private int aantalLocaties;



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
        btnBerekenRoute = new JButton("Bereken punten");
        btnBerekenRoute.addActionListener(this);
        btnNearestNeighbour = new JButton("Nearest neighbour");
        btnAntColony = new JButton("Ant colony");
        btnUnwindNN = new JButton("Unwind NN");
        btnBruteForce = new JButton("Brute force");
        JLabel jLabelAantalLocaties = new JLabel("Aantal locaties ");
        JLabel jLabelAantalTesten = new JLabel("Aantal testen ");
        jTextFieldAantalLocaties  = new JTextField(10);
        jTextFieldAantalTesten = new JTextField(10);

        TSPSimulatiePanel panel = new TSPSimulatiePanel(this);
        frame.add(panel);
        frame.add(jLabelAantalLocaties);
        frame.add(jTextFieldAantalLocaties);
        frame.add(jLabelAantalTesten);
        frame.add(jTextFieldAantalTesten);
        frame.add(btnBerekenRoute);
        frame.add(btnNearestNeighbour);
        frame.add(btnAntColony);
        frame.add(btnUnwindNN);
        frame.add(btnBruteForce);
        repaint();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public int getAantalLocaties() {
        return this.aantalLocaties;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnBerekenRoute) {
            this.aantalLocaties = Integer.parseInt(jTextFieldAantalLocaties.getText());
            repaint();

        }

    }
}

