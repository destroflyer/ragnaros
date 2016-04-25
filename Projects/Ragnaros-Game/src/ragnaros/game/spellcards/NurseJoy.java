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
public class NurseJoy extends SpellCard{

    public NurseJoy(){
        super(new Spell(){{
                description = new Description("Nurse Joy", "Restore all pokemons to full health.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 0));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                healPokemons(game, 0);
                healPokemons(game, 1);
            }
            
            private void healPokemons(Game game, int playerIndex){
                Player player = game.getPlayer(playerIndex);
                for(int i=0;i<player.getMonsters().size();i++){
                    MonsterCard monsterCard = (MonsterCard) player.getMonsters().get(i);
                    if(monsterCard.hasTribe(MonsterCard.Tribe.POKEMON)){
                        game.triggerEvent(new AddMonsterCurrentLifepointsEvent(monsterCard, monsterCard.getMaximumLifepoints()));
                    }
                }
            }
        });
    }
}
