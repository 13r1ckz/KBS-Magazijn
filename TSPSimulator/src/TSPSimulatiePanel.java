import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TSPSimulatiePanel extends JPanel{
    private int celX,celY;
    private int xcoordinate,ycoordinate;

    public TSPSimulatiePanel() {
        this.setPreferredSize(new Dimension(600, 600));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int aantalLocaties = 6;
        //loop door aantal locaties
        /**
         * SORTEER HIER PER ALGORITME DE VOLGORDE
         *
         */
        for(int z = 0; z <= 6; z++) {
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
                    System.out.println(z + "ste product, x positie " + xRand);
                    xcoordinate = xRand;
                }
            }
            for(int y = 0; y < gridGetal; y++) {
                if (y == yRand) {
                    System.out.println(z + "ste product, y positie " + yRand);
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
}
