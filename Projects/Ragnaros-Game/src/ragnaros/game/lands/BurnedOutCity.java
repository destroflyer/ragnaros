/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;
import ragnaros.game.spells.*;

/**
 *
 * @author Carl
 */
public class BurnedOutCity extends Land{

    public BurnedOutCity(){
        description = new Description("Burned Out City");
        manaTypes = new Mana[]{Mana.RED};
        setSpells(new SimpleGainManaSpell(new ManaAmount(Mana.RED, 2)){{
                description = new Description(Description.DEFAULT_TITLE, "Pay 6 health.");
                cost = new Cost(new ManaAmount(), true, 6);
            }
        });
    }
}
