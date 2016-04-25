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
public class CircleOfHealing extends SpellCard{

    public CircleOfHealing(){
        super(new Spell(){{
                description = new Description("Circle Of Healing", "Restore 4 health to all monsters.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 0));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                healMonsters(game, 0);
                healMonsters(game, 1);
            }
            
            private void healMonsters(Game game, int playerIndex){
                Player player = game.getPlayer(playerIndex);
                for(int i=0;i<player.getMonsters().size();i++){
                    MonsterCard monsterCard = (MonsterCard) player.getMonsters().get(i);
                    if(game.triggerEvent(new AddMonsterCurrentLifepointsEvent(monsterCard, 4))){
                        if(monsterCard.getCardPosition().getZone() == CardPosition.Zone.GRAVEYARD){
                            i--;
                        }
                    }
                }
            }
        });
    }
}
