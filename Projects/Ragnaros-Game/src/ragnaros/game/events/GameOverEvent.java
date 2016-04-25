/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.events;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class GameOverEvent extends Event{

    public GameOverEvent(Player winner){
        this.winner = winner;
    }
    private Player winner;

    public Player getWinner(){
        return winner;
    }

    @Override
    public void trigger(Game game){
        game.onGameOver();
    }
}
