package bpp_sim;

import java.util.ArrayList;

public class Doos {

    private static final int length = 30;
    private final ArrayList<Product> producten;
    
    /* Constructor */
    public Doos()
    {
        producten = new ArrayList<>();
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
