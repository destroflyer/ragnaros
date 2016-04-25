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
public class ElvishGirl extends MonsterCard{

    public ElvishGirl(){
        description = new Description("Elvish Girl");
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.ELF};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 1));
        attackDamage = 1;
        setLifepoints(2);
    }
}
