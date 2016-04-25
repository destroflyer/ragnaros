/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class SphinxOfMagosi extends MonsterCard{

    public SphinxOfMagosi(){
        description = new Description("Sphinx of Magosi");
        manaTypes = new Mana[]{Mana.BLUE};
        castCost = new Cost(new ManaAmount(new int[]{3, 0, 0, 0, 3, 0}));
        attackDamage = 6;
        setLifepoints(6);
        setSpells(new Spell[]{new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Draw a card and get +1/+1.");
                cost = new Cost(new ManaAmount(new int[]{2, 0, 0, 0, 1, 0}));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new DrawEvent(caster.getOwner()));
                game.triggerEvent(new AddBuffEvent(caster, new CompositeBuff(new AdditiveAttackDamageBuff(1), new AdditiveMaximumHealthBuff(1)), caster));
            }
        }});
    }
}
