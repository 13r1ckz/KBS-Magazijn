package bpp_sim.Application;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePaneel extends JPanel{
    
    /*
        ImagePaneel zorgt er voor dat de dynamische afbeelding geladen wordt.
    */
    
    BufferedImage img;
    JLabel label;
    
    /* Constructor */
    public ImagePaneel() throws IOException {
        this.img = ImageIO.read(new File("src/bpp_sim/Application/images/panel_WAIT_FOR_INPUT.png"));
        label = new JLabel(new ImageIcon(this.img));
        setPreferredSize(new Dimension(200,360));
        add(label);
    }
    
    /* Zet de afbeelding */
    public void setImage(String IMAGE_FILENAME)
    {
        try{
            this.img = ImageIO.read(new File("src/bpp_sim/Application/images/panel_" + IMAGE_FILENAME + ".png"));
        }
        catch(IOException NX){
            System.out.println("File was NOT found.");
        }
        label.setIcon(new ImageIcon(this.img));
    }
}
