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
public class Safcon extends MonsterCard{

    public Safcon(){
        description = new Description("Safcon");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.POKEMON, Tribe.BUG};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 2));
        attackDamage = 0;
        setLifepoints(5);
    }
}
