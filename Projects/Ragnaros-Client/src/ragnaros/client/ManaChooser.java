/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import java.awt.Color;
import java.util.LinkedList;
import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class ManaChooser extends CardChooser{

    public ManaChooser(CardChooserManager cardChooserManager){
        super(cardChooserManager);
    }
    public enum ManaChooseType{
        FIELD_CARD_CAST_COST,
        FIELD_CARD_SPELL_COST,
        SPELL_COST
    }
    private ManaChooseType type;
    private int playerIndex;
    private int[] chooseInformation;
    private ManaAmount remainingAmount;
    private LinkedList<ManaSpell> manaSpells = new LinkedList<ManaSpell>();

    public void start(ManaChooseType type, int playerIndex, int[] chooseInformation, ManaAmount manaAmount){
        this.type = type;
        this.playerIndex = playerIndex;
        this.chooseInformation = chooseInformation;
        remainingAmount = manaAmount.clone();
        manaSpells.clear();
        updateMessage();
    }
    
    @Override
    public boolean isValidChoice(Card card){
        if((card.getOwner().getIndex() == playerIndex)
        && ((card.getCardPosition().getZone() == CardPosition.Zone.LAND) || (card.getCardPosition().getZone() == CardPosition.Zone.MONSTER))){
            FieldCard fieldCard = (FieldCard) card;
            for(Spell spell : fieldCard.getSpells()){
                if(spell instanceof ManaSpell){
                    ManaSpell manaSpell = (ManaSpell) spell;
                    if(card.getOwner().canCast(manaSpell) && isValidChoice(manaSpell)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isValidChoice(ManaSpell manaSpell){
        return manaSpell.getGainedManaAmount().canBeUsedToPay(remainingAmount);
    }
    
    public void addManaSpell(ManaSpell manaSpell){
        manaSpells.add(manaSpell);
        remainingAmount.subtractFromRemaining(manaSpell.getGainedManaAmount());
        if(!isFinished()){
            updateMessage();
        }
    }
    
    private void updateMessage(){
        cardChooserManager.setMessage("Please choose " + getManaHTML(remainingAmount) + " mana.");
    }
    
    public static String getManaHTML(ManaAmount manaAmount){
        String html = "";
        boolean isFirstMana = true;
        for(int i=0;i<Mana.values().length;i++){
            Mana mana = Mana.values()[i];
            int amount = manaAmount.getMana(mana);
            if(amount > 0){
                if(!isFirstMana){
                    html += ", ";
                }
                //Color manaColor = ManaAmount.getColor(mana);
                //html += "<span style=\"font-weight:bold; color:rgb(" + manaColor.getRed() + "," + manaColor.getGreen() + "," + manaColor.getBlue() + ");" + ((mana == Mana.WHITE)?" background:#000;":"") + "\">" + amount + "</span>";
                html += amount + " " + mana.toString().toLowerCase();
                isFirstMana = false;
            }
        }
        return html;
    }
    
    public boolean isFinished(){
        return remainingAmount.isEmpty();
    }
    
    @Override
    public void stop(){
        super.stop();
        remainingAmount = null;
    }

    public ManaChooseType getType(){
        return type;
    }

    public int[] getChooseInformation(){
        return chooseInformation;
    }

    public ManaSpell[] getManaSpells(){
        return manaSpells.toArray(new ManaSpell[manaSpells.size()]);
    }
}
