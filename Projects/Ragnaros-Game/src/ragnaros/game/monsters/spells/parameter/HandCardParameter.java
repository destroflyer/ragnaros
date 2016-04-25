/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters.spells.parameter;

import ragnaros.game.SpellParameter;

/**
 *
 * @author Carl
 */
public class HandCardParameter extends SpellParameter{

    public HandCardParameter(){
        
    }

    public HandCardParameter(int playerIndex, int handCardIndex){
        this.playerIndex = playerIndex;
        this.handCardIndex = handCardIndex;
    }
    private int playerIndex;
    private int handCardIndex;

    public int getPlayerIndex(){
        return playerIndex;
    }

    public int getHandCardIndex(){
        return handCardIndex;
    }
}
