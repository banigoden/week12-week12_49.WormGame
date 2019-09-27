package wormgame.game;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Random randome;
    private Worm worm;
    private Apple apple;
    
    
    
    
   // private Components components;

    public WormGame(int width, int height) {
        super(1000, null);
        randome= new Random();
        int x = randome.nextInt(width);
        int y = randome.nextInt(height);
        apple = new Apple(x, y);
        worm = new Worm(width/2,height/2, Direction.DOWN);
        this.width = width;
        this.height = height;
        this.continues = true;
        
        addActionListener(this);
        setInitialDelay(2000);
        while (true) {
            this.apple.setNewXY(this.randome.nextInt(this.width), this.randome.nextInt(this.height));
            if (!this.worm.runsInto(apple)) {
                break;
            }
        }
    }


    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Worm getWorm(){
    
        return worm;
    }
    
    public void setWorm(Worm worm){
       
        this.worm = worm;
    }
    
    public Apple getApple(){
    
        return apple;   
    }
    
    public void setApple(Apple apple){
       
        this.apple = apple;
    }
    
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        worm.move();
        
        if(worm.runsInto(apple)){
            worm.grow();
            
            while(true){
                this.apple.setNewXY(this.randome.nextInt(this.width), this.randome.nextInt(this.height));
              if(!worm.runsInto(apple)){
               break;
                }  
            }
        }
        if(worm.runsIntoItself()){
                continues = false;
            }
        
        for(Piece p : this.worm.getPieces()){
            if(p.getX() >= this.width || p.getX() <= 0 || p.getY() >= this.height || p.getY()<=0){
            this.continues = false;
            }
        }
        
        updatable.update();
        this.setDelay(1000 / worm.getLength());

    }

}
