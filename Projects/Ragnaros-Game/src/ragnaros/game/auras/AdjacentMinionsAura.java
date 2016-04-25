/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.auras;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public abstract class AdjacentMinionsAura extends FriendlyMinionsAura{
    
    public AdjacentMinionsAura(Buff buff){
        super(buff);
    }

    @Override
    public boolean isAffected(Card card){
        if(super.isAffected(card)){
            int zoneDistance = Math.abs(card.getCardPosition().getIndex() - target.getCardPosition().getIndex());
            return (zoneDistance == 1);
        }
        return false;
    }
}
