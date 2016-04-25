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
public class Shyvonna extends MonsterCard{

    public Shyvonna(){
        description = new Description("Shyvonna");
        flavorText = "Stoopid.";
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.DRAGON};
        attackDamage = 0;
        setLifepoints(20);
    }
}
