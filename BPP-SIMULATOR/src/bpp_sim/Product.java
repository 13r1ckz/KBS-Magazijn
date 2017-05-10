package bpp_sim;

public class Product {

    private final int xpos;
    private final int ypos;
    private final int size;
    
    public Product(int xpos, int ypos, int size)
    {
        this.xpos = xpos;
        this.ypos = ypos;
        this.size = size;
    }
    
    public int getSize()
    {
        return size;
    }
    
}
