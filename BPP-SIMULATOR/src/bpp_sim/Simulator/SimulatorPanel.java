package bpp_sim.Simulator;

import bpp_sim.Doos;
import bpp_sim.Functions.*;
import bpp_sim.Product;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.*;

public class SimulatorPanel extends JPanel{
    
    private JLabel  jName,
                    jBoxAmount,
                    jDifferential;
    private final int function;
    private ImagePanel imgpanel;
    private final String labelName;
    
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
        switch(function){
            case 0: labelName = EnumFunction.FIRST_FIT.getLabelName(); break;
            case 1: labelName = EnumFunction.FIRST_FIT_DECREASING.getLabelName(); break;
            case 2: labelName = EnumFunction.NEXT_FIT.getLabelName(); break;
            case 3: labelName = EnumFunction.NEXT_FIT_DECREASING.getLabelName(); break;
            default: labelName = "";
        }
        this.setFrameTo(0);
    }
    
    public void calculateSingle(ArrayList<Product> producten)
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

    public void calculateMulti(int aantalProducten,int amount)
    {
        double division = 00.00;
        double hoeveelheid = 00.00;
        for(int i = 0; i < amount; i++){
            OrderLijst o = new OrderLijst(aantalProducten,10);
            ArrayList<Doos> dozen = new ArrayList<>();
            BPP func = null;
            switch(function){
                case 0: func = new FirstFit(); break;
                case 1: func = new FirstFitDecreasing(); break;
                case 2: func = new NextFit(); break;
                case 3: func = new FirstFullNFD(); break;
            }
            /* Bereken de lengte en geef het resultaat terug */
            dozen = func.berekenOplossing(o.getProducten()); 
            hoeveelheid += (double) dozen.size();
            division += 1.00;
            jDifferential.setText("<html><h3 style=\"text-align:center\"> Gemiddelde: " + Double.toString(round(hoeveelheid / division,2)) + "</h3></html>");
            jBoxAmount.setText("<html><h3 style=\"text-align:center\"> Totale dozen: " + Double.toString(hoeveelheid) + "</h3></html>");
        }
    }
    
    /* Rounding method */
    private static double round(double value, int places)
    {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
    }
    
    void setFrameTo(int i)
    {
        /* Remove the components */
        this.removeAll();
            
        /* Single selection */
        if(i == 0){
            
            /* Create the first label */
            jName = new JLabel(labelName,SwingConstants.CENTER);
            jName.setPreferredSize(new Dimension(100,100));
            add(jName);
        
            /* Create the image panel */
            imgpanel = new ImagePanel();
            imgpanel.setPreferredSize(new Dimension(300,100));
            add(imgpanel);
            
            /* Create the amount label. */
            String label2Name = ("<html><h3 style=\"text-align:center\">Dozen: 0</h3></html>");
            jBoxAmount = new JLabel(label2Name,SwingConstants.CENTER);
            jBoxAmount.setPreferredSize(new Dimension(100,100));
            add(jBoxAmount);
            this.revalidate();
            this.repaint();
        }
        
        /* Multi selection */
        if(i == 1){
            /* Create the first label */
            jName = new JLabel(labelName,SwingConstants.CENTER);
            jName.setPreferredSize(new Dimension(100,100));
            add(jName);
            
            /* Create the differential label */
            jDifferential = new JLabel("<html><h3 style=\"text-align:center\"> Gemiddelde: 0</h3></html>",SwingConstants.CENTER);
            jDifferential.setPreferredSize(new Dimension(200,100));
            add(jDifferential);
            
            /* Create the amount label. */
            String label2Name = ("<html><h3 style=\"text-align:center\">Totale dozen: 0</h3></html>");
            jBoxAmount = new JLabel(label2Name,SwingConstants.CENTER);
            jBoxAmount.setPreferredSize(new Dimension(200,100));
            add(jBoxAmount);
            this.revalidate();
            this.repaint();
        }
    }
}
