public class Start {
    public static void main(String[] ag) {
//        TSPSimulatieFrame frame = new TSPSimulatieFrame(700, 1000);
//        frame.createDisplay();
        NextFitUnwind nn = new NextFitUnwind();
        System.out.println("gesorteerd - " + nn.generateRoute(5));
        System.out.println(nn.getTotalLength() + " = totale route nn");
        NearestNeighbourReversed nnreversed = new NearestNeighbourReversed();
        System.out.println("gesorteerd - " + nnreversed.generateRoute(5));
        System.out.println(nnreversed.getTotalLength() + " = totale route nn");
    }
}