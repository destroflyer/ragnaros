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
public class LandParameter extends SpellParameter{

    public LandParameter(){
        
    }

    public LandParameter(int playerIndex, int landCardIndex){
        this.playerIndex = playerIndex;
        this.landCardIndex = landCardIndex;
    }
    private int playerIndex;
    private int landCardIndex;

    public int getPlayerIndex(){
        return playerIndex;
    }

    public int getLandCardIndex(){
        return landCardIndex;
    }
}
