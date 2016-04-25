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
public class Tibbers extends MonsterCard{

    public Tibbers(){
        description = new Description("Tibbers");
        flavorText = "\"ROAR!\"";
        isCollectible = false;
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.RED, 3));
        attackDamage = 5;
        setLifepoints(5);
        addMechanics(new Taunt());
    }
}
