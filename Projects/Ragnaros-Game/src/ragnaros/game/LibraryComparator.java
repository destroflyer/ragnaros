/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import java.util.Comparator;

/**
 *
 * @author Carl
 */
public class LibraryComparator implements Comparator<Card>{
    
    @Override
    public int compare(Card card1, Card card2){
        byte manaTypes1 = 0;
        byte manaTypes2 = 0;
        for(Mana mana : card1.getManaTypes()){
            manaTypes1 |= (1 << mana.ordinal());
        }
        for(Mana mana : card2.getManaTypes()){
            manaTypes2 |= (1 << mana.ordinal());
        }
        if(manaTypes1 == manaTypes2){
            int typeScore1 = getTypeScore(card1);
            int typeScore2 = getTypeScore(card2);
            if(typeScore1 == typeScore2){
                int manaCostAmount1 = getManaCostAmount(card1);
                int manaCostAmount2 = getManaCostAmount(card2);
                if(manaCostAmount1 == manaCostAmount2){
                    String title1 = card1.getDescription().getTitle();
                    String title2 = card2.getDescription().getTitle();
                    return title1.compareToIgnoreCase(title2);
                }
                return getResult(manaCostAmount1 < manaCostAmount2);
            }
            return getResult(typeScore1 < typeScore2);
        }
        return getResult(manaTypes1 < manaTypes2);
    }

    private int getTypeScore(Card card){
        if(card instanceof Land){
            return 0;
        }
        else if(card instanceof FieldCard){
            return 1;
        }
        else if(card instanceof SpellCard){
            return 2;
        }
        return 3;
    }

    private int getManaCostAmount(Card card){
        if(card instanceof FieldCard){
            FieldCard fieldCard = (FieldCard) card;
            return fieldCard.getCastCost().getMana().getAmount();
        }
        else if(card instanceof SpellCard){
            SpellCard spellCard = (SpellCard) card;
            return spellCard.getSpell().getCost().getMana().getAmount();
        }
        return 0;
    }

    private int getResult(boolean comparison){
        return (comparison?-1:1);
    }
}
