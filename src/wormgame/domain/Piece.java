/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

/**
 *
 * @author xbandude1
 */
public class Piece {
    private int x;
    private int y;
    
    public Piece(int x, int y){
    
        this.x = x;
        this.y = y;
    }
    public int getX(){
    
        return x;
    }
     public int getY(){
    
        return y;
    }
     public void setNewXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
     
     public boolean runsInto(Piece piece){
     
        return piece.getX() == x && piece.getY() == y ;
         
     }
     
     @Override
     public String toString(){
     StringBuilder sb = new StringBuilder();
         return sb.append("(" + getX() + ", " + getY() + ")").toString();
    }
}
