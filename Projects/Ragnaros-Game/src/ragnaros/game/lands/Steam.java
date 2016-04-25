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
public class Steam extends SimpleManaLand{

    public Steam(){
        super(Mana.BLUE, Mana.RED);
        description = new Description("Steam");
    }
}
