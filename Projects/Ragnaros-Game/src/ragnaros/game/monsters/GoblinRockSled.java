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
public class GoblinRockSled extends MonsterCard{

    public GoblinRockSled(){
        description = new Description("Goblin Rock Sled");
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.GOBLIN};
        castCost = new Cost(new ManaAmount(Mana.RED, 1, 1));
        attackDamage = 3;
        setLifepoints(1);
        addMechanics(new Trample());
    }
}
