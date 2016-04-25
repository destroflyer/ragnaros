/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class RoyMustang extends MonsterCard{

    public RoyMustang(){
        description = new Description("Roy Mustang");
        flavorText = "Flame Alchemist";
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.WARRIOR};
        castCost = new Cost(new ManaAmount(Mana.RED, 5));
        attackDamage = 5;
        setLifepoints(4);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Inferno", "Deal 2 damage to all non-red minions.");
                cost = new Cost(new ManaAmount(Mana.RED, 2), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                damageMinions(game, 0);
                damageMinions(game, 1);
            }
            
            private void damageMinions(Game game, int playerIndex){
                Player player = game.getPlayer(playerIndex);
                for(int i=0;i<player.getMonsters().size();i++){
                    MonsterCard monsterCard = (MonsterCard) player.getMonsters().get(i);
                    if(monsterCard.getManaTypes()[0] != Mana.RED){
                        if(game.triggerEvent(new AddMonsterCurrentLifepointsEvent(monsterCard, -2))){
                            if(monsterCard.getCardPosition().getZone() == CardPosition.Zone.GRAVEYARD){
                                i--;
                            }
                        }
                    }
                }
            }
        }});
    }
}
