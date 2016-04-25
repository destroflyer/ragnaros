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
public class DireWolfAlpha extends MonsterCard{

    public DireWolfAlpha(){
        description = new Description("Dire Wolf Alpha");
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
        attackDamage = 2;
        setLifepoints(2);
        addMechanics(new AdjacentMinionsAura(new AdditiveAttackDamageBuff(1)){{
                description = new Description(Description.DEFAULT_TITLE, "Adjacent monsters have +1 attack.");
            }
            
            @Override
            public boolean isActive(){
                return (cardPosition.getZone() == CardPosition.Zone.MONSTER);
            }
        });
    }
}
