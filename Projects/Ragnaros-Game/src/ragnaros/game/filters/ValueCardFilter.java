/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.filters;

import ragnaros.game.*;

/**
 *
 * @author carl
 */
public abstract class ValueCardFilter<T extends Card> extends ClassCardFilter{

    public ValueCardFilter(Class<? extends T> cardClass){
        super(cardClass);
    }

    @Override
    public boolean isValid(Card card){
        if(super.isValid(card)){
            float value = getValue((T) card);
            return isValid(value);
        }
        return false;
    }
    
    protected abstract float getValue(T card);
    
    protected abstract boolean isValid(float value);
}
