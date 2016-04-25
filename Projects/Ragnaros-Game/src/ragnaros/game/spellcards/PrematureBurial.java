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
public class PrematureBurial extends SpellCard{

    public PrematureBurial(){
        super(new Spell(){{
                description = new Description("Premature Burial", "Pay 4 health. Revive the uppermost monster in your graveyard and tap it.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 4), false, 4);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard monsterCard = (MonsterCard) caster.getOwner().getGraveyard().getLast(new ClassCardFilter(MonsterCard.class));
                if(monsterCard != null){
                    if(game.triggerEvent(new ReviveMonsterEvent(caster.getOwner(), monsterCard))){
                        game.triggerEvent(new TapEvent(monsterCard));
                    }
                }
            }
        });
    }
}
