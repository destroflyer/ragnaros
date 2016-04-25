/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.modes;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class DestroMode extends GameMode{

    @Override
    public void prepareCard(Card card){
        
    }

    @Override
    public void preparePlayer(Player player){
        player.setLifepoints(30);
        player.getDeck().shuffle();
        for(int i=0;i<5;i++){
            game.triggerEvent(new DrawEvent(player));
        }
    }
    
    @Override
    public void startTurn(Player player){
        game.triggerEvent(new DrawEvent(player));
        for(int i=0;i<player.getLands().size();i++){
            Land land = (Land) player.getLands().get(i);
            game.triggerEvent(new UntapEvent(land));
        }
        for(int i=0;i<player.getMonsters().size();i++){
            MonsterCard monsterCard = (MonsterCard) player.getMonsters().get(i);
            monsterCard.setIsExhausted(false);
            game.triggerEvent(new UntapEvent(monsterCard));
        }
        player.getAvailableMana().clear();
        player.setHasAddedLandThisTurn(false);
    }

    @Override
    public void attackMonster(MonsterCard attackingMonsterCard, MonsterCard targetMonsterCard){
        game.triggerEvent(new AddMonsterCurrentLifepointsEvent(targetMonsterCard, -1 * attackingMonsterCard.getAttackDamage(), attackingMonsterCard));
        game.triggerEvent(new AddMonsterCurrentLifepointsEvent(attackingMonsterCard, -1 * targetMonsterCard.getAttackDamage(), targetMonsterCard));
    }

    @Override
    public int getMaximumHandCards(Player player){
        return 6;
    }
    
    @Override
    public void endTurn(Player player){
        
    }

    public boolean preEvent(Game game, Event receivedEvent){
        return true;
    }

    public void postEvent(Game game, Event receivedEvent){
        
    }
}
