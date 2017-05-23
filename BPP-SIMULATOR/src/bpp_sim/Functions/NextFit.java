package bpp_sim.Functions;

import bpp_sim.Doos;
import bpp_sim.Product;
import java.util.ArrayList;

public class NextFit implements BPP{

    @Override
    public ArrayList<Doos> berekenOplossing(ArrayList<Product> producten) {
        
        ArrayList<Doos> dozen = new ArrayList<>();
        
        dozen.add(new Doos());
        int i = 0;
        for(Product d: producten){
            
            if(dozen.get(i).getFreeSpace() >= d.getSize()){
                dozen.get(i).addProduct(d);
            }
            
            else{
                Doos D = new Doos();
                dozen.add(D);
                D.addProduct(d);
                i++;
            }
        } 
        return dozen;
    }
}
