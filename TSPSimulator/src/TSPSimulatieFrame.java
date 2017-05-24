import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class TSPSimulatieFrame extends JFrame implements ActionListener {
    private JMenuItem menuItem,menuItem2;
    private JFrame frame;
    private String title;
    private int width, height;
    private JButton btnMeerdereTesten,btnBerekenRouteSecondFrame,btnMindereTesten,btnBerekenRoute, btnNearestNeighbour, btnAntColony, btnUnwindNN, btnBruteForce, btnNNReversed;
    private JTextField jTextFieldGridGrootte, jTextFieldAantalLocaties, jTextFieldAantalTesten;
    private int aantalLocaties;
    private ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> locatiesOngesorteerd = new ArrayList<>();
    private ArrayList<Integer> inner = new ArrayList<>();
    private TSPSimulatiePanel panel;
    private boolean showFirstFrame = true;
    private double totaleAfstandNNReversed, totaleAfstandNN, totaleAfstandBruteForce, totaleAfstandUntangle;
    private double gemiddeldeNNReversed, gemiddeldeNN, gemiddeldeBruteForce, gemiddeldeUntangle;

    public TSPSimulatieFrame(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setResizable(false);
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
        this.locatiesOngesorteerd = locatiesOngesorteerd;
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("A Menu");
        menuBar.add(menu);
        menuItem = new JMenuItem("Single test", KeyEvent.VK_T);
        menuItem.addActionListener(this);
        menuItem2 = new JMenuItem("Multiple tests", KeyEvent.VK_T);
        menuItem2.addActionListener(this);
        menu.add(menuItem);
        menu.add(menuItem2);
        frame.setJMenuBar(menuBar);
        //if first frame must be shown, initialize components
        if(showFirstFrame) {
            btnBerekenRoute = new JButton("Bereken punten");
            btnBerekenRoute.addActionListener(this);
            btnNearestNeighbour = new JButton("Nearest neighbour");
            btnNearestNeighbour.addActionListener(this);
            btnNNReversed = new JButton("Reversed nearest neighbour");
            btnNNReversed.addActionListener(this);
            btnUnwindNN = new JButton("Unwind NN");
            btnUnwindNN.addActionListener(this);
            btnBruteForce = new JButton("Brute force");

            JLabel jLabelAantalLocaties = new JLabel("Aantal locaties ");
            JLabel jLabelGridGrootte = new JLabel("Gridgrootte");
            jTextFieldAantalLocaties = new JTextField(10);
            jTextFieldAantalLocaties.addKeyListener(keyListener);
            jTextFieldGridGrootte = new JTextField(10);
            jTextFieldGridGrootte.addKeyListener(keyListener);
            panel = new TSPSimulatiePanel(this, locatiesOngesorteerd);
            panel.setLocaties(locatiesOngesorteerd);
            frame.add(panel);
            frame.add(jLabelAantalLocaties);
            frame.add(jTextFieldAantalLocaties);
            frame.add(jLabelGridGrootte);
            frame.add(jTextFieldGridGrootte);
            frame.add(btnBerekenRoute);
            frame.add(btnNearestNeighbour);
            frame.add(btnNNReversed);
            frame.add(btnUnwindNN);
            frame.add(btnBruteForce);
            btnBruteForce.addActionListener(this);
            panel.setGridGetal(5);
            frame.setLayout(new FlowLayout());

        } else {
            frame.setLayout(new FlowLayout());
            JLabel jLabelAantalLocaties = new JLabel("Aantal locaties ");
            jTextFieldAantalLocaties.addKeyListener(keyListener);

            JLabel jLabelAantalTesten = new JLabel("Aantal testen");
            jTextFieldAantalTesten = new JTextField(4);
            jTextFieldAantalTesten.addKeyListener(keyListener);

            btnBerekenRouteSecondFrame = new JButton("Bereken");
            btnBerekenRouteSecondFrame.addActionListener(this);
            frame.add(jLabelAantalLocaties);
            frame.add(jTextFieldAantalLocaties);
            frame.add(jLabelAantalTesten);
            frame.add(jTextFieldAantalTesten);
            frame.add(btnBerekenRouteSecondFrame);
        }

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int getAantalLocaties() {
        return this.aantalLocaties;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //clear frame and add components for multiple tests.
        if(actionEvent.getSource() == menuItem2) {
            frame.getContentPane().removeAll();
            frame.repaint();
            showFirstFrame = false;
            createDisplay(locatiesOngesorteerd);
        }
        if(actionEvent.getSource() == menuItem) {
            frame.getContentPane().removeAll();
            frame.repaint();
            showFirstFrame = true;
            createDisplay(locatiesOngesorteerd);
        }
        //Calculate route a single time
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
        //Calculate route multiple times
        if (actionEvent.getSource() == btnBerekenRouteSecondFrame) {
            int aantaltesten = 0;
            try {
                aantaltesten = Integer.parseInt(jTextFieldAantalTesten.getText());
            }
            catch(NumberFormatException e) {

            }
            for(int x = 0; x <= aantaltesten; x++) {
                System.out.println(x);
                outer = new ArrayList<>();
                try {
                    panel.setGridGetal(aantaltesten);
                    panel.setLocaties(GenerateRoute(aantaltesten));
                    //NN REVERSED
                    NearestNeighbourReversedRewrk nnReversed = new NearestNeighbourReversedRewrk();
                    nnReversed.berekenRoute(panel.getLocaties());
                    totaleAfstandNNReversed += nnReversed.getTotaleAfstand();
                    gemiddeldeNNReversed = totaleAfstandNNReversed / aantaltesten;

                    //NN
                    NearestNeighbourRework nn = new NearestNeighbourRework();
                    nn.berekenRoute(panel.getLocaties());
                    totaleAfstandNN += nn.getTotaleAfstand();
                    gemiddeldeNN = totaleAfstandNN / aantaltesten;

                    //Brute Force
                    BruteForce brute = new BruteForce();
                    brute.berekenRoute(panel.getLocaties());
                    totaleAfstandBruteForce += brute.getTotaleAfstand();
                    gemiddeldeBruteForce = totaleAfstandBruteForce / aantaltesten;

                    //NearestNeighbourUntangle
                    NearestNeighbourUntangle unwindThis = new NearestNeighbourUntangle();
                    unwindThis.berekenRoute(panel.getLocaties());
                    totaleAfstandUntangle += unwindThis.getTotaleAfstand();
                    gemiddeldeUntangle = totaleAfstandUntangle / aantaltesten;
                }
                catch(Exception e){
                    continue;
                }
                System.out.println(totaleAfstandNNReversed + " totale afstand NN Reversed");
                System.out.println(totaleAfstandNN + " totale afstand NN");
                System.out.println(totaleAfstandBruteForce + " totale afstand Brute Force");
                System.out.println(totaleAfstandUntangle + " totale afstand Untangle");
                System.out.println(gemiddeldeNNReversed + " gemiddelde afstand NN Reversed");
                System.out.println(gemiddeldeNN + " gemiddelde afstand NN");
                System.out.println(gemiddeldeBruteForce + " gemiddelde afstand Brute Force");
                System.out.println(gemiddeldeUntangle + " gemiddelde afstand Untangle");
            }
        }
        if (actionEvent.getSource() == btnNearestNeighbour){
            NearestNeighbourRework nearestNeighbour = new NearestNeighbourRework();
            this.revalidate();
            ArrayList<ArrayList<Integer>> gesorteerdeLijst = nearestNeighbour.berekenRoute(panel.getLocaties());
            panel.setRoute(gesorteerdeLijst);
            panel.repaintPanel();
        }
        if (actionEvent.getSource() == btnNNReversed){
            NearestNeighbourReversedRewrk nnReversed = new NearestNeighbourReversedRewrk();
            this.revalidate();
            ArrayList<ArrayList<Integer>> gesorteerdeLijst = nnReversed.berekenRoute(panel.getLocaties());
            panel.setRoute(gesorteerdeLijst);
            panel.repaintPanel();
        }
        if (actionEvent.getSource() == btnUnwindNN){
            NearestNeighbourUntangle unwindThis = new NearestNeighbourUntangle();
            this.revalidate();
            ArrayList<ArrayList<Integer>> gesorteerdeLijst = unwindThis.berekenRoute(panel.getLocaties());
            panel.setRoute(gesorteerdeLijst);
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

