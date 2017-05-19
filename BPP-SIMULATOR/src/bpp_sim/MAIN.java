package bpp_sim;

import bpp_sim.Functions.FirstFitDecreasing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MAIN {
    
    public static void main(String[] args) throws IOException {
        /* Create the list */
        ArrayList<Product> producten = new ArrayList<>();
        Random rand = new Random();
        /* Add a few products */
        for(int i = 0; i < 3; i++){
            int rx = rand.nextInt();
            int ry = rand.nextInt();
            int rsize = rand.nextInt(3)+3;
            producten.add(new Product(rx,ry,rsize));   
        }
        
        for(Product p: producten){
            System.out.println("Product " + p.getProductID() + " : " + p.getSize());
        }
        
        /* Set the functions */
        Direction dir = new Direction();
        /* Create the function, and run it over the list */
        FirstFitDecreasing FFD = new FirstFitDecreasing();
        ArrayList<Doos> dozen = FFD.berekenOplossing(producten);
        /* Set the directions */
        dir.setDir(dozen);
        
        
        /* Print everything */
        for(Doos d: dozen){
            System.out.println("Doos___________________ (grootte " + d.getLength() + ")");
            for(Product p:  d.getProducten()){
                System.out.println("Product " + p.getProductID() + " grootte: " + p.getSize());
            }
            System.out.println();
        }
        /* Re-sort everything [Should be backwards-sorted] */
        producten = dir.sortList(producten);
        
        for(Product p: producten){
            System.out.println("Product " + p.getProductID() + " : " + p.getDirection());
        }
        
        /* Set the data to an integer */
        int[] a = new int[producten.size()];
        for(int i = 0; i < producten.size(); i++){
            a[i] = producten.get(i).getDirection();
        }
        
        /* Shove it in a controller and let it run */
        ArduinoController ac = new ArduinoController(a,producten,dozen);
        ac.start();
    }
}
