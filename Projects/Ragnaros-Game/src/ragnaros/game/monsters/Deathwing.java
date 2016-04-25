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
public class Deathwing extends MonsterCard{

    public Deathwing(){
        description = new Description("Deathwing");
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.DRAGON};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 10));
        attackDamage = 12;
        setLifepoints(12);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Discard your hand and destroy all monsters on the field.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                for(int i=0;i<owner.getHand().size();i++){
                    if(game.triggerEvent(new DiscardEvent(owner, i))){
                        i--;
                    }
                }
                clearMonsters(game, 0);
                clearMonsters(game, 1);
            }
        });
    }

    private void clearMonsters(Game game, int playerIndex){
        Player player = game.getPlayer(playerIndex);
        for(int i=0;i<player.getMonsters().size();i++){
            MonsterCard monsterCard = (MonsterCard) player.getMonsters().get(i);
            if(monsterCard != this){
                if(game.triggerEvent(new DestroyMonsterEvent(monsterCard))){
                    i--;
                }
            }
        }
    }
}
