/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public class PutCardInGraveyardEvent extends Event{

    public PutCardInGraveyardEvent(Player player, Card card){
        this.player = player;
        this.card = card;
    }
    private Player player;
    protected Card card;

    @Override
    public void trigger(Game game){
        player.getGraveyard().add(card);
    }

    public Player getPlayer(){
        return player;
    }

    public Card getCard(){
        return card;
    }
}
