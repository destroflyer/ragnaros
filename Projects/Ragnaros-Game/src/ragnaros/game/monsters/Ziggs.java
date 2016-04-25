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
public class Ziggs extends MonsterCard{

    public Ziggs(){
        description = new Description("Ziggs");
        flavorText = "\"This doesn't look safe?\"";
        manaTypes = new Mana[]{Mana.RED};
        castCost = new Cost(new ManaAmount(new int[]{1, 0, 1, 0, 0, 0}));
        attackDamage = 2;
        setLifepoints(3);
        setSpells(new Spell(){{
                description = new Description("Bouncing Bomb", "Deal 1 damage to the enemy player.");
                cost = new Cost(new ManaAmount(), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner().getEnemy(), -1));
            }
        }, new Spell(){{
                description = new Description("Self-Explosion", "Destroy this monster and deal 3 damage to the enemy player.");
                cost = new Cost(new ManaAmount(), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                if(game.triggerEvent(new DestroyMonsterEvent((MonsterCard) caster))){
                    game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner().getEnemy(), -3));
                }
            }
        });
    }
}
