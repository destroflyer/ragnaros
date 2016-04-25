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
public class Zombie extends MonsterCard{

    public Zombie(){
        description = new Description("Zombie");
        flavorText = "\"...´\"";
        manaTypes = new Mana[]{Mana.BLACK};
        tribes = new Tribe[]{Tribe.ZOMBIE};
        castCost = new Cost(new ManaAmount(Mana.BLACK, 1));
        attackDamage = 1;
        setLifepoints(1);
    }
}
