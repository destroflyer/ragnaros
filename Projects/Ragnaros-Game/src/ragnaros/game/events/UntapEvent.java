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
public class UntapEvent extends Event{

    public UntapEvent(FieldCard fieldCard){
        this.fieldCard = fieldCard;
    }
    private FieldCard fieldCard;

    @Override
    public void trigger(Game game){
        fieldCard.untap();
    }

    public FieldCard getFieldCard(){
        return fieldCard;
    }
}
