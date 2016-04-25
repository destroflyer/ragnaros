/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public class SpellCard extends Card{

    public SpellCard(Spell spell){
        this.spell = spell;
        spell.setCaster(this);
        description = spell.getDescription();
        ManaAmount manaCost = spell.getCost().getMana();
        if(!manaCost.isEmpty()){
            manaTypes = manaCost.getUsedManaTypes(false);
            if(manaTypes.length == 0){
                manaTypes = new Mana[]{Mana.CUSTOM};
            }
        }
    }
    private Spell spell;

    public Spell getSpell(){
        return spell;
    }
}
