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
public class AddMonsterAttackDamageEvent extends Event{

    public AddMonsterAttackDamageEvent(MonsterCard monsterCard, int attackDamage){
        this.monsterCard = monsterCard;
        this.attackDamage = attackDamage;
    }
    protected MonsterCard monsterCard;
    protected int attackDamage;

    @Override
    public void trigger(Game game){
        monsterCard.setAttackDamage(monsterCard.getAttackDamage() + attackDamage);
    }

    public MonsterCard getMonsterCard(){
        return monsterCard;
    }

    public int getAttackDamage(){
        return attackDamage;
    }
}
