/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.filters;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class ClassCardFilter extends CardFilter{

    public ClassCardFilter(Class<? extends Card> cardClass){
        this.cardClass = cardClass;
    }
    private Class<? extends Card> cardClass;

    @Override
    public boolean isValid(Card card){
        return (cardClass.isAssignableFrom(card.getClass()));
    }
}
