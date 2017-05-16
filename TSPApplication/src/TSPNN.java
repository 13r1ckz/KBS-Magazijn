import java.util.ArrayList;

public class TSPNN implements TSPAlgoritme {
    private ArrayList<Integer> sOrder = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> gesorteerd;
    public TSPNN(ArrayList<ArrayList<Integer>> ongesorteerd) {
        int arrayListSize = ongesorteerd.size();
        int currentX = 0;
        int currentY = 1;
        int startX = 0;
        int startY = 1;
        //bereken verschil tussen de vorige en de huidige afstand in de loop, als verschil kleiner is overschrijft hij de kortste afstand.
        for (int x = 0; x < arrayListSize; x++) {
            int afstand = 100;
            int kortste = 0;

            for (int i = 0; i < ongesorteerd.size(); i++) {
                if (afstand > DifCalc(currentX, ongesorteerd.get(i).get(1)) + DifCalc(currentY, ongesorteerd.get(i).get(2))) {
                    //Door deze formule wordt er bepaald welk punt het dichtsbij de current location ligt.
                    afstand = DifCalc(currentX, ongesorteerd.get(i).get(1)) + DifCalc(currentY, ongesorteerd.get(i).get(2));
                    kortste = i;
                    int artikelnr = ongesorteerd.get(i).get(0);
                }
            }
            int kortsteX = ongesorteerd.get(kortste).get(1);
            int kortsteY = ongesorteerd.get(kortste).get(2);
            //als er 3 producten zijn opgehaald, reset naar start
            if (x % 3 == 0 && x != 0 ) {
                System.out.println(x+" item is opgehaald?");
                System.out.println(startX-currentX);
                System.out.println(startY-currentY);
                currentX = startX;
                currentY = startY;
            }
            //System.out.println("kortste afstand " + afstand + ", x van kortste " + kortsteX + ", y van kortste " + kortsteY );
            System.out.println("X naar:");
            //KortsteX - CurrentX en KortsteY - CurrentY zijn de formules om uit te rekenen waar de arduino relatief aan zen locatie
            //naartoe moet gaan.
            System.out.println(kortsteX - currentX);
            System.out.println("Y naar: ");
            System.out.println(kortsteY - currentY);
            currentX = kortsteX;
            currentY = kortsteY;
            ongesorteerd.remove(kortste);
            sOrder.add(kortsteX);
            sOrder.add(kortsteY);
            try {
                gesorteerd.add(sOrder);
                sOrder = new ArrayList<Integer>();
            }
            catch (Exception e) {
                System.out.println("X" + kortsteX);
                System.out.println("Y" +kortsteY);
                System.out.println("order" + sOrder);
                System.out.println("gesorteerd" + gesorteerd);
                System.out.println(e);
                sOrder = new ArrayList<Integer>();
            }

            //System.out.println(ongesorteerd);
        }
        currentX = startX - currentX;
        currentY = startY - currentY;
        System.out.println("X naar:");
        System.out.println(currentX);
        System.out.println("Y naar: ");
        System.out.println(currentY);
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
}
