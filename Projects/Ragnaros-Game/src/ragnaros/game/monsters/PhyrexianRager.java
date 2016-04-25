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
public class PhyrexianRager extends MonsterCard{

    public PhyrexianRager(){
        description = new Description("Phyrexian Rager");
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(new int[]{2, 0, 0, 0, 0, 1}), false, 1);
        attackDamage = 2;
        setLifepoints(2);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Pay 1 lifepoint and draw a card.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new DrawEvent(caster.getOwner()));
            }
        });
    }
}
