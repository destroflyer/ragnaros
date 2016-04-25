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
public class GrizzlyBears extends MonsterCard{

    public GrizzlyBears(){
        description = new Description("Grizzly Bears");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(new int[]{1, 0, 0, 1, 0, 0}));
        attackDamage = 2;
        setLifepoints(2);
    }
}
