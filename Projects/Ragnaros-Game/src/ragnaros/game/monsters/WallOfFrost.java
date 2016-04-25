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
public class WallOfFrost extends MonsterCard{

    public WallOfFrost(){
        description = new Description("Wall of Frost");
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.WALL};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 3));
        attackDamage = 0;
        setLifepoints(7);
        addMechanics(new Taunt());
    }
}
