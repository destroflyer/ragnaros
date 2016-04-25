/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import ragnaros.game.*;
import ragnaros.game.monsters.spells.parameter.*;

/**
 *
 * @author Carl
 */
public class SpellParameterChooser extends CardChooser{

    public SpellParameterChooser(CardChooserManager cardChooserManager){
        super(cardChooserManager);
    }
    public enum SpellChooseType{
        FIELD_CARD_CAST_SPELL,
        FIELD_CARD_SPELL,
        SPELL
    }
    private SpellChooseType type;
    private Spell spell;
    private int currentParameterIndex;
    private SpellParameter[] spellParameters;
    private ManaSpell[] spell_ManaSpells;

    public void start(SpellChooseType type, Spell spell, ManaSpell[] spell_ManaSpells){
        this.type = type;
        this.spell = spell;
        this.spell_ManaSpells = spell_ManaSpells;
        currentParameterIndex = 0;
        spellParameters = new SpellParameter[spell.getSpellParameterFormat().size()];
        updateMessage();
    }
    
    @Override
    public boolean isValidChoice(Card card){
        if(card != null){
            SpellParameterFormat_Entry formatEntry = spell.getSpellParameterFormat().getEntry(currentParameterIndex);
            return formatEntry.isValid(spell, card);
        }
        return false;
    }
    
    public void setNextParameter(Card card){
        SpellParameterFormat_Entry formatEntry = spell.getSpellParameterFormat().getEntry(currentParameterIndex);
        SpellParameter spellParameter = null;
        switch(formatEntry.getType()){
            case LAND:
                spellParameter = new LandParameter(card.getOwner().getIndex(), card.getCardPosition().getIndex());
                break;
            
            case MONSTER:
                spellParameter = new MonsterParameter(card.getOwner().getIndex(), card.getCardPosition().getIndex());
                break;
            
            case HAND_CARD:
                spellParameter = new HandCardParameter(card.getOwner().getIndex(), card.getCardPosition().getIndex());
                break;
        }
        spellParameters[currentParameterIndex] = spellParameter;
        currentParameterIndex++;
        if(!isFinished()){
            updateMessage();
        }
    }
    
    private void updateMessage(){
        if(spell.getSpellParameterFormat().size() > 0){
            SpellParameterFormat_Entry formatEntry = spell.getSpellParameterFormat().getEntry(currentParameterIndex);
            cardChooserManager.setMessage(formatEntry.getDescription());
        }
    }
    
    public boolean isFinished(){
        return (currentParameterIndex >= spell.getSpellParameterFormat().size());
    }
    
    @Override
    public void stop(){
        super.stop();
        spell = null;
    }

    public SpellChooseType getType(){
        return type;
    }

    public Spell getSpell(){
        return spell;
    }

    public ManaSpell[] getSpell_ManaSpells(){
        return spell_ManaSpells;
    }
    
    public SpellParameter[] getSpellParameters(){
        return spellParameters;
    }
}
