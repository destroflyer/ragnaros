/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class ThrowingOffChooser extends CardChooser{

    public ThrowingOffChooser(CardChooserManager cardChooserManager){
        super(cardChooserManager);
        isCancelable = false;
    }
    private int playerIndex;

    public void start(int playerIndex){
        this.playerIndex = playerIndex;
        cardChooserManager.setMessage("Choose the card to throw off.");
    }
    
    @Override
    public boolean isValidChoice(Card card){
        return ((card.getOwner().getIndex() == playerIndex)
             && (card.getCardPosition().getZone() == CardPosition.Zone.HAND));
    }
}
