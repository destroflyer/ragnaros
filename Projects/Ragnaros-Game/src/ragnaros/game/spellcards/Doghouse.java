/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Doghouse extends SpellCard{

    public Doghouse(){
        super(new Spell(){{
                description = new Description("Doghouse", "Increase the lifepoints of all allied beasts by 2.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                for(int i=0;i<caster.getOwner().getMonsters().size();i++){
                    MonsterCard monsterCard = (MonsterCard) caster.getOwner().getMonsters().get(i);
                    if(monsterCard.hasTribe(MonsterCard.Tribe.BEAST)){
                        game.triggerEvent(new AddBuffEvent(monsterCard, new AdditiveMaximumHealthBuff(2), caster));
                    }
                }
            }
        });
    }
}
