/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author carl
 */
public class ResettedCards extends Cards{

    public ResettedCards(){
        
    }

    public ResettedCards(CardPosition.Zone zone){
        super(zone);
    }
    
    @Override
    public void add(Card card){
        super.add(card);
        reset(card);
    }

    @Override
    public void add(Card card, int index){
        super.add(card, index);
        reset(card);
    }
    
    private void reset(Card card){
        card.removeBuffs();
        if(card instanceof FieldCard){
            FieldCard fieldCard = (FieldCard) card;
            fieldCard.untap();
        }
    }
}
