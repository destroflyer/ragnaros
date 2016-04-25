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
public class KappaMeteor extends SpellCard{

    public KappaMeteor(){
        super(new Spell(){{
                description = new Description("Kappa Meteor", "Destroy all cards on the field.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 6));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                clearCards(game, 0);
                clearCards(game, 1);
            }
            
            private void clearCards(Game game, int playerIndex){
                Player player = game.getPlayer(playerIndex);
                for(int i=0;i<player.getLands().size();i++){
                    Land land = (Land) player.getLands().get(i);
                    if(game.triggerEvent(new RemoveLandEvent(land))){
                        i--;
                    }
                }
                for(int i=0;i<player.getMonsters().size();i++){
                    MonsterCard monsterCard = (MonsterCard) player.getMonsters().get(i);
                    if(game.triggerEvent(new DestroyMonsterEvent(monsterCard))){
                        i--;
                    }
                }
            }
        });
    }
}
