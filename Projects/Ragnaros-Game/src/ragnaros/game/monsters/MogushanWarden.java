/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class MogushanWarden extends MonsterCard{

    public MogushanWarden(){
        description = new Description("Mogu'shan Warden");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 4));
        attackDamage = 1;
        setLifepoints(7);
        addMechanics(new Taunt());
    }
}
