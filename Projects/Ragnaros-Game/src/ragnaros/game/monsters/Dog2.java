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
public class Dog2 extends MonsterCard{

    public Dog2(){
        description = new Description("Dog #2");
        flavorText = "Very second.";
        isCollectible = false;
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
        attackDamage = 1;
        setLifepoints(1);
        hasCharge = true;
    }
}
