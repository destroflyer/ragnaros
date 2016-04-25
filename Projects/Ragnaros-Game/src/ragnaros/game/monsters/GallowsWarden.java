/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.auras.*;
import ragnaros.game.buffs.*;

/**
 *
 * @author Carl
 */
public class GallowsWarden extends MonsterCard{

    public GallowsWarden(){
        description = new Description("Gallows Warden");
        manaTypes = new Mana[]{Mana.WHITE};
        castCost = new Cost(new ManaAmount(new int[]{3, 1, 0, 0, 0, 0}));
        attackDamage = 3;
        setLifepoints(3);
        addMechanics(new AdjacentMinionsAura(new AdditiveMaximumHealthBuff(1)){{
                description = new Description(Description.DEFAULT_TITLE, "Other friendly monsters have +1 health.");
            }
            
            @Override
            public boolean isActive(){
                return (cardPosition.getZone() == CardPosition.Zone.MONSTER);
            }
        });
    }
}
