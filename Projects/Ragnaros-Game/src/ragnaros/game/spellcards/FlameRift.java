/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class FlameRift extends SpellCard{

    public FlameRift(){
        super(new Spell(){{
                description = new Description("Flame Rift", "Deal 4 damage to both players.");
                cost = new Cost(new ManaAmount(new int[]{1, 0, 1, 0, 0, 0}));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner(), -4));
                game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner().getEnemy(), -4));
            }
        });
    }
}
