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
public class Pinsir extends MonsterCard{

    public Pinsir(){
        description = new Description("Pinsir");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.POKEMON, Tribe.BUG};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 2));
        attackDamage = 3;
        setLifepoints(2);
    }
}
