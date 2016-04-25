/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.auras.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;
import ragnaros.game.mechanics.*;
import ragnaros.game.spells.*;

/**
 *
 * @author Carl
 */
public class Dog1 extends MonsterCard{

    public Dog1(){
        description = new Description("Dog #1");
        flavorText = "Such first.";
        isCollectible = false;
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
        attackDamage = 1;
        setLifepoints(1);
        hasCharge = true;
    }
}
