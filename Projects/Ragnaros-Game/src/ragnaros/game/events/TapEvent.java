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
public class TapEvent extends Event{

    public TapEvent(FieldCard fieldCard){
        this.fieldCard = fieldCard;
    }
    private FieldCard fieldCard;

    @Override
    public void trigger(Game game){
        fieldCard.tap();
    }

    public Card getFieldCard(){
        return fieldCard;
    }
}
