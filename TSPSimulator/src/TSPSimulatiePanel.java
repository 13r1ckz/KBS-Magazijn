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

    public TSPSimulatiePanel(TSPSimulatieFrame frame, ArrayList<ArrayList<Integer>> locatiesOngesorteerd) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(600, 600));
        this.locatiesOngesorteerd = locatiesOngesorteerd;
    }

    protected void repaintPanel() {
        System.out.println("oeirheoir sf");
        repaint();
    }

    public void setLocaties(ArrayList<ArrayList<Integer>> locaties) {
        this.locaties = locaties;
    }

    public ArrayList<ArrayList<Integer>> getLocaties() {
        return locaties;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        this.g = graphics;

        super.paintComponent(g);
        int aantalLocaties;
        if(frame.getAantalLocaties() <= 3) {
            aantalLocaties = 3;
        }
        else {
            aantalLocaties = frame.getAantalLocaties();
        }
        int [][] locatieLijst = new int[aantalLocaties][1];
        for(int z = 0; z <= aantalLocaties; z++) {
            int gridGetal = 10;
            Random rand = new Random();
        gridGetal = 5;
        celX = this.getWidth() / gridGetal;
        celY = this.getHeight() / gridGetal;


        g.setColor(Color.BLACK);
        for (int i = 0; i < gridGetal; i++) {
            g.drawLine(0, celX * i, (gridGetal) * celY, celY * i);
            g.drawLine(celY * i, 0, celX * i, (gridGetal) * celX);

        }
        g.drawRect(0, 0, (celX * gridGetal) - 1, (celX * gridGetal) - 1);

            for(int x = 0; x < gridGetal; x++) {
                if(x == xRand) {
                    //System.out.println(z + "ste product, x positie " + xRand);
                    xcoordinate = xRand;
                }
            }
            for(int y = 0; y < gridGetal; y++) {
                if (y == yRand) {
                    //System.out.println(z + "ste product, y positie " + yRand);
                    ycoordinate = yRand;
                }
            }
        //tekent lijnen
        //System.out.println(locaties + " hier worden locaties gesout");
        for (int z = 0; z <= this.locaties.size() - 1; z++) {
            Random r = new Random();
            int xRand = locaties.get(z).get(0);
            int yRand = locaties.get(z).get(1);


            g.setColor(Color.RED);
            g.fillRect((xRand) * celX, (yRand) * celX, celX, celX);

            g.setColor(Color.CYAN);
            g.drawLine(celX / 2, celX / 2, (xRand * celX) + celX / 2, (yRand * celX) + celX / 2);


            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(z + "", (xRand) * celX + (celX / 2), (yRand) * celX + (celX / 2));
        }
    }
}
