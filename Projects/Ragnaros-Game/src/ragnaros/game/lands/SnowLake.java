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
public class SnowLake extends SimpleManaLand{

    public SnowLake(){
        super(Mana.WHITE, Mana.BLUE);
        description = new Description("Snow Lake");
    }
}
