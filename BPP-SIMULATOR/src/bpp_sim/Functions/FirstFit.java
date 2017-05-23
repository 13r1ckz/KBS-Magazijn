package bpp_sim.Functions;

import bpp_sim.Doos;
import bpp_sim.Product;
import java.util.ArrayList;

public class FirstFit implements BPP{

    @Override
    public ArrayList<Doos> berekenOplossing(ArrayList<Product> producten)
    {
        
        ArrayList<Doos> dozen = new ArrayList<>();
        //Voor elk product...
        for(Product p: producten){
            boolean isPlaced = false;
            //Controleer elke doos of er plek is.
            for(int i = 0; i < dozen.size(); i++){
                if(!isPlaced && dozen.get(i).getFreeSpace() >= p.getSize()){
                    dozen.get(i).addProduct(p);
                    isPlaced = true;
                }
            }
            //Maak een nieuwe doos aan als er geen enkele doos was
            //die nog plek had.
            if(isPlaced == false){
                Doos d = new Doos();
                d.addProduct(p);
                dozen.add(d);
            }
        }
        
        return dozen;
    }
}
