/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;
import ragnaros.game.spells.*;

/**
 *
 * @author carl
 */
public class SimpleManaLand extends Land{

    public SimpleManaLand(Mana... manaTypes){
        this.manaTypes = manaTypes;
        TabForManaSpell[] spells = new TabForManaSpell[manaTypes.length];
        for(int i=0;i<manaTypes.length;i++){
            spells[i] = new TabForManaSpell(new ManaAmount(manaTypes[i], 1));
        }
        setSpells(spells);
    }
}
