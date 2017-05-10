package bpp_sim;

import java.util.ArrayList;

public class FirstFitDecreasing implements BPP{

    @Override
    public ArrayList<Doos> berekenOplossing(ArrayList<Product> producten) {
        
        /**
         * @author Jasper Fritse
         * -- ARRAYLIST SORTING PROGRAM --
         */
        
        for(int j = 0; j < producten.size()-1 ; j++){      
            for(int i = 0; i < producten.size()-1 ; i++){
                if(producten.get(i).getSize()< producten.get(i+1).getSize()){
                    Product ACC = producten.get(i);
                    producten.set(i, producten.get(i+1));
                    producten.set(i+1, ACC);
                }
            }
        }
        
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
