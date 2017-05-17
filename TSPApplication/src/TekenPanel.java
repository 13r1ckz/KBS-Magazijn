import java.awt.*;
import java.util.ArrayList;

class TekenPanel extends Canvas {
    int width, height, rows, columns;
    private int artCount;
    private int YW;
    private int XH;

    TekenPanel(int w, int h, int r, int c, int aL) {
        setArtCount(aL);
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
        for (x = 0; x < artCount ; x++) {
            g.fillRect(1, 1, 179, 139);
        }
    }

    public void setArtCount(int artCount) {
        this.artCount = artCount;
    }

    public void setXH(int XH) {
        this.XH = 139;
    }

    public void setYW(int YW) {
        this.YW = 179;
    }
}
