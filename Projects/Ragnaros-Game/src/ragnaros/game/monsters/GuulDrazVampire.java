/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class GuulDrazVampire extends MonsterCard{

    public GuulDrazVampire(){
        description = new Description("Guul Draz Vampire", "As long as your opponent has 10 or less life, this monster has +2/+1.");
        flavorText = "\"A creature's bloodscent is a beacon that cannot be disguised.\"";
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(Mana.BLACK, 1));
        attackDamage = 1;
        setLifepoints(1);
    }
    private Buff buff = new CompositeBuff(new AdditiveAttackDamageBuff(2), new AdditiveMaximumHealthBuff(1));

    @Override
    public void onCast(){
        super.onCast();
        updateBuff();
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof AddPlayerLifepointsEvent){
            AddPlayerLifepointsEvent event = (AddPlayerLifepointsEvent) receivedEvent;
            if(event.getPlayer() == owner.getEnemy()){
                updateBuff();
            }
        }
    }
    
    private void updateBuff(){
        boolean shouldHaveBuff = (owner.getEnemy().getLifepoints() <= 10);
        boolean hasBuff = hasBuff(buff);
        if(hasBuff != shouldHaveBuff){
            if(shouldHaveBuff){
                game.triggerEvent(new AddBuffEvent(this, buff, this));
            }
            else{
                game.triggerEvent(new RemoveBuffEvent(this, buff));
            }
        }
    }
}
