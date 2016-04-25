/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.network.BitOutputStream;
import ragnaros.network.BitInputStream;
import java.io.IOException;

/**
 *
 * @author carl
 */
public class FieldCard extends Card{
    
    private boolean isTapped;
    protected Cost castCost = new Cost();
    private Spell castSpell;
    protected boolean isCastSpellObligatory;
    private Spell[] spells = new Spell[0];
    
    public void tap(){
        isTapped = true;
    }
    
    public void untap(){
        isTapped = false;
    }

    public boolean isTapped(){
        return isTapped;
    }

    public void setCastCost(Cost castCost){
        this.castCost = castCost;
    }

    public Cost getCastCost(){
        return castCost;
    }

    public void setCastSpell(Spell castSpell){
        castSpell.setCaster(this);
        this.castSpell = castSpell;
    }

    public Spell getCastSpell(){
        return castSpell;
    }

    public boolean isCastSpellObligatory(){
        return isCastSpellObligatory;
    }

    public void setSpells(Spell... spells){
        for(Spell spell : spells){
            spell.setCaster(this);
        }
        this.spells = spells;
    }

    public Spell[] getSpells(){
        return spells;
    }
    
    public void onCast(){
        
    }
    
    public void onDestroyed(){
        
    }

    @Override
    public void write(BitOutputStream outputStream){
        super.write(outputStream);
        outputStream.writeBoolean(isTapped);
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException{
        super.read(inputStream);
        isTapped = inputStream.readBoolean();
    }
}
