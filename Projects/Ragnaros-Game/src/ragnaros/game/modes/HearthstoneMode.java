/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.modes;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.lands.*;

/**
 *
 * @author Carl
 */
public class HearthstoneMode extends GameMode{

    @Override
    public void prepareCard(Card card){
        if(card instanceof MonsterCard){
            MonsterCard monsterCard = (MonsterCard) card;
            monsterCard.setCastCost(generateMergedCost(monsterCard.getCastCost()));
            for(int i=0;i<monsterCard.getSpells().length;i++){
                Spell spell = monsterCard.getSpells()[i];
                spell.setCost(generateMergedCost(spell.getCost()));
            }
        }
        else if(card instanceof SpellCard){
            SpellCard spellCard = (SpellCard) card;
            Spell spell = spellCard.getSpell();
            spell.setCost(generateMergedCost(spell.getCost()));
        }
    }
    
    private Cost generateMergedCost(Cost cost){
        ManaAmount newManaAmount = new ManaAmount(Mana.CUSTOM, cost.getMana().getAmount());
        return new Cost(newManaAmount, true, cost.getLifepoints());
    }

    @Override
    public void preparePlayer(Player player){
        player.setLifepoints(30);
        player.getDeck().shuffle();
        for(int i=0;i<3;i++){
            game.triggerEvent(new DrawEvent(player));
        }
    }
    
    @Override
    public void startTurn(Player player){
        game.triggerEvent(new DrawEvent(player));
        for(int i=0;i<player.getLands().size();i++){
            Land land = (Land) player.getLands().get(i);
            game.triggerEvent(new UntapEvent(land));
        }
        for(int i=0;i<player.getMonsters().size();i++){
            MonsterCard monsterCard = (MonsterCard) player.getMonsters().get(i);
            monsterCard.setIsExhausted(false);
            game.triggerEvent(new UntapEvent(monsterCard));
        }
        player.getAvailableMana().clear();
        Plains plains = new Plains();
        game.triggerEvent(new AddLandEvent(player, plains));
        player.setHasAddedLandThisTurn(true);
    }

    @Override
    public void attackMonster(MonsterCard attackingMonsterCard, MonsterCard targetMonsterCard){
        game.triggerEvent(new AddMonsterCurrentLifepointsEvent(targetMonsterCard, -1 * attackingMonsterCard.getAttackDamage(), attackingMonsterCard));
        game.triggerEvent(new AddMonsterCurrentLifepointsEvent(attackingMonsterCard, -1 * targetMonsterCard.getAttackDamage(), targetMonsterCard));
    }

    @Override
    public int getMaximumHandCards(Player player){
        return 10;
    }
    
    @Override
    public void endTurn(Player player){
        
    }

    public boolean preEvent(Game game, Event receivedEvent){
        return true;
    }

    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof DrawEvent){
            DrawEvent event = (DrawEvent) receivedEvent;
            Card drawnCard = event.getPlayer().getHand().getLast();
            if(drawnCard instanceof Land){
                game.triggerEvent(new DiscardEvent(drawnCard));
                game.triggerEvent(new DrawEvent(event.getPlayer()));
            }
        }
    }
}
