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
public class MonasterySwiftspear extends MonsterCard{

    public MonasterySwiftspear(){
        description = new Description("Monastery Swiftspear");
        manaTypes = new Mana[]{Mana.RED};
        castCost = new Cost(new ManaAmount(Mana.RED, 1));
        attackDamage = 2;
        setLifepoints(1);
    }
}
