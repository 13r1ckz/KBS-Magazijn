package bpp_sim;

import java.util.ArrayList;

public class Direction {

    public void setDir(ArrayList<Doos> dozen)
    {
        for(Doos doos: dozen){
            for(Product p: doos.getProducten()){
                p.setDirection(doos.getDirection());
            }
        }
    }
    
    public ArrayList<Product> reverseList(ArrayList<Product> producten)
    {
        ArrayList<Product> AList = new ArrayList<>();
        
        for(int i = producten.size()-1; i >= 0; i--){
            AList.add(producten.get(i));
        }
        
        return AList;
    }
    
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
