package bpp_sim;

import bpp_sim.Functions.FirstFitDecreasing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MAIN {
    
    public static void main(String[] args) throws IOException {
        /* Create the list */
        ArrayList<Product> producten = new ArrayList<>();
        ArrayList<ArrayList<Product>> productListPerThree = new ArrayList<>();
        ArrayList<ArrayList<Product>> outputProductList = new ArrayList<>(); 
        ArrayList<ArrayList<Doos>> outputBoxList = new ArrayList<>(); 
        
        Random rand = new Random();
        /* Add a few products */
        for(int i = 0; i < 7; i++){
            int rx = rand.nextInt();
            int ry = rand.nextInt();
            int rsize = rand.nextInt(3)+3;
            producten.add(new Product(rx,ry,rsize));   
        }
        
        for(Product p: producten){
            System.out.println("Product " + p.getProductID() + " : " + p.getSize());
        }
        
        /* For each three products in the list, shove them into the Plist3. */
        int size = (int) Math.ceil( (double) producten.size() / 3);
        System.out.println(size);
        
        int position = 0;
        for(int i = 0; i < (size*3); i += 3){
            try{
                //Create arrayList
                productListPerThree.add(new ArrayList<>());
                //Add the products
                productListPerThree.get(position).add(producten.get(i));
                productListPerThree.get(position).add(producten.get(i+1));
                productListPerThree.get(position).add(producten.get(i+2));
            }
            catch(IndexOutOfBoundsException AIOOBEX){}
            position++;
        }
        
        /* Set the functions */
        FirstFitDecreasing FFD = new FirstFitDecreasing();
        
        /* For each product list, do stuff. */
        for(ArrayList<Product> inputList : productListPerThree){
            System.out.println("### " + Arrays.deepToString(inputList.toArray()) + " ###");
            Direction dir = new Direction();
            ArrayList<Doos> dozen = FFD.berekenOplossing(inputList);
            dir.setDir(dozen);
            outputBoxList.add(dozen);
            
            
            
            /* Sort producten */
            inputList = dir.sortList(inputList);
            
            outputProductList.add(inputList);
            
            
            System.out.println();
        }
        
        /* Print everything */
            for(ArrayList<Doos> list : outputBoxList){
                for(Doos d: list){
                    System.out.println("Doos: ");
                    ArrayList<Product> pro = d.getProducten();
                    System.out.println(Arrays.deepToString(pro.toArray()));
                }
                System.out.println("____________");
            }
            
        for(ArrayList<Product> p: outputProductList){
            System.out.println(">>> OUTPUT >>>");
            for(Product pd : p){
                System.out.println("Product " + pd.getProductID() + " : " + pd.getDirection());
            }
        }
        
        ArduinoController ac = new ArduinoController(outputBoxList,outputProductList);
        ac.start();
    }
}
