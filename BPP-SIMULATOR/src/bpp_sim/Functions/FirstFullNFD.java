package bpp_sim.Functions;

import bpp_sim.Doos;
import bpp_sim.Product;
import java.util.ArrayList;

/*
    Onze eigen bedachte functie - FullNFD
    Ofwel - Even/uneven-functie
*/
public class FirstFullNFD implements BPP{

    @Override
    public ArrayList<Doos> berekenOplossing(ArrayList<Product> producten) {
        
        /* Voor elk product, gooi het in een EVEN of ONEVEN-lijst. */
        ArrayList<Product> EVEN = new ArrayList<>();
        ArrayList<Product> ODD = new ArrayList<>();
        for(Product p: producten){
            if(p.getSize() % 2 == 0){
                EVEN.add(p);
            }
            else{
                ODD.add(p);
            }
        }
        
        /*
            Gooi het in een nieuwe lijst:
            EVEN-ONEVEN-EVEN-ONEVEN-EVEN-ONEVEN-EVEN-ONEVEN-EVEN-ONEVEN-EVEN-ONEVEN-EVEN-ONEVEN-EVEN.
        */
        int j = producten.size();
        producten.clear();
        for(int i = 0; i < j; i++){
            try{producten.add(EVEN.get(i));}catch(Exception ex){}
            try{producten.add(ODD.get(i));}catch(Exception ex){}
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
