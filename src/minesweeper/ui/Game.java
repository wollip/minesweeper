package minesweeper.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import utils.Utils;

public class Game extends JPanel{

    private int height;
    private int width;
    private int bombs;
    private int bombsRemaining;
    private Minegrid grid;
    private JTextField timeDisplay, bombDisplay;
    private JLabel stateDisplay;

    private Timer timer ;
    private long startTime;

    public Game() {
        //setPreferredSize(new Dimension(500,500));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTHWEST;

        timeDisplay = new JTextField();
        timeDisplay.setEditable(false);
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0.4;
        c.weighty = 0.1;
        add(timeDisplay, c);

        stateDisplay = new JLabel();
        c.gridy = 0;
        c.gridx = 1;
        c.weightx = 0.2;
        c.weighty = 0.1;
        add(stateDisplay, c);

        bombDisplay = new JTextField();
        bombDisplay.setEditable(false);
        c.gridy = 0;
        c.gridx = 2;
        c.weightx = 0.4;
        c.weighty = 0.1;
        add(bombDisplay, c);

        grid = new Minegrid(this);
        c.weightx = 0.0;
        c.weighty = 0.9;
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridheight = 2;
        add(grid, c);
    }

    public void startGame(int height, int width, int bombs) {
        grid.start(height, width, bombs);
        this.height = height;
        this.width = width;
        this.bombs = bombs;
        bombsRemaining = bombs;
        bombDisplay.setText(Integer.toString(bombs));
        try {
            stateDisplay.setHorizontalAlignment(JLabel.CENTER);
            stateDisplay.setText("");
            stateDisplay.setIcon(
                    Utils.imageIcon("src\\pictures\\alive.png",
                            100,
                            100));
        } catch (IOException e) {
            System.out.println(e);
            stateDisplay.setIcon(null);
            stateDisplay.setText("Ongoing");
        }
        timeDisplay.setText("0");
        startTime = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime++;
                timeDisplay.setText(Long.toString(startTime));
            }
        });
        timer.start();
    }

    public void updateBombRemaining(int change) {
        bombsRemaining -= change;
        bombDisplay.setText(Integer.toString(bombsRemaining));
    }

    public void win() {
        if (!timer.isRunning()) return;
        try {
            stateDisplay.setHorizontalAlignment(JLabel.CENTER);
            stateDisplay.setText("");
            stateDisplay.setIcon(
                    Utils.imageIcon("src\\pictures\\win.png",
                            100,
                            100));
        } catch (IOException e) {
            System.out.println(e);
            stateDisplay.setIcon(null);
            stateDisplay.setText("Happy");
        }
        prompt(true);
    }

    public void lose() {
        if (!timer.isRunning()) return;
        try {
            stateDisplay.setHorizontalAlignment(JLabel.CENTER);
            stateDisplay.setText("");
            stateDisplay.setIcon(
                    Utils.imageIcon("src\\pictures\\lose.png",
                            100,
                            100));
        } catch (IOException e) {
            System.out.println(e);
            stateDisplay.setIcon(null);
            stateDisplay.setText("Sad");
        }
        prompt(false);
    }

    private void prompt(boolean win) {
        timer.stop();
        JFrame frame = new JFrame();
        frame.setTitle("Restart?");
        JButton b = new JButton();
        b.setText("reset");
        b.addMouseListener( new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                startGame(height, width, bombs);
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
        frame.add(b);


        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
