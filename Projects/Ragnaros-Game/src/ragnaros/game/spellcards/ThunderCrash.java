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
public class ThunderCrash extends SpellCard{

    public ThunderCrash(){
        super(new Spell(){{
                description = new Description("Thunder Crash", "Destroy all of your monsters and deal 2 damage for each one to your enemy.");
                cost = new Cost(new ManaAmount(Mana.RED, 5));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Cards monsters = caster.getOwner().getMonsters();
                for(int i=0;i<monsters.size();i++){
                    MonsterCard monsterCard = (MonsterCard) monsters.get(i);
                    if(game.triggerEvent(new DestroyMonsterEvent(monsterCard))){
                        game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner().getEnemy(), -2));
                        i--;
                    }
                }
            }
        });
    }
}
