/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.spells.*;

/**
 *
 * @author Carl
 */
public class WildOcean extends Land{

    public WildOcean(){
        description = new Description("Wild Ocean");
        manaTypes = new Mana[]{Mana.BLUE};
        setSpells(new SimpleGainManaSpell(new ManaAmount(Mana.BLUE, 3)){{
                description = new Description(Description.DEFAULT_TITLE, "Destroy this card.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new DestroyLandEvent(WildOcean.this));
                super.cast(game, parameters);
            }
        });
    }
}
