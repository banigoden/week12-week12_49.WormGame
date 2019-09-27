package wormgame.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import wormgame.game.WormGame;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WormGame game;
    private int sideLength;
    private  DrawingBoard drawingBoard;

    public UserInterface(WormGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
    }

    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        int width = (game.getWidth() + 1) * sideLength + 10;
        int height = (game.getHeight() + 2) * sideLength + 10;

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        frame.setBackground(Color.GRAY);
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        // Create drawing board first which then is added into container-object.
         this.drawingBoard = new DrawingBoard(this.game, sideLength);
         container.add(drawingBoard);
        // After this, create keyboard listener which is added into frame-object
        KeyboardListener key = new KeyboardListener(this.game.getWorm());
        frame.addKeyListener(key);
    }


    public JFrame getFrame() {
        return frame;
    }

    public  Updatable getUpdatable() {
        return drawingBoard;
    }
}
