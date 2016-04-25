/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;

/**
 *
 * @author Carl
 */
public class Wukong extends MonsterCard{

    public Wukong(){
        description = new Description("Wukong");
        manaTypes = new Mana[]{Mana.RED};
        castCost = new Cost(new ManaAmount(new int[]{0, 0, 1, 0, 0, 0}));
        attackDamage = 1;
        setLifepoints(1);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Summon Deck Monster", "Summon a random red minion from your deck and tap it.");
                cost = new Cost(new ManaAmount(Mana.RED, 2), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard monsterCard = (MonsterCard) caster.getOwner().getDeck().getRandom(new ManaTypeCardFilter(Mana.RED), new ClassCardFilter(MonsterCard.class));
                if(monsterCard !=  null){
                    if(game.triggerEvent(new SummonDeckMonsterEvent(caster.getOwner(), monsterCard))){
                        game.triggerEvent(new TapEvent(monsterCard));
                    }
                }
            }
        }});
    }
}
