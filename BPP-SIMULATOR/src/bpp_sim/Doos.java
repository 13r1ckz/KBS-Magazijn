package bpp_sim;

import java.util.ArrayList;

public class Doos {

    /**
     * Globale variabelen.
     * length is de lengte van de doos.
     * Global_direction en direction zorgen voor de richting waarin de producten van de doos zich bewegen.
     * ArrayList<Producten> is de lijst van producten.
     */
    private static final int length = 10;
    private static int global_direction = 1;
    private final int direction;
    private final ArrayList<Product> producten;
    
    /* Constructor */
    public Doos()
    {
        producten = new ArrayList<>();
        /* zet de richting */
        direction = global_direction;
        global_direction++;
    }
    
    /* Get the direction */
    public int getDirection()
    {
        return direction;
    }
    
    /* Get the length of the box */
    public int getLength()
    {
        return length;
    }
    
    /* Get the free space of the box */
    public int getFreeSpace()
    {
        int i = length;
        for(Product p : producten){
            i -= p.getSize();
        }
        return i;
    }
    
    /* Get the product list */
    public ArrayList<Product> getProducten()
    {
        return producten;
    }
    
    /* Add a product */
    public void addProduct(Product p)
    {
        producten.add(p);
    }
    
}
