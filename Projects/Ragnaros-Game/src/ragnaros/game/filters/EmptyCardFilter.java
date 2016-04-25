/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.filters;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class EmptyCardFilter extends CardFilter{

    public EmptyCardFilter(){
        
    }

    @Override
    public boolean isValid(Card card){
        return true;
    }
}
