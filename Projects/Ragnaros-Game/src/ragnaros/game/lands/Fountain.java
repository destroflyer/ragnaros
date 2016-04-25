/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.spells.*;

/**
 *
 * @author carl
 */
public class Fountain extends SlowCastLand{

    public Fountain(){
        description = new Description("Fountain");
        manaTypes = new Mana[]{Mana.BLUE};
        setSpells(
            new TabForManaSpell(new ManaAmount(Mana.BLUE, 2)),
            new TabForManaSpell(new ManaAmount(Mana.BLUE, 1)){{
                    description = new Description(Description.DEFAULT_TITLE, "Restore 2 lifepoints.");
                }

                @Override
                public void cast(Game game, SpellParameter[] parameters){
                    game.triggerEvent(new AddPlayerLifepointsEvent(owner, 2));
                    super.cast(game, parameters);
                }
            }
        );
    }
}
