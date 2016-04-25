/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.game.modes.*;

/**
 *
 * @author Carl
 */
public abstract class GameMode implements EventListener{

    public GameMode(){
        
    }
    public static final GameMode DEFAULT = new DestroMode();
    protected Game game;
    
    public abstract void prepareCard(Card card);
    
    public abstract void preparePlayer(Player player);
    
    public abstract void startTurn(Player player);
    
    public abstract void attackMonster(MonsterCard attackingMonsterCard, MonsterCard targetMonsterCard);

    public abstract int getMaximumHandCards(Player player);
    
    public abstract void endTurn(Player player);

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}
