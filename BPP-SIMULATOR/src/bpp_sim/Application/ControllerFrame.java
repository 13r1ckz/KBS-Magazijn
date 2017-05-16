package bpp_sim.Application;

import bpp_sim.Doos;
import bpp_sim.Product;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ControllerFrame extends JFrame{

    private ArrayList<Doos> dozen;
    private ArrayList<Product> producten;
    private ImagePaneel iPaneel = new ImagePaneel();
    
    
    public ControllerFrame(ArrayList<Doos> dozen,ArrayList<Product> producten) throws IOException{
        this.dozen = dozen;
        this.producten = producten;
        //2 panelen voor elke doos.
        DoosPaneel P_LEFT = new DoosPaneel(dozen.get(0));
        add(P_LEFT);
        add(iPaneel);
        /* Let wel op, dat als het in 1 doos past, de tweede niet meegegeven wordt. */
        try{
            DoosPaneel P_RIGHT = new DoosPaneel(dozen.get(1));
            add(P_RIGHT);
        }
        catch(IndexOutOfBoundsException | NullPointerException exc){
            DoosPaneel P_RIGHT = new DoosPaneel();
            add(P_RIGHT);
        }
        
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(400,250);
        this.pack();
    }
    
    public ImagePaneel getImage(){
        return iPaneel;
    }
}
