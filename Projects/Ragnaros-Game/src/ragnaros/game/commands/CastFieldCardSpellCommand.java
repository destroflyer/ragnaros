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
public class CastFieldCardSpellCommand extends Command{

    public CastFieldCardSpellCommand(){
        
    }

    public CastFieldCardSpellCommand(CardPosition cardPosition, int spellIndex, SpellParameter[] spellParameters){
        this.cardPosition = cardPosition;
        this.spellIndex = spellIndex;
        this.spellParameters = spellParameters;
    }
    private CardPosition cardPosition;
    private int cardIndex;
    private int spellIndex;
    private SpellParameter[] spellParameters;

    public CardPosition getCardPosition(){
        return cardPosition;
    }

    public int getSpellIndex(){
        return spellIndex;
    }

    public SpellParameter[] getSpellParameters(){
        return spellParameters;
    }
}
