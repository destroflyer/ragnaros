/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class CardDestruction extends SpellCard{

    public CardDestruction(){
        super(new Spell(){{
                description = new Description("Card Destruction", "Both players discard all their cards and draw as many new cards.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                renewHand(game, caster.getOwner());
                renewHand(game,caster.getOwner().getEnemy());
            }

            private void renewHand(Game game, Player player){
                int discardedCards = 0;
                for(int i=0;i<player.getHand().size();i++){
                    if(game.triggerEvent(new DiscardEvent(player, i))){
                        discardedCards++;
                        i--;
                    }
                }
                for(int i=0;i<discardedCards;i++){
                    game.triggerEvent(new DrawEvent(player));
                }
            }
        });
    }
}
