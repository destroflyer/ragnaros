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
public class Fireblast extends SpellCard{

    public Fireblast(){
        super(new Spell(){{
                description = new Description("Fireblast", "Deal 1 damage to the enemy player.");
                cost = new Cost(new ManaAmount(Mana.RED, 1));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner().getEnemy(), -1));
            }
        });
        isCollectible = false;
    }
}
