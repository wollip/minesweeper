package minesweeper.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JButton;

import utils.Utils;

public class Tile extends JButton {
    private int state = 0;
    private boolean clicked = false;
    private boolean flagged = false;

    public Tile() {
        super();
        setPreferredSize(new Dimension(50,50));
        setText("   ");
    }

    public void setBomb() {
        state = -1;
    }

    public void incrementState() {
        if (!isBomb()) state++;
    }

    // return true if first click
    public boolean click() {
        if (flagged) return false;
        boolean prevClick = !clicked;
        clicked = true;
        if (isBomb()) {
            try {
                setText("");
                Dimension d = getSize();
                Insets i = getInsets();
                int height = (int) (d.getHeight() - i.bottom - i.top);
                int width = (int) (d.getWidth() - i.left - i.right);
                setIcon(Utils.imageIcon("src\\pictures\\bomb.png", height, width));
            } catch (Exception e) {
                System.out.print(e);
                setText(" * ");
            }
        } else {
            setText(String.format(" %1d ", state));
        }
        return prevClick;
    }

    // return whether flag increase or decrease
    public int flag() {
        if (!clicked) {
            flagged = !flagged;

            if (flagged) {
                try {
                    setText("");
                    Dimension d = getSize();
                    Insets i = getInsets();
                    int height = (int) (d.getHeight() - i.bottom - i.top);
                    int width = (int) (d.getWidth() - i.left - i.right);
                    System.out.println(d);
                    System.out.println(i);
                    setIcon(Utils.imageIcon("src\\pictures\\flag1.png", height, width));
                    setSize(getSize());
                } catch (IOException e) {
                    System.out.print(e);
                    setText(" F ");
                }
            } else {
                setIcon(null);
            }

            return (flagged ? 1 : -1);
        } else {
            return 0;
        }
    }

    public boolean isBomb() {
        return state == -1;
    }

    public boolean expand() {
        return state == 0;
    }
}
