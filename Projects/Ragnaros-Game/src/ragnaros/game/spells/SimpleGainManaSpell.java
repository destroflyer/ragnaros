/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spells;

import ragnaros.game.*;

/**
 *
 * @author carl
 */
public class SimpleGainManaSpell extends ManaSpell{

    public SimpleGainManaSpell(ManaAmount manaAmount){
        this.manaAmount = manaAmount;
        String title = "Gain ";
        Mana[] usedManaTypes = manaAmount.getUsedManaTypes();
        for(int i=0;i<usedManaTypes.length;i++){
            if(i != 0){
                title += ", ";
            }
            title += (manaAmount.getMana(usedManaTypes[i]) + " " + usedManaTypes[i].name().substring(0, 1).toUpperCase() + usedManaTypes[i].name().substring(1).toLowerCase());
        }
        title += " Mana";
        description = new Description(title);
        
    }
    private ManaAmount manaAmount;

    @Override
    public ManaAmount getGainedManaAmount(){
        return manaAmount;
    }
}
