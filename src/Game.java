import javax.swing.JFrame;

import game.Minesweeper;

public class Game {
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        Minesweeper game = new Minesweeper(100,100);
        game.start(10, 10, 10);

        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
