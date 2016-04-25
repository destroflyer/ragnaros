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
public class Chansey extends MonsterCard{

    public Chansey(){
        description = new Description("Chansey");
        flavorText = "c:";
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.POKEMON};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 3));
        attackDamage = 0;
        setLifepoints(3);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Heal Allies", "Heal all allied monsters for 1.");
                cost = new Cost(new ManaAmount(Mana.WHITE, 1), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Cards monsters = caster.getOwner().getMonsters();
                for(int i=0;i<monsters.size();i++){
                    MonsterCard monsterCard = (MonsterCard) monsters.get(i);
                    game.triggerEvent(new AddMonsterCurrentLifepointsEvent(monsterCard, 1));
                }
            }
        }});
    }
}
