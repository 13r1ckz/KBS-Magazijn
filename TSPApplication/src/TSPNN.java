import java.util.ArrayList;

public class TSPNN implements TSPAlgoritme {
    private int i;
    private int artikelnr;
    private int arrayListSize;
    private ArrayList<ArrayList<Integer>> gesorteerd = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> sortInner = new ArrayList<Integer>();
    public TSPNN(ArrayList<ArrayList<Integer>> ongesorteerd) {
        arrayListSize = ongesorteerd.size();
        //bereken verschil tussen de vorige en de huidige afstand in de loop, als verschil kleiner is overschrijft hij de kortste afstand.
        for (int x = 0; x < arrayListSize; x++) {
            int afstand = 100;
            int kortste = 0;
            //We gaan hier langs al onze producten en onthouden degene met de kortste afstand.
            int currentX = 1;
            int currentY = 0;
            for (i = 0; i < ongesorteerd.size(); i++) {

                if (afstand > DifCalc(currentX, ongesorteerd.get(i).get(1)) + DifCalc(currentY, ongesorteerd.get(i).get(2))) {
                    afstand = DifCalc(currentX, ongesorteerd.get(i).get(1)) + DifCalc(currentY, ongesorteerd.get(i).get(2));
                    kortste = i;
                    artikelnr = ongesorteerd.get(i).get(0);

                }
            }
            //pak x, y en de afstand van het artikel met de korste afstand berekent in bovenstaande loop.
            int kortsteX = ongesorteerd.get(kortste).get(1);
            int kortsteY = ongesorteerd.get(kortste).get(2);
            currentX = kortsteX;
            currentY = kortsteY;
            sortInner.add(currentX);
            sortInner.add(currentY);
            gesorteerd.add(sortInner);
            sortInner = new ArrayList<Integer>();

            ongesorteerd.remove(kortste);



        }
    }
    public int DifCalc(int x, int y) {
        if (x != y) {
            return Math.max(x, y) - Math.min(x, y);
        } else {
            return 0;
        }
    }
    @Override
    public ArrayList<ArrayList<Integer>> berekenRoute() {
        return gesorteerd;
    }

    public ArrayList<ArrayList<Integer>> getGesorteerd() {
        return gesorteerd;
    }

    public void setGesorteerd(ArrayList<ArrayList<Integer>> gesorteerd) {
        this.gesorteerd = gesorteerd;
    }

}