import java.awt.*;
import java.util.ArrayList;

class TekenPanel extends Canvas {
    int width, height, rows, columns;
    private int artCount;

    TekenPanel(int w, int h, int r, int c, int artlist) {
        int artCount = artlist;
        setSize(width = w, height = h);
        rows = r;
        columns = c;
    }
    public void paint(Graphics g) {
        int k;
        int x;
        width = width;
        height = height;

        int htOfRow = height / (rows);
        for (k = 0; k <= rows; k++) {
            g.drawLine(0, k * htOfRow, width, k * htOfRow);
        }

        int wdOfRow = width / (columns);
        for (k = 0; k <= columns; k++) {
            g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
        }

        g.setColor(Color.RED);
        //System.out.println(artCount);
        for (x = 0; x < artCount ; x++) {
            g.fillRect(200 * x, 20 * x, 200, 200);
            g.fillRect(200,50,100,100);
        }
    }
}
