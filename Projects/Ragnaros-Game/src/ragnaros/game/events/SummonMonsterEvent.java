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
public class SummonMonsterEvent extends CastFieldCardEvent{

    public SummonMonsterEvent(Player player, MonsterCard monsterCard){
        super(player, monsterCard);
    }

    @Override
    public void trigger(Game game){
        player.getMonsters().add(fieldCard);
        super.trigger(game);
        MonsterCard monsterCard = (MonsterCard) fieldCard;
        monsterCard.setIsExhausted(!monsterCard.hasCharge());
    }
    
    public MonsterCard getMonsterCard(){
        return (MonsterCard) fieldCard;
    }
}
