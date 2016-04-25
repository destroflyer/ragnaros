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
public class IllidanStormrage extends MonsterCard{

    public IllidanStormrage(){
        description = new Description("Illidan Stormrage", "Whenever you play a card, summon a 2/1 Flame of Azzinoth.");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 6));
        attackDamage = 7;
        setLifepoints(5);
    }

    /*@Override
    public void onCast(){
        super.onCast();
        new OggClip("/cardgame/resources/sounds/illidan_stormrage_summon.ogg").play();
    }*/

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(cardPosition.getZone() == CardPosition.Zone.MONSTER){
            if(receivedEvent instanceof AddLandEvent){
                AddLandEvent event = (AddLandEvent) receivedEvent;
                if(event.getPlayer() == owner){
                    spawnToken();
                }
            }
            else if(receivedEvent instanceof SummonHandMonsterEvent){
                SummonHandMonsterEvent event = (SummonHandMonsterEvent) receivedEvent;
                if((event.getPlayer() == owner) && (event.getMonsterCard() != this)){
                    spawnToken();
                }
            }
            else if(receivedEvent instanceof CastSpellCardEvent){
                CastSpellCardEvent event = (CastSpellCardEvent) receivedEvent;
                if(event.getSpellCard().getOwner() == owner){
                    spawnToken();
                }
            }
        }
    }
    
    private void spawnToken(){
        FlameOfAzzinoth flameOfAzzinoth = new FlameOfAzzinoth();
        if(game.triggerEvent(new SummonMonsterEvent(owner, flameOfAzzinoth))){
            game.triggerEvent(new TapEvent(flameOfAzzinoth));
        }
    }
}
