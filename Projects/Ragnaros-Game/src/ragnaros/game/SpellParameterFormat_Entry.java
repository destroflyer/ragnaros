/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public class SpellParameterFormat_Entry{

    public SpellParameterFormat_Entry(String description, Type type, Owner targetOwner, CardFilter[] cardFilters){
        this.description = description;
        this.type = type;
        this.targetOwner = targetOwner;
        this.cardFilters = cardFilters;
    }
    public enum Type{
        ALL,
        HAND_CARD,
        LAND,
        MONSTER
    }
    public enum Owner{
        ALL,
        ALLIED,
        ENEMY
    }
    private String description;
    private Type type;
    private Owner targetOwner;
    private CardFilter[] cardFilters;
    
    public boolean isValid(Spell spell, Card card){
        boolean isValid = true;
        switch(targetOwner){
            case ALLIED:
                isValid = (card.getOwner() == spell.getCaster().getOwner());
                break;

            case ENEMY:
                isValid = (card.getOwner() != spell.getCaster().getOwner());
                break;
        }
        if(isValid){
            switch(type){
                case LAND:
                    isValid = ((card.getCardPosition().getZone() == CardPosition.Zone.LAND) && (card instanceof Land));
                    break;

                case MONSTER:
                    isValid = ((card.getCardPosition().getZone() == CardPosition.Zone.MONSTER) && (card instanceof MonsterCard));
                    break;

                case HAND_CARD:
                    isValid = ((card.getCardPosition().getZone() == CardPosition.Zone.HAND) && (card != spell.getCaster()));
                    break;
            }
            if(isValid){
                for(CardFilter cardFilter : cardFilters){
                    if(!cardFilter.isValid(card)){
                        isValid = false;
                        break;
                    }
                }
            }
        }
        return isValid;
    }

    public String getDescription(){
        return description;
    }

    public Type getType(){
        return type;
    }

    public Owner getTargetOwner(){
        return targetOwner;
    }

    public CardFilter[] getCardFilters(){
        return cardFilters;
    }
}
