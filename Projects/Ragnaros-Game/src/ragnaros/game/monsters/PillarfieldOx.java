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
public class PillarfieldOx extends MonsterCard{

    public PillarfieldOx(){
        description = new Description("Pillarfield Ox");
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 3));
        attackDamage = 2;
        setLifepoints(4);
    }
}
