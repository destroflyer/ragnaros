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
public class TrainedArmodon extends MonsterCard{

    public TrainedArmodon(){
        description = new Description("Trained Armodon");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 2, 1));
        attackDamage = 3;
        setLifepoints(3);
    }
}
