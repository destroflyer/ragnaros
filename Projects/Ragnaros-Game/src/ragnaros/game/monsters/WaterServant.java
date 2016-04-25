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
public class WaterServant extends MonsterCard{

    public WaterServant(){
        description = new Description("Water Servant");
        manaTypes = new Mana[]{Mana.BLUE};
        castCost = new Cost(new ManaAmount(new int[]{2, 0, 0, 0, 2, 0}));
        attackDamage = 3;
        setLifepoints(4);
        setSpells(new Spell(){{
                description = new Description("Get +1/-1", "Get +1/-1 until the end of the turn.");
                cost = new Cost(new ManaAmount(Mana.BLUE, 1));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddBuffEvent(caster, new CompositeBuff(new AdditiveAttackDamageBuff(1), new AdditiveMaximumHealthBuff(-1)){{
                    setRemainingTurns(1);
                }}, caster));
            }
        }, new Spell(){{
                description = new Description("Get -1/+1", "Get -1/+1 until the end of the turn.");
                cost = new Cost(new ManaAmount(Mana.BLUE, 1));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddBuffEvent(caster, new CompositeBuff(new AdditiveAttackDamageBuff(-1), new AdditiveMaximumHealthBuff(1)){{
                    setRemainingTurns(1);
                }}, caster));
            }
        });
    }
}
