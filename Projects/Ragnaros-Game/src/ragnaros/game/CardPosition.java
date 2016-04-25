/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public class CardPosition{

    public CardPosition(){
        
    }

    public CardPosition(Zone zone, int index){
        this.zone = zone;
        this.index = index;
    }
    public enum Zone{
        DECK,
        HAND,
        LAND,
        MONSTER,
        GRAVEYARD
    }
    private Zone zone;
    private int index;

    public void setZone(Zone zone){
        this.zone = zone;
    }

    public Zone getZone(){
        return zone;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
