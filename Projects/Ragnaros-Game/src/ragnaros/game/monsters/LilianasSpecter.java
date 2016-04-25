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
public class LilianasSpecter extends MonsterCard{

    public LilianasSpecter(){
        description = new Description("Liliana's Specter");
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(new int[]{1, 0, 0, 0, 0, 2}));
        attackDamage = 2;
        setLifepoints(1);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Your opponents discards a random card.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Card randomEnemyHandCard = caster.getOwner().getEnemy().getHand().getRandom();
                if(randomEnemyHandCard != null){
                    game.triggerEvent(new DiscardEvent(randomEnemyHandCard));
                }
            }
        });
    }
}
