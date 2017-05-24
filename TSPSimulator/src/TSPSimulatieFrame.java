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
    private JTextField jTF_multi_hoeveelheid, jTF_multi_locaties;
    private JLabel jamtNN, jamtNNR, jamtBF, jamtNNU;
    private JLabel jgemNN, jgemNNR, jgemBF, jgemNNU;
    private int aantalLocaties;
    private ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> locatiesOngesorteerd = new ArrayList<>();
    private ArrayList<Integer> inner = new ArrayList<>();
    private TSPSimulatiePanel panel;
    private boolean showFirstFrame = true;
    private double totaleAfstandNNReversed, totaleAfstandNN, totaleAfstandBruteForce, totaleAfstandUntangle;
    private double gemiddeldeNNReversed, gemiddeldeNN, gemiddeldeBruteForce, gemiddeldeUntangle;
    private GridBagConstraints c = new GridBagConstraints();

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
            /* Create buttons */
            btnBerekenRoute = new JButton("Bereken punten");
            btnNearestNeighbour = new JButton("Nearest neighbour");
            btnNNReversed = new JButton("Reversed nearest neighbour");
            btnUnwindNN = new JButton("Untangle NN");
            btnBruteForce = new JButton("Brute force");
            JLabel jLabelAantalLocaties = new JLabel("Aantal locaties ");
            JLabel jLabelGridGrootte = new JLabel("Gridgrootte");
            jTextFieldAantalLocaties = new JTextField(10);
            jTextFieldGridGrootte = new JTextField(10);
            panel = new TSPSimulatiePanel(this, locatiesOngesorteerd);
            /* Add ActionListeners */
            btnBerekenRoute.addActionListener(this);
            btnNearestNeighbour.addActionListener(this);
            btnNNReversed.addActionListener(this);
            btnUnwindNN.addActionListener(this);
            btnBruteForce.addActionListener(this);
            jTextFieldAantalLocaties.addKeyListener(keyListener);
            jTextFieldGridGrootte.addKeyListener(keyListener);
            /* Set locations */
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
            panel.setGridGetal(5);
            frame.setLayout(new FlowLayout());
            frame.setSize(width, height);
        }
        /* Second frame */
        else {
            GridBagLayout layout = new GridBagLayout();
            layout.setConstraints(this,c);
            frame.setLayout(layout);

            /* Create components */
            jTF_multi_hoeveelheid = new JTextField(5);
            jTF_multi_locaties = new JTextField(5);
            btnBerekenRouteSecondFrame = new JButton("Bereken");

            jamtNN = new JLabel();
            jamtNNR = new JLabel();
            jamtNNU = new JLabel();
            jamtBF = new JLabel();

            jgemNN = new JLabel();
            jgemNNR = new JLabel();
            jgemNNU = new JLabel();
            jgemBF = new JLabel();

            /* Add key lsiteners */
            jTF_multi_hoeveelheid.addKeyListener(keyListener);
            jTF_multi_locaties.addKeyListener(keyListener);
            btnBerekenRouteSecondFrame.addActionListener(this);

            /* Add components to frame */
            //Top row
            this.addComp(new JLabel("Aantal locaties",SwingConstants.CENTER),150,35,0,0,1,1);
            this.addComp(jTF_multi_locaties,150,35,1,0,1,1);
            this.addComp(new JLabel("Aantal testen",SwingConstants.CENTER),150,35,2,0,1,1);
            this.addComp(jTF_multi_hoeveelheid,150,35,3,0,1,1);
            this.addComp(btnBerekenRouteSecondFrame,150,35,4,0,1,1);
            //Bottom rows
            this.addComp(new JLabel("<html><h3 style=\"text-align:center\">Nearest Neighbor</h3></html>",SwingConstants.CENTER),150,50,0,1,1,1);
            this.addComp(jamtNN,300,50,1,1,2,1);
            this.addComp(jgemNN,300,50,3,1,2,1);

            this.addComp(new JLabel("<html><h3 style=\"text-align:center\">Nearest Neighbor Reversed</h3></html>",SwingConstants.CENTER),150,50,0,2,1,1);
            this.addComp(jamtNNR,300,50,1,2,2,1);
            this.addComp(jgemNNR,300,50,3,2,2,1);

            this.addComp(new JLabel("<html><h3 style=\"text-align:center\">Nearest Neighbor Untangled</h3></html>",SwingConstants.CENTER),150,50,0,3,1,1);
            this.addComp(jamtNNU,300,50,1,3,2,1);
            this.addComp(jgemNNU,300,50,3,3,2,1);

            this.addComp(new JLabel("<html><h3 style=\"text-align:center\">Brute Force</h3></html>",SwingConstants.CENTER),150,50,0,4,1,1);
            this.addComp(jamtBF,300,50,1,4,2,1);
            this.addComp(jgemBF,300,50,3,4,2,1);

            frame.revalidate();
            frame.pack();
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

            /* Store the amount of tests into an integer */
            int aantaltesten = 0;
            try {aantaltesten = Integer.parseInt(jTF_multi_hoeveelheid.getText());}
            catch(NumberFormatException e) {}

            /* Doe dit zo veel keer. */
            for(int x = 0; x <= aantaltesten; x++) {
                //Print waar hij is
                System.out.println(x);
                outer = new ArrayList<>();
                try {
                    int locaties = Integer.parseInt(jTF_multi_locaties.getText());
                    panel.setGridGetal(locaties);
                    panel.setLocaties(GenerateRoute(locaties));
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
            //Zet JLabels naar variabelen

            jamtNN.setText("Tot. afstand NN = " + Double.toString(totaleAfstandNN));
            jgemNN.setText("Gem. afstand NN = " + Double.toString(gemiddeldeNN));

            jamtNNR.setText("Tot. afstand NN Reversed = " + Double.toString(totaleAfstandNNReversed));
            jgemNNR.setText("Gem. afstand NN Reversed = " + Double.toString(gemiddeldeNNReversed));

            jamtNNU.setText("Tot. afstand NN Untangled = " + Double.toString(totaleAfstandUntangle));
            jgemNNU.setText("Gem. afstand NN Untangled = " + Double.toString(gemiddeldeUntangle));

            jamtBF.setText("Tot. afstand Brute Force = " + Double.toString(totaleAfstandBruteForce));
            jgemBF.setText("Gem. afstand Brute Force = " + Double.toString(gemiddeldeBruteForce));

            //Zet alles weer naar 0
            totaleAfstandNNReversed = 0;
            totaleAfstandNN = 0 ;
            totaleAfstandBruteForce = 0;
            totaleAfstandUntangle = 0;

            gemiddeldeNNReversed = 0;
            gemiddeldeNN = 0 ;
            gemiddeldeBruteForce = 0;
            gemiddeldeUntangle = 0;
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

    private void addComp(Component cp, int xsize, int ysize, int xpos, int ypos, int gridwidth, int gridheight)
    {
        c.gridx = xpos;
        c.gridy = ypos;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        cp.setPreferredSize(new Dimension(xsize,ysize));
        frame.add(cp, c);
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

