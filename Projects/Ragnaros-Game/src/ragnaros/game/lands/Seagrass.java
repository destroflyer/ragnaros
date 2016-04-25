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
public class Seagrass extends SimpleManaLand{

    public Seagrass(){
        super(Mana.BLUE, Mana.GREEN);
        description = new Description("Seagrass");
    }
}
