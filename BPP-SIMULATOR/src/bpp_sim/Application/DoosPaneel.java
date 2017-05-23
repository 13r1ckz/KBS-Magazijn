package bpp_sim.Application;

import bpp_sim.Doos;
import bpp_sim.Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class DoosPaneel extends JPanel{
    
    private Doos doos;
    private int width = 100;
    private int height;
    private Random random_color = new Random();
    
    public DoosPaneel(){
        this(new Doos());
    }
    
    public DoosPaneel(Doos doos){
        this.doos = doos;
        height = doos.getLength()*25;
        setPreferredSize(new Dimension(width,height));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int ypos = 0;
        for(Product p: doos.getProducten()){
            int red = random_color.nextInt(255);
            int gre = random_color.nextInt(255);
            int blu = random_color.nextInt(255);
            g.setColor(new Color(red,gre,blu));
            g.fillRect(0, ypos, width, p.getSize()*25);
            g.setColor(Color.black);
            g.drawString(Integer.toString(p.getSize()), 0, p.getSize()*25+ypos);
            ypos += (p.getSize()*25);
        }
        //outline
        g.setColor(Color.black);
        g.drawRect(0,0,width-1,height-1);
        
    }
    
}
