import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

class TekenPanel extends Canvas {
    int width, height, rows, columns;
    private int artCount;
    private int YW;
    private int XH;
    private ArrayList<ArrayList<Integer>> aList;
    private ArrayList<ArrayList<Integer>> Route;
    private ArrayList<ArrayList<Integer>> Route2;
    private ArduinoConnect status;
    private int p;
    private int l;


    Color back = new Color(0xEEEEEE);

    TekenPanel(int w, int h, int r, int c, ArrayList<ArrayList<Integer>> aL, ArrayList<ArrayList<Integer>> routL) {
        setPreferredSize(new Dimension(w, h));
        setRoute(routL);

        setArtCount(aL.size());
        setaList(aL);
        setSize(width = w, height = h);
        rows = r;
        columns = c;
        Route2 = new ArrayList<>();

    }

    public void shiftOut(int data) {
        switch (data) {

            //P
            case 112:
                if (p < Route2.size()) {
                    p++;
                }
                break;
            default:
                return;
        }
        this.repaint();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int k;
        int x;
        ArrayList<Integer> startLocatie = new ArrayList<>();
        startLocatie.add(0);
        startLocatie.add(1);
        if (p == 0) {
            for (l = 0; l < Route.size(); l++) {
                int xTemp = Route.get(l).get(0);
                int yTemp = Route.get(l).get(1);
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(xTemp);
                temp.add(yTemp);
                Route2.add(temp);
                if (((l + 1) % 3 == 0)) {

                    Route2.add(startLocatie);
                }
                if (l + 1 == Route.size()) {
                    Route2.add(startLocatie);
                }

            }
        }


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
        g.fillRect(0, 0, 180, 560);
        g.setColor(Color.BLACK);
        g.drawString("BPP", 85, 635);

        //Draw location not retrieved.

        for (x = 0; x < artCount; x++) {
            g.setColor(Color.RED);
            g.fillRect(((aList.get(x).get(1) + 1) * XH) - XH, 560 - ((aList.get(x).get(2) * YW) - YW), 180, 140);
            g.setColor(Color.black);
            g.drawRect(((aList.get(x).get(1) + 1) * XH) - XH, 560 - ((aList.get(x).get(2) * YW) - YW), 180, 140);
        }

        //Colours location green when item has been retrieved.
        for (x = 0; x < p; x++) {
            if (!(Route2.get(x).get(0) == 0 && Route2.get(x).get(1) == 1)) {
                g.setColor(Color.GREEN);
                g.fillRect(((Route2.get(x).get(0) + 1) * XH) - XH, 560 - ((Route2.get(x).get(1) * YW) - YW), 180, 140);
                g.setColor(Color.black);
                g.drawRect(((Route2.get(x).get(0) + 1) * XH) - XH, 560 - ((Route2.get(x).get(1) * YW) - YW), 180, 140);
            }
        }
        //Draws article indexes.
        for (x = 0; x < artCount; x++) {
            g2.setStroke(new BasicStroke(3));

        g2.setColor(Color.BLACK);
        g2.drawString(String.valueOf(aList.get(x).get(0)), (((aList.get(x).get(1) + 1) * XH) - XH) + 85, 635 - ((aList.get(x).get(2) * YW) - YW));
        }




//hier moet ie terug gaan

        //Draw travel route
        if (p == 0) {
            if (p == 0) {
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.BLUE);
                g2.drawLine(90, 640, (((Route.get(0).get(0) + 1) * XH) - XH) + 85, 635 - ((Route.get(0).get(1) * YW) - YW));
            }
        }



        for (x = 0; x < p ; x++) {
            if (p < Route2.size()) {
                g.setColor(Color.BLUE);
                g.drawLine((((Route2.get(x).get(0) + 1) * XH) - XH) + 85, 635 - ((Route2.get(x).get(1) * YW) - YW),
                        (((Route2.get(x + 1).get(0) + 1) * XH) - XH) + 85, 635 - ((Route2.get(x + 1).get(1) * YW) - YW));
            }
        }

        for (x = 0; x < p ; x++) {
            if (x == 0) {
                g.setColor(Color.BLACK);
                g.drawLine(90, 640, (((Route2.get(x).get(0) + 1) * XH) - XH) + 85, 635 - ((Route2.get(x).get(1) * YW) - YW));
            } else if (x > 0) {
                g.setColor(Color.BLACK);
                g.drawLine((((Route2.get(x - 1).get(0) + 1) * XH) - XH) + 85, 635 - ((Route2.get(x - 1).get(1) * YW) - YW),
                        (((Route2.get(x).get(0) + 1) * XH) - XH) + 85, 635 - ((Route2.get(x).get(1) * YW) - YW));

            }
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

    public void setRoute(ArrayList<ArrayList<Integer>> route) {
        Route = route;
    }

}
