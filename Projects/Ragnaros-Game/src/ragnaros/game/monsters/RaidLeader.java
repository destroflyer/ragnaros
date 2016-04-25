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
public class RaidLeader extends MonsterCard{

    public RaidLeader(){
        description = new Description("Raid Leader");
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.WARRIOR};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 3));
        attackDamage = 2;
        setLifepoints(2);
        addMechanics(new OtherFriendlyMinionsAura(new AdditiveAttackDamageBuff(1)){{
                description = new Description(Description.DEFAULT_TITLE, "Other friendly monsters have +1 attack.");
            }
            
            @Override
            public boolean isActive(){
                return (cardPosition.getZone() == CardPosition.Zone.MONSTER);
            }
        });
    }
}
