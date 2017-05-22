import java.awt.*;
import java.util.ArrayList;

class TekenPanel extends Canvas {
    int width, height, rows, columns;
    private int artCount;
    private int YW;
    private int XH;
    private ArrayList<ArrayList<Integer>> aList;
    private ArrayList<ArrayList<Integer>> Route;
    Color back = new Color(0xEEEEEE);

    TekenPanel(int w, int h, int r, int c, ArrayList<ArrayList<Integer>> aL, ArrayList<ArrayList<Integer>> routL ) {
        setPreferredSize(new Dimension(w,h));
        setRoute(routL);
        setArtCount(aL.size());
        setaList(aL);
        setSize(width = w, height = h);
        rows = r;
        columns = c;
    }
    public void paint(Graphics g) {
        int k;
        int x;
        width = width;
        height = height;
        YW = 140;
        XH = 180;

        int htOfRow = height / (rows);
        for (k = 0; k <= rows; k++) {
            g.drawLine(0, k * htOfRow, width, k * htOfRow);
        }

        int wdOfRow = width / (columns);
        for (k = 0; k <= columns; k++) {
            g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
        }
        g.setColor(back);
        g.fillRect(0,0,180,560);
        g.setColor(Color.BLACK);
        g.drawString("BPP",85,635);

        for (x = 0; x < artCount ; x++) {
            g.setColor(Color.RED);
            g.fillRect(((aList.get(x).get(1) + 1) * XH) - XH, 560 - ((aList.get(x).get(2) * YW) - YW), 180, 140);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(aList.get(x).get(0)), (((aList.get(x).get(1) + 1) * XH) - XH) + 85, 635 - ((aList.get(x).get(2) * YW) - YW));
            g.setColor(Color.black);
            g.drawRect(((aList.get(x).get(1) + 1) * XH) - XH, 560 - ((aList.get(x).get(2) * YW) - YW), 180, 140);
            System.out.println(aList.get(x));
            System.out.println(aList.get(x).get(1));
            System.out.println(aList.get(x).get(2));
            System.out.println(x + ":X " + (aList.get(x).get(1) * XH));
            System.out.println(x + ":Y " + (701 - (aList.get(x).get(2) * YW)));
            //System.out.println((aList.get(x).get(2) * YW));
            //System.out.println(XH * YW);
        }
        g.setColor(Color.BLACK);
        System.out.println("rout in teken" + Route.get(0).get(0) + " " + Route.get(0).get(1));
        g.drawLine(90, 640,(((Route.get(0).get(0) + 1) * XH) - XH) + 85, 635 - ((Route.get(0).get(1) * YW) - YW));
    }

    public void setArtCount(int artCount) {
        this.artCount = artCount;
    }

    public void setaList(ArrayList<ArrayList<Integer>> aL) {
        this.aList = new ArrayList<>(aL);
    }

    public ArrayList<ArrayList<Integer>> getaList() {
        return aList;
    }

    public void setRoute(ArrayList<ArrayList<Integer>> route) {
        Route = route;
    }

}
