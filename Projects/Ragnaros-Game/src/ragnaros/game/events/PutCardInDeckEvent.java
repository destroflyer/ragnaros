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
public class PutCardInDeckEvent extends Event{

    public PutCardInDeckEvent(Player player, Card card){
        this.player = player;
        this.card = card;
    }
    protected Player player;
    protected Card card;

    @Override
    public void trigger(Game game){
        card.setOwner(player);
        player.getDeck().add(card);
    }

    public Player getPlayer(){
        return player;
    }

    public Card getCard(){
        return card;
    }
}
