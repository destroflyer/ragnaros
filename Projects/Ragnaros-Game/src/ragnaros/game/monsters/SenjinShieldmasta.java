/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class SenjinShieldmasta extends MonsterCard{

    public SenjinShieldmasta(){
        description = new Description("Sen'jin Shieldmasta");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 4));
        attackDamage = 3;
        setLifepoints(5);
        addMechanics(new Taunt());
    }
}
