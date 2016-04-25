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
public class TabForManaSpell extends SimpleGainManaSpell{

    public TabForManaSpell(ManaAmount manaAmount){
        super(manaAmount);
        cost = new Cost(new ManaAmount(), true);
    }
}
