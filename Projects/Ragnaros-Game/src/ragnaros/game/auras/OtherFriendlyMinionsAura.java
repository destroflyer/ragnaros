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
public abstract class OtherFriendlyMinionsAura extends FriendlyMinionsAura{
    
    public OtherFriendlyMinionsAura(Buff buff){
        super(buff);
    }

    @Override
    public boolean isAffected(Card card){
        return (super.isAffected(card) && (card != target));
    }
}
