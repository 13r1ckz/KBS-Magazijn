package bpp_sim.Simulator;

import bpp_sim.Doos;
import bpp_sim.Functions.*;
import bpp_sim.Product;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;

public class SimulatorPanel extends JPanel{
    
    private final JLabel jName,
                         jBoxAmount;
    private final JPanel DoosPaneel = new DoosPaneel();
    private final int function;
    private final ImagePanel imgpanel;
    
    public SimulatorPanel(int enumeration)
    {
        // The panels are 500 x 100.
        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        layout.setHgap(0);
        layout.setVgap(2);
        setPreferredSize(new Dimension(500,100));
        
        function = enumeration;
        
        /* Create the label */
        String labelName;
        switch(function){
            case 0: labelName = EnumFunction.FIRST_FIT.getLabelName(); break;
            case 1: labelName = EnumFunction.FIRST_FIT_DECREASING.getLabelName(); break;
            case 2: labelName = EnumFunction.NEXT_FIT.getLabelName(); break;
            case 3: labelName = EnumFunction.NEXT_FIT_DECREASING.getLabelName(); break;
            default: labelName = "";
        }
        jName = new JLabel(labelName,SwingConstants.CENTER);
        jName.setPreferredSize(new Dimension(100,100));
        add(jName);
        
        /* Create the image panel */
        imgpanel = new ImagePanel();
        imgpanel.setPreferredSize(new Dimension(300,100));
        add(imgpanel);
        
        /* Create the other label. */
        String label2Name = ("<html><h3 style=\"text-align:center\">Dozen: 0</h3></html>");
        jBoxAmount = new JLabel(label2Name,SwingConstants.CENTER);
        add(jBoxAmount);
    }
    
    public void calculate(ArrayList<Product> producten)
    {
        ArrayList<Doos> dozen = new ArrayList<>();
        BPP func = null;
        switch(function){
            case 0: func = new FirstFit(); break;
            case 1: func = new FirstFitDecreasing(); break;
            case 2: func = new NextFit(); break;
            case 3: func = new FirstFullNFD(); break;
        }
        try{
            dozen = func.berekenOplossing(producten);
            String labelName = Integer.toString(func.berekenOplossing(producten).size());
            jBoxAmount.setText("<html><h3 style=\"text-align:center\">Dozen: " + labelName + "</h3></html>");
        }
        catch(NullPointerException nx){}
        imgpanel.drawDozen(dozen);
    }
}
