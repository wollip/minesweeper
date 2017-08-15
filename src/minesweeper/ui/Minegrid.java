package minesweeper.ui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Minegrid extends JPanel{

    private final Random r = new Random(Double.doubleToLongBits(Math.random()));
    private final ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
    private Game game;
    private int height = 0;
    private int width = 0;
    private int unclicked;
    private int bombs;

    public class ButtonAdapter extends MouseAdapter {
        private int x;
        private int y;
        private long time;

        public ButtonAdapter(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            time = System.currentTimeMillis();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (time - System.currentTimeMillis() > 1000) return;
            if (e.getButton() == MouseEvent.BUTTON1) {
                update(x,y,true);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                update(x,y,false);
            }
        }



        //        @Override
        //        public void mouseClicked(MouseEvent e) {
        //            if (e.getButton() == MouseEvent.BUTTON1) {
        //                update(x,y,true);
        //            } else if (e.getButton() == MouseEvent.BUTTON3) {
        //                update(x,y,false);
        //            }
    }


    public Minegrid(Game game) {
        super();
        this.game = game;
    }

    public void start(int height, int width, int bombs) {
        unclicked = height * width;

        this.bombs = bombs;

        generateTiles(height, width);
        addBombs();

        revalidate();
        repaint();
    }

    public void update(int x, int y, boolean leftClick) {
        if (leftClick) {
            click(x, y);
        } else {
            flag(x, y);
        }
    }

    private void click(int x, int y) {
        if (tiles.get(x).get(y).click()) {
            unclicked--;
            if (tiles.get(x).get(y).expand()) {
                for (int a = Math.max(0, x - 1); a < Math.min(height, x + 2); a++) {
                    for (int b = Math.max(0, y - 1); b < Math.min(width, y + 2); b++) {
                        click(a, b);
                    }
                }
            } else if (tiles.get(x).get(y).isBomb()) {
                game.lose();
            }
        }

        if (unclicked == bombs) {
            game.win();
        }
    }

    private void flag(int x, int y) {
        game.updateBombRemaining(tiles.get(x).get(y).flag());
    }

    private void generateTiles(int height, int width) {
        this.height = height;
        this.width = width;

        for (ArrayList<Tile> ts : tiles) {
            for(Tile t : ts) {
                remove(t);
            }
        }
        tiles.clear();
        setLayout(new GridLayout(height, width, 1, 1));

        //        GridBagConstraints c = new GridBagConstraints();
        //        c.fill = GridBagConstraints.BOTH;
        //        c.anchor = GridBagConstraints.NORTHWEST;
        //        c.weightx = 1;
        //        c.weighty = 1;
        for (int i = 0; i < height; i++) {
            ArrayList<Tile> temp = new ArrayList<Tile>();
            for (int i2 = 0; i2 < width; i2++) {
                Tile t = new Tile();
                t.addMouseListener(new ButtonAdapter(i, i2));
                //                c.gridy = i;
                //                c.gridx = i2;
                //                add(t, c);
                add(t);
                temp.add(t);
            }
            tiles.add(temp);
        }
    }

    private void addBombs() {
        for (int i = 0; i < bombs; i++) {
            while(true) {
                int row = r.nextInt(height);
                int col = r.nextInt(width);

                if (tiles.get(row).get(col).isBomb()) continue;
                else tiles.get(row).get(col).setBomb();

                for (int a = Math.max(0, row - 1); a < Math.min(height, row + 2); a++) {
                    for (int b = Math.max(0, col - 1); b < Math.min(width, col + 2); b++) {
                        tiles.get(a).get(b).incrementState();
                    }
                }
                break;
            }
        }
    }
}
