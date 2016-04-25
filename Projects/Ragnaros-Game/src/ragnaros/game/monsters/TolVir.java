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
public class TolVir extends MonsterCard{

    public TolVir(){
        description = new Description("Tol'vir");
        manaTypes = new Mana[]{Mana.WHITE};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 7));
        attackDamage = 7;
        setLifepoints(7);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Heal all allies for 2 and damage all enemies for 2.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                applySummonEffect(owner);
                applySummonEffect(owner.getEnemy());
            }
        });
    }
    
    private void applySummonEffect(Player player){
        int lifepoints = ((player == owner)?2:-2);
        game.triggerEvent(new AddPlayerLifepointsEvent(player, lifepoints));
        Cards monsters = player.getMonsters();
        for(int i=0;i<monsters.size();i++){
            MonsterCard monsterCard = (MonsterCard) monsters.get(i);
            if(game.triggerEvent(new AddMonsterCurrentLifepointsEvent(monsterCard, lifepoints))){
                if(monsterCard.getCardPosition().getZone() == CardPosition.Zone.GRAVEYARD){
                    i--;
                }
            }
        }
    }
}
