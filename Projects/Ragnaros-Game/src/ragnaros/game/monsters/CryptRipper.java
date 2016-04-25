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
public class CryptRipper extends MonsterCard{

    public CryptRipper(){
        description = new Description("Crypt Ripper");
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(new int[]{2, 0, 0, 0, 0, 2}));
        attackDamage = 2;
        setLifepoints(2);
        setSpells(new Spell[]{new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Get +1/+1 until the end of the turn.");
                cost = new Cost(new ManaAmount(Mana.BLACK, 1));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddBuffEvent(caster, new CompositeBuff(new AdditiveAttackDamageBuff(1), new AdditiveMaximumHealthBuff(1)){{
                    setRemainingTurns(1);
                }}, caster));
            }
        }});
    }
}
