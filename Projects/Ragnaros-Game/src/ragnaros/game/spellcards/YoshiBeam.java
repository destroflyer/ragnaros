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
public class YoshiBeam extends SpellCard{

    public YoshiBeam(){
        super(new Spell(){{
                description = new Description("Yoshi Beam", "Deal 4 damage to all enemy monsters.");
                cost = new Cost(new ManaAmount(Mana.BLUE, 7));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Cards enemyMonsters = caster.getOwner().getEnemy().getMonsters();
                for(int i=0;i<enemyMonsters.size();i++){
                    MonsterCard monsterCard = (MonsterCard) enemyMonsters.get(i);
                    if(game.triggerEvent(new AddMonsterCurrentLifepointsEvent(monsterCard, -4))){
                        if(monsterCard.getCardPosition().getZone() == CardPosition.Zone.GRAVEYARD){
                            i--;
                        }
                    }
                }
            }
        });
        manaTypes = new Mana[]{Mana.BLUE};
        flavorText = "Yoshistrike?";
    }
}
