/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;
import ragnaros.game.monsters.spells.parameter.*;

/**
 *
 * @author Carl
 */
public class Bicycle extends SpellCard{

    public Bicycle(){
        super(new Spell(){{
                description = new Description("Bicycle", "Discard a pokemon and draw 3 cards.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the pokemon to discard.", SpellParameterFormat_Entry.Type.HAND_CARD, SpellParameterFormat_Entry.Owner.ALLIED, new MonsterTribeCardFilter(MonsterCard.Tribe.POKEMON));
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                int handCardIndex = ((HandCardParameter) parameters[0]).getHandCardIndex();
                Card card = caster.getOwner().getHand().get(handCardIndex);
                game.triggerEvent(new DiscardEvent(caster.getOwner(), handCardIndex));
                for(int i=0;i<3;i++){
                    game.triggerEvent(new DrawEvent(caster.getOwner()));
                }
            }
        });
    }
}
