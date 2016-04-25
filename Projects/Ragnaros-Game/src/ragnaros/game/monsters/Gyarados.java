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
public class Gyarados extends MonsterCard{

    public Gyarados(){
        description = new Description("Gyarados");
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.POKEMON, Tribe.FISH};
        attackDamage = 9;
        setLifepoints(7);
    }
}
