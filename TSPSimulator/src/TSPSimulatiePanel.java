import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TSPSimulatiePanel extends JPanel {
    private int celX,celY;
    private int xcoordinate,ycoordinate;
    private TSPSimulatieFrame frame;
    public TSPSimulatiePanel(TSPSimulatieFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(600, 600));
    }
    @Override
    protected void paintComponent(Graphics g) {
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

            int xRand = rand.nextInt(gridGetal);
            int yRand = rand.nextInt(gridGetal);
            if(xRand == 0) {
                xRand++;
            }
            if(yRand == 0) {
                yRand++;
            }
            //check if location already was set

            celX = this.getWidth() / gridGetal;
            celY = this.getHeight() / gridGetal;

            for (int i =0 ;i<gridGetal; i++) {
                g.setColor(Color.BLACK);
                g.drawLine(0, celX * i, (gridGetal-1) * celY,  celY * i);
                g.drawLine(celY * i, 0 , celX * i, (gridGetal-1) * celX);
            }

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

            g.setColor(Color.RED);
            g.fillRect((xRand - 1) * celX,(yRand -1)* celX, celX,celX);
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(z + "",(xRand - 1) * celX + (celX / 2),(yRand -1)* celX + (celX / 2));
        }
    }

//    public int getAantalLocaties() {
//        return
//    }

}
