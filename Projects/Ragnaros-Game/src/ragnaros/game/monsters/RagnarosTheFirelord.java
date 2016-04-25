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
public class RagnarosTheFirelord extends MonsterCard{

    public RagnarosTheFirelord(){
        description = new Description("Ragnaros the Firelord", "At the end of your turn, deal 8 damage to a random enemy (monster or player).");
        flavorText = "\"By fire be purged!\"";
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.PYRO};
        castCost = new Cost(new ManaAmount(Mana.RED, 8));
        attackDamage = 0;
        setLifepoints(8);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof TurnEndEvent){
            TurnEndEvent turnEndEvent = (TurnEndEvent) receivedEvent;
            if((turnEndEvent.getPlayer() == owner) && (cardPosition.getZone() == CardPosition.Zone.MONSTER)){
                int enemyIndex = (int) (Math.random() * (owner.getEnemy().getMonsters().size() + 1));
                if(enemyIndex < owner.getEnemy().getMonsters().size()){
                    Card targetCard = owner.getEnemy().getMonsters().get(enemyIndex);
                    game.triggerEvent(new AddMonsterCurrentLifepointsEvent((MonsterCard) targetCard, -8));
                }
                else{
                    game.triggerEvent(new AddPlayerLifepointsEvent(owner.getEnemy(), -8));
                }
            }
        }
    }
}
