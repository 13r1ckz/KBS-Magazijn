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
        /* Voor elk product */
        for(Product d: producten){
            
            /* Als het in een doos past, gooi het in die doos. */
            if(dozen.get(i).getFreeSpace() >= d.getSize()){
                dozen.get(i).addProduct(d);
            }
            
            /* Maak anders een nieuwe doos aan en gooi daar het product in. */
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
