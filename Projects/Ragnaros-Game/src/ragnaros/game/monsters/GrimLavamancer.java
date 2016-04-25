/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class GrimLavamancer extends MonsterCard{

    public GrimLavamancer(){
        description = new Description("Grim Lavamancer");
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(Mana.RED, 1));
        attackDamage = 1;
        setLifepoints(1);
        setSpells(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Deal 3 damage to your opponent.");
                cost = new Cost(new ManaAmount(Mana.RED, 1), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner().getEnemy(), -3));
            }
        });
    }
}
