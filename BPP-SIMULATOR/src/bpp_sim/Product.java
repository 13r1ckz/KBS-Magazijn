package bpp_sim;

public class Product {

    private static int global_product_id = 0;
    private final int product_id;
    private final int xpos;
    private final int ypos;
    private final int size;
    private int direction;
    
    public Product(int xpos, int ypos, int size)
    {
        product_id = global_product_id;
        global_product_id++;
        this.xpos = xpos;
        this.ypos = ypos;
        this.size = size;
    }
    
    public int getProductID()
    {
        return product_id;
    }
    
    public int getSize()
    {
        return size;
    }
    
    /* Used for robot control */
    public void setDirection(int dir)
    {
        this.direction = dir;
    }
    
    public int getDirection()
    {
        return this.direction;
    }
    
    @Override
    public String toString()
    {
        return("Product " + this.product_id + " with length " + this.size);
    }
}
