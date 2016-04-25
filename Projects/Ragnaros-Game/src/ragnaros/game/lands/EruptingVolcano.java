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
public class EruptingVolcano extends Land{

    public EruptingVolcano(){
        description = new Description("Erupting Volcano");
        manaTypes = new Mana[]{Mana.RED};
        setSpells(new TabForManaSpell(new ManaAmount(Mana.RED, 1)){{
                description = new Description(Description.DEFAULT_TITLE, "Deal 1 damage to all players.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                for(int i=0;i<2;i++){
                    game.triggerEvent(new AddPlayerLifepointsEvent(game.getPlayer(i), -1));
                }
                super.cast(game, parameters);
            }
        });
    }
}
