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
public class CastSpellCommand extends Command{

    public CastSpellCommand(){
        
    }

    public CastSpellCommand(int handCardIndex, SpellParameter[] spellParameters){
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
