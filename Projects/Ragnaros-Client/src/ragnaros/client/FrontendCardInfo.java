/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import ragnaros.game.Card;
import ragnaros.game.CardPosition;

/**
 *
 * @author Carl
 */
public class FrontendCardInfo{

    public FrontendCardInfo(CardPosition cardPosition){
        this.cardPosition = cardPosition;
    }
    private CardPosition cardPosition;
    private Card card;

    public CardPosition getCardPosition(){
        return cardPosition;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return card;
    }
}
