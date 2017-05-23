import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class TSPSimulatieFrame extends JFrame implements ActionListener {
    private JFrame frame;
    private String title;
    private int width, height;
    private JButton btnBerekenRoute, btnNearestNeighbour, btnAntColony, btnUnwindNN, btnBruteForce;
    private JTextField jTextFieldGridGrootte, jTextFieldAantalLocaties, jTextFieldAantalTesten;
    private int aantalLocaties;
    private ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
    private ArrayList<Integer> inner = new ArrayList<>();
    private TSPSimulatiePanel panel;

    public TSPSimulatieFrame(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public ArrayList<ArrayList<Integer>> GenerateRoute(int aantalProducten) {
        if (aantalProducten < 3) {
            aantalProducten = 3;
        }
        Random r = new Random();
        for (int x = 0; x < aantalProducten; x++) {
            int randX;
            int randY;
            int gridGrootte = 5;
            try {
                gridGrootte = Integer.parseInt(jTextFieldGridGrootte.getText());
                randX = r.nextInt(gridGrootte);
                randY = r.nextInt(gridGrootte);
            }
            catch(NullPointerException | NumberFormatException e) {
                randX = 5;
                randY = 5;
            }
            for (int i = 0; i < outer.size(); i++) {
                if ((outer.get(i).get(0) == randX && outer.get(i).get(1) == randY) || (randX == 0 && randY == 0 )  ) {
                    randX = r.nextInt(gridGrootte);
                    randY = r.nextInt(gridGrootte);
                    i = -1;
                }
            }
            inner.add(randX);
            inner.add(randY);
            outer.add(inner);
            inner = new ArrayList<>();
        }
        for (int y = 0; y < outer.size(); y++) {
            System.out.println("x = " + outer.get(y).get(0) + ", y = " + outer.get(y).get(1));
        }
        return outer;
    }

    public void createDisplay(ArrayList<ArrayList<Integer>> locatiesOngesorteerd) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setResizable(false);
        btnBerekenRoute = new JButton("Bereken punten");
        btnBerekenRoute.addActionListener(this);
        btnNearestNeighbour = new JButton("Nearest neighbour");
        btnNearestNeighbour.addActionListener(this);
        btnAntColony = new JButton("Ant colony");
        btnUnwindNN = new JButton("Unwind NN");
        btnBruteForce = new JButton("Brute force");
        JLabel jLabelGridGrootte = new JLabel("Gridgrootte");
        JLabel jLabelAantalLocaties = new JLabel("Aantal locaties ");
        JLabel jLabelAantalTesten = new JLabel("Aantal testen ");
        jTextFieldGridGrootte = new JTextField(10);
        jTextFieldGridGrootte.addKeyListener(keyListener);
        jTextFieldAantalLocaties = new JTextField(10);
        jTextFieldAantalLocaties.addKeyListener(keyListener);
        jTextFieldAantalTesten = new JTextField(10);
        jTextFieldAantalTesten.addKeyListener(keyListener);
        panel = new TSPSimulatiePanel(this, locatiesOngesorteerd);
        panel.setLocaties(locatiesOngesorteerd);
        frame.add(jLabelGridGrootte);
        frame.add(jTextFieldGridGrootte);
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
        btnBruteForce.addActionListener(this);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //set default grid size
        panel.setGridGetal(5);
        frame.setVisible(true);
    }

    public int getAantalLocaties() {
        return this.aantalLocaties;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnBerekenRoute) {
            outer = new ArrayList<>();
            try {
                panel.setGridGetal(Integer.parseInt(jTextFieldGridGrootte.getText()));
                panel.setLocaties(GenerateRoute(Integer.parseInt(jTextFieldAantalLocaties.getText())));
            }
            catch(Exception e){
                return;
            }
            panel.setRoute(null);
            panel.repaintPanel();
        }
        if (actionEvent.getSource() == btnBruteForce){
            BruteForce bruteForce = new BruteForce();
            this.revalidate();
            ArrayList<ArrayList<Integer>> gesorteerdeLijst = bruteForce.berekenRoute(panel.getLocaties());
            panel.setRoute(gesorteerdeLijst);
            panel.repaintPanel();
        }
    }

    private KeyListener keyListener = new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                getToolkit().beep();
                e.consume();
            }
        }
    };
}

