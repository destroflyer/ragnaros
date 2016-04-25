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
public class FlameOfAzzinoth extends MonsterCard{

    public FlameOfAzzinoth(){
        description = new Description("Flame of Azzinoth");
        isCollectible = false;
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
        attackDamage = 2;
        setLifepoints(1);
    }
}
