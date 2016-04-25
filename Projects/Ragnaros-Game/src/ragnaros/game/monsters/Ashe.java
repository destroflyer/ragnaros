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
public class Ashe extends MonsterCard{

    public Ashe(){
        description = new Description("Ashe");
        flavorText = "\"For Freljord!\"";
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.WARRIOR};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 3));
        attackDamage = 2;
        setLifepoints(1);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Salve", "Deal 1 damage to all enemy monsters.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 1), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Cards enemyMonsters = caster.getOwner().getEnemy().getMonsters();
                for(int i=0;i<enemyMonsters.size();i++){
                    MonsterCard monsterCard = (MonsterCard) enemyMonsters.get(i);
                    if(game.triggerEvent(new AddMonsterCurrentLifepointsEvent(monsterCard, -1))){
                        if(monsterCard.getCardPosition().getZone() == CardPosition.Zone.GRAVEYARD){
                            i--;
                        }
                    }
                }
            }
        }});
    }
}
