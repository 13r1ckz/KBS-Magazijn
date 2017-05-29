package bpp_sim.Functions;

import bpp_sim.Doos;
import bpp_sim.Product;
import java.util.ArrayList;

/*
    BPP-Interface.
*/

public interface BPP {
    
    // Het enige wat nodig is, is berekenOplossing;
    // Het krijgt een ArrayList van producten mee,
    // sorteert ze, en geeft een ArrayList<Doos> terug.
    public ArrayList<Doos> berekenOplossing(ArrayList<Product> producten);
    
}
