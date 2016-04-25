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
public class PollutedRiver extends SimpleManaLand{

    public PollutedRiver(){
        super(Mana.BLACK, Mana.BLUE);
        description = new Description("Polluted River");
    }
}
