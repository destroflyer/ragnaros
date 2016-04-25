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
public class CloudySky extends SlowCastLand{

    public CloudySky(){
        description = new Description("Cloudy Sky");
        manaTypes = new Mana[]{Mana.CUSTOM};
        setSpells(new TabForManaSpell(new ManaAmount(Mana.CUSTOM, 2)));
    }
}
