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
public class AttackTargetChooser extends CardChooser{

    public AttackTargetChooser(CardChooserManager cardChooserManager){
        super(cardChooserManager);
    }
    private int playerIndex;
    private int attack_MonsterCardIndex;

    public void start(int playerIndex, int attack_MonsterCardIndex){
        this.playerIndex = playerIndex;
        this.attack_MonsterCardIndex = attack_MonsterCardIndex;
        cardChooserManager.setMessage("Choose the monster to attack.");
    }
    
    @Override
    public boolean isValidChoice(Card card){
        return ((card instanceof MonsterCard)
             && (card.getOwner().getIndex() != playerIndex)
             && (card.getCardPosition().getZone() == CardPosition.Zone.MONSTER));
    }

    public int getAttack_MonsterCardIndex(){
        return attack_MonsterCardIndex;
    }
}
