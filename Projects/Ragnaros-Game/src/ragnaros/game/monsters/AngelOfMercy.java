/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class AngelOfMercy extends MonsterCard{

    public AngelOfMercy(){
        description = new Description("Angel of Mercy");
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.ANGEL};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 3));
        attackDamage = 3;
        setLifepoints(3);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Gain 3 lifepoints.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddPlayerLifepointsEvent(owner, 3));
            }
        });
    }
}
