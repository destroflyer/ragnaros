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
public class InjuredBlademaster extends MonsterCard{

    public InjuredBlademaster(){
        description = new Description("Injured Blademaster");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 3));
        attackDamage = 4;
        setLifepoints(7);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Deal 4 damage to this minion.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(InjuredBlademaster.this, -4));
            }
        });
    }
}
