package bpp_sim;

import java.util.ArrayList;
import java.util.Random;

public class MAIN {

    public static void main(String[] args) {
        ArrayList<Product> producten = new ArrayList<>();
        Random rand = new Random();
        
        for(int i = 0; i < 50; i++){
            int rx = rand.nextInt();
            int ry = rand.nextInt();
            int rsize = rand.nextInt(14)+1;
            producten.add(new Product(rx,ry,rsize));   
        }
        
        System.out.println("FIRST FIT###########################################");
        FirstFit FF0 = new FirstFit();
        ArrayList<Doos> dozen = FF0.berekenOplossing(producten);
        for(Doos d: dozen){
            System.out.println();
            System.out.println("Box size: " + d.getLength());
            System.out.println("_____________");
            ArrayList<Product> product = d.getProducten();
            for(Product p: product){
                System.out.println(p.getSize());
            }
        }
        System.out.println("SIZE: " + dozen.size());
        
        System.out.println("FIRST FIT DECREASING################################");
        FirstFitDecreasing FFD = new FirstFitDecreasing();
        ArrayList<Doos> dozen0 = FFD.berekenOplossing(producten);
        for(Doos d: dozen0){
            System.out.println();
            System.out.println("Box size: " + d.getLength());
            System.out.println("_____________");
            ArrayList<Product> product = d.getProducten();
            for(Product p: product){
                System.out.println(p.getSize());
            }
        }
        System.out.println("SIZE: " + dozen0.size());
        
        System.out.println("NEXT FIT############################################");
        NextFit NFT = new NextFit();
        ArrayList<Doos> dozen1 = NFT.berekenOplossing(producten);
        for(Doos d: dozen1){
            System.out.println();
            System.out.println("Box size: " + d.getLength());
            System.out.println("_____________");
            ArrayList<Product> product = d.getProducten();
            for(Product p: product){
                System.out.println(p.getSize());
            }
        }
        System.out.println("SIZE: " + dozen1.size());
    }
}
