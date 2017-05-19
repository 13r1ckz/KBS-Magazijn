package bpp_sim.Application;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePaneel extends JPanel{
    BufferedImage img;
    JLabel label;
    
    public ImagePaneel() throws IOException {
        this.img = ImageIO.read(new File("src/bpp_sim/Application/images/panel_WAIT_FOR_INPUT.png"));
        label = new JLabel(new ImageIcon(this.img));
        setPreferredSize(new Dimension(200,360));
        add(label);
    }
    
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
