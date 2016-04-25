/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import ragnaros.game.Card;

/**
 *
 * @author Carl
 */
public abstract class CardChooser{
    
    protected CardChooser(CardChooserManager cardChooserManager){
        this.cardChooserManager = cardChooserManager;
    }
    protected  CardChooserManager cardChooserManager;
    protected boolean isCancelable = true;
    
    public abstract boolean isValidChoice(Card card);
    
    public void stop(){
        
    }

    public boolean isCancelable(){
        return isCancelable;
    }
}
