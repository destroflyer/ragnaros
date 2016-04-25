/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Carl
 */
public class CardPool{
    
    private HashMap<Integer, LinkedList<Card>> cardPool = new HashMap<Integer, LinkedList<Card>>();
    
    public Card getCard(int type, int id){
        LinkedList<Card> cards = cardPool.get(type);
        if(cards == null){
            cards = new LinkedList<Card>();
            cardPool.put(type, cards);
        }
        else{
            for(Card card : cards){
                if(card.getID() == id){
                    return card;
                }
            }
        }
        Card card = CardTypeManager.createCard(type);
        cards.add(card);
        return card;
    }
    
    public void clear(){
        cardPool.clear();
    }
}
