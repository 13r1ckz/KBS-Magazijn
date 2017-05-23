import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class TSPSimulatieFrame extends JFrame implements ActionListener {
    private JFrame frame;
    private String title;
    private int width, height;
    private JButton btnBerekenRoute, btnNearestNeighbour, btnNNReversed, btnUnwindNN, btnBruteForce;
    private JTextField jTextFieldAantalLocaties, jTextFieldAantalTesten;
    private int aantalLocaties;
    private ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
    private ArrayList<Integer> inner = new ArrayList<>();
    private TSPSimulatiePanel panel;
    private BruteForce bruteForce;
    private NearestNeighbourRework nn;
    private NearestNeighbourReversedRewrk nnr;



    public TSPSimulatieFrame(String title, int width,int height , BruteForce bruteForce , NearestNeighbourRework nn, NearestNeighbourReversedRewrk nnr) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.nn = nn;
        this.bruteForce = bruteForce;
        this.nnr = nnr;
    }

    public ArrayList<ArrayList<Integer>> GenerateRoute(int aantal) {
        if (aantal < 3) {
            aantal = 3;
        }
        

        Random r = new Random();
        for (int x = 0; x < aantal; x++) {
            int randX = r.nextInt(30);
            int randY = r.nextInt(30);
            for (int i = 0; i < outer.size(); i++) {
                if ((outer.get(i).get(0) == randX && outer.get(i).get(1) == randY) || (randX== 0 && randY ==0 )  ) {
                    randX = r.nextInt(30);
                    randY = r.nextInt(30);
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
        btnNNReversed = new JButton("Reversed nearest neighbour");
        btnNNReversed.addActionListener(this);
        btnUnwindNN = new JButton("Unwind NN");
        btnBruteForce = new JButton("Brute force");
        JLabel jLabelAantalLocaties = new JLabel("Aantal locaties ");
        JLabel jLabelAantalTesten = new JLabel("Aantal testen ");
        jTextFieldAantalLocaties = new JTextField(10);
        jTextFieldAantalLocaties.addKeyListener(keyListener);
        jTextFieldAantalTesten = new JTextField(10);
        jTextFieldAantalTesten.addKeyListener(keyListener);
        panel = new TSPSimulatiePanel(this, locatiesOngesorteerd);
        panel.setLocaties(locatiesOngesorteerd);
        frame.add(panel);
        frame.add(jLabelAantalLocaties);
        frame.add(jTextFieldAantalLocaties);
        frame.add(jLabelAantalTesten);
        frame.add(jTextFieldAantalTesten);
        frame.add(btnBerekenRoute);
        frame.add(btnNearestNeighbour);
        frame.add(btnNNReversed);
        frame.add(btnUnwindNN);
        frame.add(btnBruteForce);
        btnBruteForce.addActionListener(this);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                panel.setLocaties(GenerateRoute(Integer.parseInt(jTextFieldAantalLocaties.getText())));
            }
            catch(Exception e){
                return;
            }
            panel.repaintPanel();
        }
        if (actionEvent.getSource() == btnBruteForce){
            bruteForce= new BruteForce();
            System.out.println(bruteForce.berekenRoute(panel.getLocaties())+ " Bruteforce Gesorteerde lijst ");
        }
        if (actionEvent.getSource() == btnNearestNeighbour){
            nn = new NearestNeighbourRework();
            System.out.println(nn.berekenRoute(panel.getLocaties()) + " NearestNeighbour Gesorteerde lijst");
        }
        if (actionEvent.getSource() == btnNNReversed){
            nnr = new NearestNeighbourReversedRewrk();
            System.out.println(nnr.berekenRoute(panel.getLocaties()) + " NearestNeighbourReversed Gesorteerde lijst");
        }
    }

    private KeyListener keyListener = new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!((c >= '0') && (c <= '9') ||
                    (c == KeyEvent.VK_BACK_SPACE) ||
                    (c == KeyEvent.VK_DELETE))) {
                getToolkit().beep();
                e.consume();
            }
        }
    };
}

