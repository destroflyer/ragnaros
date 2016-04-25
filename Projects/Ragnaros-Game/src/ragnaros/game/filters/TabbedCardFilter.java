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
public class TabbedCardFilter extends ClassCardFilter{

    public TabbedCardFilter(boolean isTapped){
        super(FieldCard.class);
        this.isTapped = isTapped;
    }
    private boolean isTapped;

    @Override
    public boolean isValid(Card card){
        if(super.isValid(card)){
            FieldCard fieldCard = (FieldCard) card;
            return (fieldCard.isTapped() == isTapped);
        }
        return false;
    }
}
