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
public abstract class CardChooserManager{

    public CardChooserManager(){
        manaChooser = new ManaChooser(this);
        spellParameterChooser = new SpellParameterChooser(this);
        attackTargetChooser = new AttackTargetChooser(this);
        throwingOffChooser = new ThrowingOffChooser(this);
    }
    protected CardChooser activeCardChooser;
    private ManaChooser manaChooser;
    private SpellParameterChooser spellParameterChooser;
    private AttackTargetChooser attackTargetChooser;
    private ThrowingOffChooser throwingOffChooser;
    
    public void startChoosing_FieldCardCastCost(int playerIndex, FieldCard fieldCard, ManaAmount neededMana){
        manaChooser.start(ManaChooser.ManaChooseType.FIELD_CARD_CAST_COST, playerIndex, new int[]{fieldCard.getCardPosition().getIndex()}, neededMana);
        start(manaChooser);
    }
    
    public void startChoosing_FieldCardSpellCost(int playerIndex, Spell spell, int spellIndex, ManaAmount neededMana){
        CardPosition cardPosition = spell.getCaster().getCardPosition();
        manaChooser.start(ManaChooser.ManaChooseType.FIELD_CARD_SPELL_COST, playerIndex, new int[]{cardPosition.getZone().ordinal(), cardPosition.getIndex(), spellIndex}, neededMana);
        start(manaChooser);
    }
    
    public void startChoosing_SpellCost(int playerIndex, Spell spell, ManaAmount neededMana){
        CardPosition cardPosition = spell.getCaster().getCardPosition();
        manaChooser.start(ManaChooser.ManaChooseType.SPELL_COST, playerIndex, new int[]{cardPosition.getIndex()}, neededMana);
        start(manaChooser);
    }
    
    public void startChoosing_FieldCardCastSpellParameter(Spell spell, ManaSpell[] spell_ManaSpells){
        spellParameterChooser.start(SpellParameterChooser.SpellChooseType.FIELD_CARD_CAST_SPELL, spell, spell_ManaSpells);
        start(spellParameterChooser);
    }
    
    public void startChoosing_FieldCardSpellParameter(Spell spell, ManaSpell[] spell_ManaSpells){
        spellParameterChooser.start(SpellParameterChooser.SpellChooseType.FIELD_CARD_SPELL, spell, spell_ManaSpells);
        start(spellParameterChooser);
    }
    
    public void startChoosing_SpellParameter(Spell spell, ManaSpell[] spell_ManaSpells){
        spellParameterChooser.start(SpellParameterChooser.SpellChooseType.SPELL, spell, spell_ManaSpells);
        start(spellParameterChooser);
    }
    
    public void startChoosing_AttackTarget(int playerIndex, int attack_MonsterCardIndex){
        attackTargetChooser.start(playerIndex, attack_MonsterCardIndex);
        start(attackTargetChooser);
    }
    
    public void startChoosing_ThrowingOff(int playerIndex){
        throwingOffChooser.start(playerIndex);
        start(throwingOffChooser);
    }
    
    protected void start(CardChooser cardChooser){
        activeCardChooser = cardChooser;
    }

    public void stop(){
        activeCardChooser.stop();
        activeCardChooser = null;
        setMessage(null);
    }

    public CardChooser getActiveCardChooser(){
        return activeCardChooser;
    }
    
    public abstract void setMessage(String text);
}
