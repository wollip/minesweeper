package minesweeper;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import minesweeper.ui.Game;
import minesweeper.ui.Menu;

public class Launcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("MineSweeper");
        frame.setLayout(new GridBagLayout());
        Game game = new Game();
        Menu menu = new Menu(game);
        game.startGame(24, 24, 99   );

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTHWEST;

        c.gridy = 0;
        c.gridx = 0;
        c.weighty = 0.01;
        frame.add(menu, c);

        c.gridy = 1;
        c.gridx = 0;
        c.weighty = 1;
        frame.add(game, c);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
