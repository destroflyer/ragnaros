/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Konda extends MonsterCard{

    public Konda(){
        description = new Description("Konda, Lord of Eiganjo", "Indestructible");
        manaTypes = new Mana[]{Mana.WHITE};
        castCost = new Cost(new ManaAmount(new int[]{0, 5, 0, 0, 0, 0}));
        attackDamage = 3;
        setLifepoints(3);
    }

    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        super.preEvent(game, receivedEvent);
        if(receivedEvent instanceof AttackMonsterEvent){
            AttackMonsterEvent event = (AttackMonsterEvent) receivedEvent;
            if((event.getAttackingMonsterCard() == this) || (event.getTargetMonsterCard() == this)){
                
            }
        }
        else if(receivedEvent instanceof DestroyMonsterEvent){
            DestroyMonsterEvent event = (DestroyMonsterEvent) receivedEvent;
            if(event.getMonsterCard() == this){
                return false;
            }
        }
        return true;
    }
}
