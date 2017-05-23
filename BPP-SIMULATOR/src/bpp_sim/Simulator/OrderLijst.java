package bpp_sim.Simulator;

import bpp_sim.Product;
import java.util.ArrayList;
import java.util.Random;

public class OrderLijst {

    private final Random rand = new Random();
    private final ArrayList<Product> producten = new ArrayList<>();
    
    public OrderLijst(int amount,int maxsize){
        for(int i = 0; i < amount; i++){
            int rx = rand.nextInt();
            int ry = rand.nextInt();
            int rsize = rand.nextInt(maxsize)+1;
            producten.add(new Product(rx,ry,rsize));
        }
    }
    
    public ArrayList<Product> getProducten()
    {
        return producten;
    }
    
}
