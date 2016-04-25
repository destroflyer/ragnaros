/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.game.events.AddPlayerManaEvent;

/**
 *
 * @author carl
 */
public abstract class ManaSpell extends Spell{

    public ManaSpell(){
        
    }

    @Override
    public void cast(Game game, SpellParameter[] parameters){
        game.triggerEvent(new AddPlayerManaEvent(caster.getOwner(), getGainedManaAmount()));
    }
    
    public abstract ManaAmount getGainedManaAmount();
}
