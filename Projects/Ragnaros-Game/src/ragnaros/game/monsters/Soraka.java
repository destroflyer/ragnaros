/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;
import ragnaros.game.lands.*;

/**
 *
 * @author Carl
 */
public class Soraka extends MonsterCard{

    public Soraka(){
        description = new Description("Soraka");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
        attackDamage = 0;
        setLifepoints(1);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Charge Mana", "Put a random mana land from your deck into play and tap it.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                BasicManaLand basicManaLand = (BasicManaLand) caster.getOwner().getDeck().getRandom(new ClassCardFilter(BasicManaLand.class));
                if(basicManaLand != null){
                    if(game.triggerEvent(new AddDeckLandEvent(caster.getOwner(), basicManaLand))){
                        game.triggerEvent(new TapEvent(basicManaLand));
                    }
                }
            }
        }});
    }
}
