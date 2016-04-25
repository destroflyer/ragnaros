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
public class ForestOgre extends MonsterCard{

    public ForestOgre(){
        description = new Description("Forest Ogre");
        flavorText = "\"Please bring me 3 trees.\"";
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.OGRE};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 6));
        attackDamage = 6;
        setLifepoints(6);
    }
}
