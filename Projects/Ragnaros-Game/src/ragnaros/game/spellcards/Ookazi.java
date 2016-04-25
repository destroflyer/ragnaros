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
public class Ookazi extends SpellCard{

    public Ookazi(){
        super(new Spell(){{
                description = new Description("Ookazi", "Deal 4 damage to the enemy player.");
                cost = new Cost(new ManaAmount(Mana.RED, 3));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner().getEnemy(), -4));
            }
        });
    }
}
