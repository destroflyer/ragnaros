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
public class Dog4 extends MonsterCard{

    public Dog4(){
        description = new Description("Dog #4");
        flavorText = "Much doges.";
        isCollectible = false;
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
        attackDamage = 1;
        setLifepoints(1);
        hasCharge = true;
    }
}
