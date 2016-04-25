/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class WaterDrake extends MonsterCard{

    public WaterDrake(){
        description = new Description("Water Drake");
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.DRAGON};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 5));
        attackDamage = 4;
        setLifepoints(7);
    }
}
