import java.awt.*;
import java.util.ArrayList;

class TekenPanel extends Canvas {
    int width, height, rows, columns;
    private int artCount;
    private int YW;
    private int XH;
    private ArrayList<ArrayList<Integer>> aList;

    TekenPanel(int w, int h, int r, int c, ArrayList<ArrayList<Integer>> aL) {
        setPreferredSize(new Dimension(w,h));

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

        for (x = 0; x < artCount ; x++) {
            g.setColor(Color.pink);
            g.fillRect((aList.get(x).get(1) * XH) - XH, 560 - ((aList.get(x).get(2) * YW) - YW), 180, 140);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(aList.get(x).get(0)), ((aList.get(x).get(1) * XH) - XH) + 85, 635 - ((aList.get(x).get(2) * YW) - YW));
            g.setColor(Color.black);
            g.drawRect((aList.get(x).get(1) * XH) - XH, 560 - ((aList.get(x).get(2) * YW) - YW), 180, 140);
            System.out.println(aList.get(x));
            System.out.println(aList.get(x).get(1));
            System.out.println(aList.get(x).get(2));
            System.out.println(x + ":X " + (aList.get(x).get(1) * XH));
            System.out.println(x + ":Y " + (701 - (aList.get(x).get(2) * YW)));
            //System.out.println((aList.get(x).get(2) * YW));
            //System.out.println(XH * YW);
        }
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

}
