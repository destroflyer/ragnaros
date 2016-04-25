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
public class Ghoul extends MonsterCard{

    public Ghoul(){
        description = new Description("Ghoul");
        flavorText = "Boo!";
        isCollectible = false;
        manaTypes = new Mana[]{Mana.BLACK};
        attackDamage = 1;
        setLifepoints(1);
    }
}
