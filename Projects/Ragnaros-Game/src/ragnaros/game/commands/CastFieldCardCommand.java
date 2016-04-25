/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.commands;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class CastFieldCardCommand extends Command{

    public CastFieldCardCommand(){
        
    }

    public CastFieldCardCommand(int handCardIndex, SpellParameter[] spellParameters){
        this.handCardIndex = handCardIndex;
        this.spellParameters = spellParameters;
    }
    private int handCardIndex;
    private SpellParameter[] spellParameters;

    public int getHandCardIndex(){
        return handCardIndex;
    }

    public SpellParameter[] getSpellParameters(){
        return spellParameters;
    }
}
