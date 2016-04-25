/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class BurningWoods extends SimpleManaLand{

    public BurningWoods(){
        super(Mana.RED, Mana.GREEN);
        description = new Description("Burning Woods");
    }
}
