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
public abstract class FriendlyMinionsAura extends Aura{
    
    public FriendlyMinionsAura(Buff buff){
        super(buff);
    }

    @Override
    public boolean isAffected(Card card){
        return ((card.getOwner() == target.getOwner()) && (card.getCardPosition().getZone() == CardPosition.Zone.MONSTER));
    }
}
