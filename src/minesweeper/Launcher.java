package minesweeper;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import minesweeper.ui.Game;
import minesweeper.ui.Menu;

public class Launcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("MineSweeper");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        Game game = new Game();
        Menu menu = new Menu(game);
        game.startGame(10, 10, 50);

        frame.add(menu);
        frame.add(game);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
