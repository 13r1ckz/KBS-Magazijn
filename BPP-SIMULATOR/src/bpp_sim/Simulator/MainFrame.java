package bpp_sim.Simulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener{

    private final JMenuBar menu = new JMenuBar();
    JMenu settings = new JMenu("Opties...");
    JMenuItem menu_single, menu_multi, menu_quit;
    
    private final GridBagConstraints c = new GridBagConstraints();
    private final int compHeight = 100;
    private final JButton generate = new JButton("Bereken"),
                          multiple = new JButton("Bereken meerdere");
    private final JTextField jtext = new JTextField(5),
                             jamount = new JTextField("0",5);
    private final ArrayList<SimulatorPanel> panelen = new ArrayList<>();
    
    private int currentFrame = 0;
    
    /* Constructor */
    public MainFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BBP Simulator");
        setResizable(false);
        
        menu_single = new JMenuItem("Bereken één");
        menu_single.addActionListener(this);
        settings.add(menu_single);
        
        menu_multi = new JMenuItem("Bereken meerdere");
        menu_multi.addActionListener(this);
        settings.add(menu_multi);
        
        settings.addSeparator();
        
        menu_quit = new JMenuItem("Exit");
        menu_quit.addActionListener(this);
        settings.add(menu_quit);
        
        menu.add(settings);
        
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        layout.setConstraints(this, c);
        this.setJMenuBar(menu);
        this.setComponents();
        this.pack();
    }
    
    /* Set the components in the frame */
    public void setComponents()
    {
        /* Top row */
        this.addComp(new JLabel("PRODUCTS",SwingConstants.CENTER),100,30,0,0);
        this.addComp(jtext,60,30,1,0);
        this.addComp(new JLabel("TIMES",SwingConstants.CENTER),100,30,2,0,1,1);
        this.addComp(jamount,60,30,3,0,1,1);
        this.addComp(generate,180,30,4,0);
        jamount.setEnabled(false);
        
        /* Add the panels */
        for(int i = 0; i < 4; i++){
            SimulatorPanel panel = new SimulatorPanel(i);
            panelen.add(panel);
            this.addComp(panel,500,compHeight,0,i+1,5,1);
        }
        
        /* Add the ActionListeners */
        generate.addActionListener(this);
        multiple.addActionListener(this);
    }
    
    /**
     * Sets the component. Easier to modify. Overloads with 1 width and 1 height.
     * @author Jasper Fritse
     * @param cp
     * @param xsize
     * @param ysize
     * @param xpos
     * @param ypos
     * @see addComponent()
     */
    private void addComp(Component cp, int xsize, int ysize, int xpos, int ypos)
    {
        this.addComp(cp,xsize,ysize,xpos,ypos,1,1);
    }
    
    /**
     * Sets the component. Easier to modify.
     * @author Jasper Fritse
     * @param cp
     * @param xsize
     * @param ysize
     * @param xpos
     * @param ypos
     * @param xwidth
     * @param ywidth
     */
    private void addComp(Component cp, int xsize, int ysize, int xpos, int ypos, int gridwidth, int gridheight)
    {
        c.gridx = xpos;
        c.gridy = ypos;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        cp.setPreferredSize(new Dimension(xsize,ysize));
        add(cp, c);
    }   
    
    /* Static void main */
    public static void main(String[] args)
    {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
    }
    
    /* Action Events */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        /* If generated, create a random list. This list should be thrown to all of the panels. */
        if(e.getSource() == generate){
            try{
                int amount = Integer.parseInt(jtext.getText());
                
                /* If the amount is above 1000, show a warning */
                if(amount >= 1000){
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Weet u zeker dat u met " + amount + " producten de tests wilt uitvoeren?","Waarschuwing",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.NO_OPTION){
                        return;
                    }
                }
                
                int times = Integer.parseInt(jamount.getText());
                        
                /* If the amount is above 100, show a warning */
                if(times >= 100){
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Weet u zeker dat u " + times + " keer de tests wilt uitvoeren?","Waarschuwing",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.NO_OPTION){
                        return;
                    }
                }
                        
                OrderLijst o = new OrderLijst(amount,10);
                for(SimulatorPanel p: panelen){
                    if(currentFrame == 0){
                        p.calculateSingle(o.getProducten());
                    }
                    else{
                        p.calculateMulti(amount,times);
                    }
                }
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }
        /* Menu items */
        if(e.getSource() == menu_quit){
            System.exit(0);
        }
        if(e.getSource() == menu_single){
            for(SimulatorPanel p: panelen){
                p.setFrameTo(0);
                currentFrame = 0;
                jamount.setEnabled(false);
            }
        }
        if(e.getSource() == menu_multi){
            for(SimulatorPanel p: panelen){
                p.setFrameTo(1);
                currentFrame = 1;
                jamount.setEnabled(true);
            }
        }
    }
}
