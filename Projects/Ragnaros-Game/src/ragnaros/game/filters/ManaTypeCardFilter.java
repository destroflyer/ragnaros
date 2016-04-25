/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.filters;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class ManaTypeCardFilter extends CardFilter{

    public ManaTypeCardFilter(Mana manaType){
        this.manaType = manaType;
    }
    private Mana manaType;

    @Override
    public boolean isValid(Card card){
        for(Mana manaType : card.getManaTypes()){
            if(manaType == manaType){
                return true;
            }
        }
        return false;
    }
    
    
}
