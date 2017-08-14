package minesweeper.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel{

    public Menu(Game game) {
        super();
        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        JTextField[] fields = new JTextField[3];
        JLabel[] labels = {new JLabel("height"), new JLabel("width"), new JLabel("bombs")};
        for (int i = 0; i < 3; i++) {
            add(labels[i]);
            fields[i] = new JTextField();
            fields[i].setPreferredSize(new Dimension(30,20));
            add(fields[i]);
        }

        JButton b = new JButton("Reset");
        b.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                int height = Integer.parseInt(fields[0].getText());
                int width = Integer.parseInt(fields[1].getText());
                int bombs = Integer.parseInt(fields[2].getText());
                System.out.println(height + ", " + width + ", " + bombs);
                game.startGame(height, width, bombs);
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
                try {
                    int height = Integer.parseInt(fields[0].getText());
                    int width = Integer.parseInt(fields[1].getText());
                    int bombs = Integer.parseInt(fields[2].getText());
                    game.startGame(height, width, bombs);
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }

            }

        });
        add(b);
    }
}
