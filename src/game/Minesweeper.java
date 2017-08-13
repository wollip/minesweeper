package game;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

public class Minesweeper extends JPanel{

    private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
    private Random r;
    private int height = 0;
    private int width = 0;

    private int clicked = 0;
    private int clickGoal;

    public Minesweeper(int pixel_height, int pixel_width) {
        super();
        r = new Random(Double.doubleToLongBits(Math.random()));
        setPreferredSize(new Dimension(pixel_height, pixel_width));
    }

    public void update(int x, int y) {
        expandZero(x, y);
        checkDone(x, y);
        System.out.println(clicked);
    }

    private void checkDone(int x, int y) {
        if (tiles.get(x).get(y).isBomb()) {
            System.out.println("game over");
        }

        clicked++;

        if (clicked == clickGoal) {
            System.out.println("well done");
        }

    }

    private void expandZero(int x, int y) {
        if (tiles.get(x).get(y).getState() != 0) return;

        LinkedList<Point> queue = new LinkedList<Point>();

        queue.add(new Point(x,y));
        while (!queue.isEmpty()) {
            Point p = queue.remove();
            int row = (int) p.getX();
            int col = (int) p.getY();

            if (tiles.get(row).get(col).notClicked()) {
                clicked++;
                tiles.get(row).get(col).click();
            }


            if (tiles.get(row).get(col).getState() == 0) {
                for (int a = Math.max(0, row - 1); a < Math.min(height, row + 2); a++) {
                    for (int b = Math.max(0, col - 1); b < Math.min(width, col + 2); b++) {
                        if (a == row && b == col) continue;
                        if (tiles.get(a).get(b).notClicked()) queue.add(new Point(a,b));
                    }
                }
            }
        }
    }

    public void start(int height, int width, int bombs) {
        if (bombs > height * width)
            bombs = (int) (height * width * 0.1);

        clicked = 0;
        clickGoal = height * width - bombs;
        generateTiles(height, width);
        addBombs(bombs);


    }

    private void generateTiles(int height, int width) {
        if (this.height == height && this.width == width) return;

        this.height = height;
        this.width = width;
        setLayout(new GridLayout(height, width, 1, 1));

        tiles.clear();
        for (int i = 0; i < height; i++) {
            ArrayList<Tile> temp = new ArrayList<Tile>();
            for (int i2 = 0; i2 < width; i2++) {
                Tile t = new Tile(i, i2, this);
                t.setActionCommand(Integer.toString(i) + "," +Integer.toString(i2));
                add(t);
                temp.add(t);
            }
            tiles.add(temp);
        }
    }

    private void addBombs(int bombs) {
        for (int i = 0; i < bombs; i++) {
            while (true) {
                int row = r.nextInt(height);
                int col = r.nextInt(width);

                if (tiles.get(row).get(col).isBomb()) continue;
                else tiles.get(row).get(col).setBomb();

                for (int a = Math.max(0, row - 1); a < Math.min(height, row + 2); a++) {
                    for (int b = Math.max(0, col - 1); b < Math.min(width, col + 2); b++) {
                        if (a == row && b == col) continue;
                        tiles.get(a).get(b).incrementState();
                    }
                }
                break;
            }
        }
    }
}
