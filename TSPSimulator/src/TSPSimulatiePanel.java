import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class TSPSimulatiePanel extends JPanel {
    private int celX, celY;
    private ArrayList<ArrayList<Integer>> locatiesOngesorteerd;
    private ArrayList<ArrayList<Integer>> locaties;
    private TSPSimulatieFrame frame;
    private int gridGetal;
    private Graphics g;
    private ArrayList<ArrayList<Integer>> locatiesGesorteerd;

    public TSPSimulatiePanel(TSPSimulatieFrame frame, ArrayList<ArrayList<Integer>> locatiesOngesorteerd) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(600, 600));
        this.locatiesOngesorteerd = locatiesOngesorteerd;
    }

    protected void repaintPanel() {
        System.out.println("repainted");
        repaint();
    }
    public void setGridGetal(int aantal) {
        this.gridGetal = aantal;
    }

    public void setLocaties(ArrayList<ArrayList<Integer>> locaties) {
        this.locaties = locaties;
    }

    public ArrayList<ArrayList<Integer>> getLocaties() {
        return locaties;
    }

    public void setRoute(ArrayList<ArrayList<Integer>> locatiesGesorteerd) {
        this.locatiesGesorteerd = locatiesGesorteerd;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        this.g = graphics;
        super.paintComponent(g);
        int aantalLocaties = frame.getAantalLocaties();
        int[][] locatieLijst = new int[aantalLocaties][1];
        for (int z = 0; z <= aantalLocaties; z++) {
            celX = this.getWidth() / gridGetal;
            celY = this.getHeight() / gridGetal;

            g.setColor(Color.BLACK);
            for (int i = 0; i < gridGetal; i++) {
                g.drawLine(0, celX * i, (gridGetal) * celY, celY * i);
                g.drawLine(celY * i, 0, celX * i, (gridGetal) * celX);

            }
            g.drawRect(0, 0, (celX * gridGetal) - 1, (celX * gridGetal) - 1);

            //tekent lijnen
            for (int x = 0; x <= this.locaties.size() - 1; x++) {
                Random r = new Random();
                int xRand = locaties.get(x).get(0);
                int yRand = locaties.get(x).get(1);

                g.setColor(Color.RED);
                g.fillOval((xRand) * celX, (yRand) * celX, celX, celX);
            }
        }
        if(this.locatiesGesorteerd != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            int currentX = 0;
            int currentY = 0;
            for(int x = 0; x < this.locatiesGesorteerd.size(); x++) {
                if(x <= 1) {
                    g2.setColor(Color.GRAY);
                } else {
                    g2.setColor(Color.BLACK);
                }
                g2.drawLine((celX * currentX) + celX / 2, (celY * currentY) + celY / 2, (this.locatiesGesorteerd.get(x).get(0) * celX) + celX / 2, (this.locatiesGesorteerd.get(x).get(1) * celX) + celX / 2);
                currentX = this.locatiesGesorteerd.get(x).get(0);
                currentY = this.locatiesGesorteerd.get(x).get(1);
            }
        }
    }
}
