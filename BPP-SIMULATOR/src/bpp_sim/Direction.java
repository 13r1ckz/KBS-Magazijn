package bpp_sim;

import java.util.ArrayList;

/*
    Direction is een klasse om ArrayLists te sorteren.
    Dit is behulpzaam voor de MAIN-klasse.
*/

public class Direction {

    /* Zet de richting. */
    public void setDir(ArrayList<Doos> dozen)
    {
        for(Doos doos: dozen){
            for(Product p: doos.getProducten()){
                p.setDirection(doos.getDirection());
            }
        }
    }
    
    /* Keer de lijst om. */
    public ArrayList<Product> reverseList(ArrayList<Product> producten)
    {
        ArrayList<Product> AList = new ArrayList<>();
        
        for(int i = producten.size()-1; i >= 0; i--){
            AList.add(producten.get(i));
        }
        
        return AList;
    }
    
    /* Sorteer de lijst op grootte. */
    public ArrayList<Product> sortList(ArrayList<Product> producten)
    {
        ArrayList<Product> AList = new ArrayList<>();
        
        for(int j = 0; j < producten.size() ; j++){      
            for(int i = 0; i < producten.size()-1 ; i++){
                if(producten.get(i).getProductID()< producten.get(i+1).getProductID()){
                    Product ACC = producten.get(i);
                    producten.set(i, producten.get(i+1));
                    producten.set(i+1, ACC);
                }
            }
        }
        for(int j = 0; j < producten.size() ; j++){  
            AList.add(producten.get(j));
        }
        
        return AList;
    }
    
}
