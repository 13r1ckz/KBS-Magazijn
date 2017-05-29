package bpp_sim.Simulator;

import bpp_sim.Doos;
import bpp_sim.Product;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

class ImagePanel extends JPanel{

    private final ArrayList<Doos> dozen = new ArrayList<>();
    private final Random randColor = new Random();
    
    /* Teken-functie */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        /* Teken de dozen */
        int xpos = 0;
        for(Doos d: dozen){
            int ypos = 0;
            for(Product p: d.getProducten()){
                //Willekeurige kleur
                g.setColor(new Color(randColor.nextInt(255),randColor.nextInt(255),randColor.nextInt(255)));
                g.fillRect(xpos, ypos*10, 15, p.getSize()*10);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(p.getSize()), xpos, ypos*10+10);
                ypos += p.getSize();
            }
            xpos += 16;
        }
        g.setColor(Color.BLACK);
    }
    
    public void drawDozen(ArrayList<Doos> all)
    {
        dozen.clear();
        dozen.addAll(all);
        repaint();
    }
    
}
