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
public class Fish extends MonsterCard{

    public Fish(){
        description = new Description("Fish");
        flavorText = "Blubb?";
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.FISH};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 1));
        attackDamage = 1;
        setLifepoints(1);
    }
}
