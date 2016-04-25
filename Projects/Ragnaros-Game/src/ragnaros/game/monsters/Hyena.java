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
public class Hyena extends MonsterCard{

    public Hyena(){
        description = new Description("Hyena");
        isCollectible = false;
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 2));
        attackDamage = 2;
        setLifepoints(2);
    }
}
