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
public class Kappa extends SpellCard{

    public Kappa(){
        super(new Spell(){{
                description = new Description("Kappa", "Destroy all monsters on the field.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 4));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                clearMonsters(game, 0);
                clearMonsters(game, 1);
            }
            
            private void clearMonsters(Game game, int playerIndex){
                Player player = game.getPlayer(playerIndex);
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
