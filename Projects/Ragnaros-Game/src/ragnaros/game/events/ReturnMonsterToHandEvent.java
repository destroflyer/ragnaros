/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.events;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class ReturnMonsterToHandEvent extends ReturnToHandEvent{

    public ReturnMonsterToHandEvent(MonsterCard monsterCard){
        super(monsterCard);
    }

    @Override
    public void trigger(Game game){
        card.getOwner().getMonsters().remove(card);
        super.trigger(game);
    }
}
