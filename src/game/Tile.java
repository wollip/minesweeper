package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Tile extends JButton{

    private int state = 0;
    private boolean clicked = false;
    private boolean flaged = false;
    private int x, y;
    private Minesweeper game;

    public Tile(int x, int y, Minesweeper game) {
        super();
        this.x = x;
        this.y = y;
        this.game = game;

        addMouseListener( new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (arg0.getButton() == MouseEvent.BUTTON2) return;

                if (arg0.getButton() == MouseEvent.BUTTON1) {
                    click();
                } else if (arg0.getButton() == MouseEvent.BUTTON3) {
                    if (!clicked) {
                        flaged = !flaged;
                        if (flaged) setText("F");
                        else setText("");
                    }

                }

                game.update(x, y);
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
    }

    public void incrementState() {
        if (!isBomb()) state++;
    }

    public void click() {
        clicked = true;
        setText(Integer.toString(state));
    }

    public boolean notClicked() {
        return !clicked;
    }

    public int getState() {
        return state;
    }

    public void setBomb() {
        state = -1;
    }

    public boolean isBomb() {
        return state == -1;
    }
}
