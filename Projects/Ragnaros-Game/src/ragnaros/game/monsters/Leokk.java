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
public class Leokk extends MonsterCard{

    public Leokk(){
        description = new Description("Leokk");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 3));
        attackDamage = 2;
        setLifepoints(4);
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
