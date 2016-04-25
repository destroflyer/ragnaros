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
public class AddPlayerLifepointsEvent extends Event{

    public AddPlayerLifepointsEvent(Player player, int lifepoints){
        this.player = player;
        this.lifepoints = lifepoints;
    }
    protected Player player;
    protected int lifepoints;

    @Override
    public void trigger(Game game){
        player.setLifepoints(player.getLifepoints() + lifepoints);
        if(player.getLifepoints() <= 0){
            player.setLifepoints(0);
            game.triggerEvent(new GameOverEvent(player.getEnemy()));
        }
    }

    public Player getPlayer(){
        return player;
    }

    public int getLifepoints(){
        return lifepoints;
    }
}
