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
public class MonsterTribeCardFilter extends ClassCardFilter{

    public MonsterTribeCardFilter(MonsterCard.Tribe tribe){
        super(MonsterCard.class);
        this.tribe = tribe;
    }
    private MonsterCard.Tribe tribe;

    @Override
    public boolean isValid(Card card){
        if(super.isValid(card)){
            MonsterCard monsterCard = (MonsterCard) card;
            return monsterCard.hasTribe(tribe);
        }
        return false;
    }
}
