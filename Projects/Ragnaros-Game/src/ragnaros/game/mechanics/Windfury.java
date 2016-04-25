/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.mechanics;

import ragnaros.game.Description;

/**
 *
 * @author Carl
 */
public class Windfury extends MultipleAttacksMechanic{

    public Windfury(){
        super(2);
        description = new Description("Windfury");
    }
}
