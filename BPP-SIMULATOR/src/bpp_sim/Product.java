package bpp_sim;

public class Product {

    /* Product ID */
    private static int global_product_id = 0;
    private final int product_id;
    /* Positie in magazijn */
    private final int xpos;
    private final int ypos;
    /* Product size */
    private final int size;
    private int direction;
    
    /* constructor */
    public Product(int xpos, int ypos, int size)
    {
        product_id = global_product_id;
        global_product_id++;
        this.xpos = xpos;
        this.ypos = ypos;
        this.size = size;
    }
    
    /* get the product id */
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
