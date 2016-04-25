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
public class Saproling extends MonsterCard{

    public Saproling(){
        description = new Description("Saproling");
        isCollectible = false;
        manaTypes = new Mana[]{Mana.GREEN};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 1));
        attackDamage = 1;
        setLifepoints(1);
    }
}
