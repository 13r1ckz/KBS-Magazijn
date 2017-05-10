package bpp_sim;

import java.util.ArrayList;

public class FirstFullNFD implements BPP{

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
        
        //Switch each two items.
        try{
            for(int j = 0; j < producten.size()-1 ; j += 2){      
                Product ACC = producten.get(j);
                producten.set(j, producten.get(j+1));
                producten.set(j+1, ACC);
            }
        }
        catch(ArrayIndexOutOfBoundsException ns){}
        
        //Switch each three items.
        try{
            for(int j = 0; j < producten.size()-2 ; j += 3){      
                Product ACC = producten.get(j);
                producten.set(j, producten.get(j+2));
                producten.set(j+2, ACC);
            }
        }
        catch(ArrayIndexOutOfBoundsException ns){}
        
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
