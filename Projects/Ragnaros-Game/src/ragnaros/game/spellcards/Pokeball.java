/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;

/**
 *
 * @author Carl
 */
public class Pokeball extends SpellCard{

    public Pokeball(){
        super(new Spell(){{
                description = new Description("Pokeball", "Summon a random pokemon from your deck.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard monsterCard = (MonsterCard) caster.getOwner().getDeck().getRandom(new MonsterTribeCardFilter(MonsterCard.Tribe.POKEMON));
                if(monsterCard !=  null){
                    game.triggerEvent(new SummonDeckMonsterEvent(caster.getOwner(), monsterCard));
                }
            }
        });
    }
}
