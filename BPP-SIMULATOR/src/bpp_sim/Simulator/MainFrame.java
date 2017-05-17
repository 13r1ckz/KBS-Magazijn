package bpp_sim.Simulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener{

    private final GridBagConstraints c = new GridBagConstraints();
    private final int compHeight = 100;
    private final JButton generate = new JButton("Bereken"),
                          multiple = new JButton("Bereken meerdere");
    private final JTextField jtext = new JTextField(5);
    private final ArrayList<SimulatorPanel> panelen = new ArrayList<>();
    
    /* Constructor */
    public MainFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BBP Simulator");
        setResizable(false);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        layout.setConstraints(this, c);
        this.setComponents();
        this.pack();
    }
    
    /* Set the components in the frame */
    public void setComponents()
    {
        /* Top row */
        this.addComp(new JLabel("PRODUCTS",SwingConstants.CENTER),100,30,0,0);
        this.addComp(jtext,60,30,1,0);
        this.addComp(generate,100,30,2,0);
        this.addComp(multiple,240,30,3,0,2,1);
        
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
    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
    }
    
    /* Action Events */
    @Override
    public void actionPerformed(ActionEvent e) {
        /* If generated, create a random list. This list should be thrown to all of the panels. */
        if(e.getSource() == generate){
            try{
                int amount = Integer.parseInt(jtext.getText());
                OrderLijst o = new OrderLijst(amount,10);
                for(SimulatorPanel p: panelen){
                    p.calculate(o.getProducten());
                }
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }
    }
}
