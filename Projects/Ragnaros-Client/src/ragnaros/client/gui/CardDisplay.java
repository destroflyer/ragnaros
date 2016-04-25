/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client.gui;

import ragnaros.game.Card;

/**
 *
 * @author Carl
 */
public class CardDisplay{

    public CardDisplay(Card card){
        this.card = card;
    }
    public final static int WIDTH = 300;
    public final static int HEIGHT = 400;
    private Card card;
    private boolean isFront;
    private boolean isHighlighted;
    private int x;
    private int y;
    private int targetX;
    private int targetY;
    private int tempX;
    private int tempY;
    
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
        targetX = x;
        targetY = y;
    }
    
    public void setTargetLocation(int targetX, int targetY){
        this.targetX = targetX;
        this.targetY = targetY;
    }
    
    public void saveTempLocation(){
        tempX = x;
        tempY = y;
    }
    
    public void targetTempLocation(){
        targetX = tempX;
        targetY = tempY;
    }
    
    public void update(float lastTimePerFrame){
        int diffX = (targetX - x);
        int diffY = (targetY - y);
        if((diffX != 0) || (diffY != 0)){
            double diffLength = Math.sqrt((diffX * diffX) + (diffY * diffY));
            double speed = Math.max(400, diffLength * 4);
            if(diffX != 0){
                x += ((diffX / diffLength) * speed * lastTimePerFrame);
                int newDiffX = (targetX - x);
                if(Math.signum(newDiffX) != Math.signum(diffX)){
                    x = targetX;
                }
            }
            if(diffY != 0){
                y += ((diffY / diffLength) * speed * lastTimePerFrame);
                int newDiffY = (targetY - y);
                if(Math.signum(newDiffY) != Math.signum(diffY)){
                    y = targetY;
                }
            }
        }
    }
    
    public boolean intersects(int x, int y){
        int hitboxX = (this.x + 25);
        int hitboxY = (this.y + 30);
        return ((x >= hitboxX) && (y >= hitboxY) && (x < (hitboxX + 251)) && (y < (hitboxY + 355)));
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return card;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setIsFront(boolean isFront){
        this.isFront = isFront;
    }

    public boolean isFront(){
        return isFront;
    }

    public void setIsHighlighted(boolean isHighlighted){
        this.isHighlighted = isHighlighted;
    }

    public boolean isHighlighted(){
        return isHighlighted;
    }
}
