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
public class PayCostsEvent extends Event{

    public PayCostsEvent(Player player, Cost cost, Card card){
        this.player = player;
        this.cost = cost;
        this.card = card;
    }
    private Player player;
    private Cost cost;
    private Card card;

    @Override
    public void trigger(Game game){
        if(cost.isTap()){
            game.triggerEvent(new TapEvent((FieldCard) card));
        }
        game.triggerEvent(new SubtractPlayerManaEvent(player, cost.getMana()));
        game.triggerEvent(new AddPlayerLifepointsEvent(player, -1 * cost.getLifepoints()));
    }

    public Player getPlayer(){
        return player;
    }

    public Cost getCost(){
        return cost;
    }

    public Card getCard(){
        return card;
    }
}
