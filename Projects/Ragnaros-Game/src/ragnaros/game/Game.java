/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.network.BitSerializable;
import ragnaros.network.BitOutputStream;
import ragnaros.network.BitInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import ragnaros.core.Util;
import ragnaros.game.commands.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Game implements BitSerializable{

    public Game(boolean isServer){
        this.isServer = isServer;
    }
    private CardPool cardPool = new CardPool();
    private boolean isServer;
    private GameMode gameMode;
    private Player[] players;
    private int currentPlayerIndex;
    private HashMap<Integer, Card> cards = new HashMap<Integer, Card>();
    private int nextCardID;
    private ArrayList<EventListener> eventListeners = new ArrayList<EventListener>();
    private Stack<LinkedList<EventListener>> currentEventHandles = new Stack<LinkedList<EventListener>>();
    private boolean isRunning;

    public void setGameMode(GameMode gameMode){
        gameMode.setGame(this);
        this.gameMode = gameMode;
        addEventListener(gameMode);
    }

    public GameMode getGameMode(){
        return gameMode;
    }

    public void setPlayers(Player player1, Player player2){
        player1.setGame(this);
        player2.setGame(this);
        players = new Player[]{player1, player2};
    }
    
    public int getIndex(Player player){
        return ((player == players[0])?0:1);
    }
    
    public Player getPlayer(int index){
        return players[index];
    }
    
    public Player getEnemy(Player player){
        return ((player == players[0])?players[1]:players[0]);
    }

    public int getCurrentPlayerIndex(){
        return currentPlayerIndex;
    }

    public Player getCurrentPlayer(){
        return players[currentPlayerIndex];
    }

    public Collection<Card> getCards(){
        return cards.values();
    }
    
    public Card getCard(int id){
        return cards.get(id);
    }
    
    public void registerCard(Card card){
        if(isServer){
            card.setID(nextCardID);
            nextCardID++;
            cards.put(card.getID(), card);
        }
    }
    
    public void startGame(){
        isRunning = true;
        gameMode.preparePlayer(players[0]);
        gameMode.preparePlayer(players[1]);
        startTurn();
    }
    
    public void executeCommand(Command receivedCommand){
        Player currentPlayer = getCurrentPlayer();
        if(receivedCommand instanceof CastFieldCardCommand){
            CastFieldCardCommand command = (CastFieldCardCommand) receivedCommand;
            FieldCard fieldCard = (FieldCard) currentPlayer.getHand().get(command.getHandCardIndex());
            if(currentPlayer.canPay(fieldCard.getCastCost(), fieldCard) && ((!(fieldCard instanceof Land)) || currentPlayer.canAddLand())){
                if(triggerEvent(new PayCostsEvent(currentPlayer, fieldCard.getCastCost(), fieldCard))){
                    if((fieldCard.getCastSpell() != null) && (fieldCard.isCastSpellObligatory || (command.getSpellParameters() != null))){
                        triggerEvent(new CastSpellEvent(fieldCard.getCastSpell(), command.getSpellParameters()));
                    }
                    if(fieldCard instanceof Land){
                        triggerEvent(new AddHandLandEvent(currentPlayer, (Land) fieldCard));
                    }
                    else if(fieldCard instanceof MonsterCard){
                        triggerEvent(new SummonHandMonsterEvent(currentPlayer, (MonsterCard) fieldCard));
                    }
                }
            }
        }
        else if(receivedCommand instanceof CastFieldCardSpellCommand){
            CastFieldCardSpellCommand command = (CastFieldCardSpellCommand) receivedCommand;
            Card card = currentPlayer.getCard(command.getCardPosition());
            if(card instanceof FieldCard){
                FieldCard fieldCard = (FieldCard) card;
                if(Util.containsArrayIndex(fieldCard.getSpells(), command.getSpellIndex())){
                    Spell spell = fieldCard.getSpells()[command.getSpellIndex()];
                    if(currentPlayer.canCast(spell)){
                        if(triggerEvent(new PayCostsEvent(currentPlayer, spell.getCost(), fieldCard))){
                            triggerEvent(new CastSpellEvent(spell, command.getSpellParameters()));
                        }
                    }
                }
            }
        }
        else if(receivedCommand instanceof AttackMonsterCommand){
            AttackMonsterCommand command = (AttackMonsterCommand) receivedCommand;
            MonsterCard monsterCard = (MonsterCard) currentPlayer.getMonsters().get(command.getMonsterCardIndex());
            MonsterCard targetMonsterCard = (MonsterCard) currentPlayer.getEnemy().getMonsters().get(command.getTargetMonsterCardIndex());
            if((monsterCard != null) && monsterCard.canAttack(targetMonsterCard)){
                if(triggerEvent(new PayCostsEvent(currentPlayer, monsterCard.getAttackCost(), monsterCard))){
                    triggerEvent(new AttackMonsterEvent(monsterCard, targetMonsterCard));
                }
            }
        }
        else if(receivedCommand instanceof AttackPlayerCommand){
            AttackPlayerCommand command = (AttackPlayerCommand) receivedCommand;
            MonsterCard monsterCard = (MonsterCard) currentPlayer.getMonsters().get(command.getMonsterCardIndex());
            if((monsterCard != null) && currentPlayer.getEnemy().getMonsters().isEmpty() && monsterCard.canAttack()){
                if(triggerEvent(new PayCostsEvent(currentPlayer, monsterCard.getAttackCost(), monsterCard))){
                    triggerEvent(new AttackPlayerEvent(monsterCard, currentPlayer.getEnemy()));
                }
            }
        }
        else if(receivedCommand instanceof CastSpellCommand){
            CastSpellCommand command = (CastSpellCommand) receivedCommand;
            SpellCard spellCard = (SpellCard) currentPlayer.getHand().get(command.getHandCardIndex());
            if((spellCard != null) && currentPlayer.canCast(spellCard.getSpell())){
                if(triggerEvent(new PayCostsEvent(currentPlayer, spellCard.getSpell().getCost(), spellCard))){
                    triggerEvent(new CastSpellCardEvent(spellCard, command.getSpellParameters()));
                }
            }
        }
        else if(receivedCommand instanceof EndTurnCommand){
            EndTurnCommand command = (EndTurnCommand) receivedCommand;
            checkTurnEndHand();
        }
        else if(receivedCommand instanceof ThrowingOffCommand){
            ThrowingOffCommand command = (ThrowingOffCommand) receivedCommand;
            triggerEvent(new DiscardEvent(currentPlayer, command.getHandCardIndex()));
            checkTurnEndHand();
        }
    }
    
    public boolean triggerEvent(Event event){
        boolean triggerEvent = true;
        LinkedList<EventListener> currentHandlingEventListeners = new LinkedList<EventListener>();
        currentHandlingEventListeners.addAll(eventListeners);
        currentEventHandles.push(currentHandlingEventListeners);
        for(EventListener eventListener : currentHandlingEventListeners){
            boolean allowEvent = eventListener.preEvent(this, event);
            if(!allowEvent){
                triggerEvent = false;
                break;
            }
        }
        if(triggerEvent){
            event.trigger(this);
            while(currentHandlingEventListeners.size() > 0){
                EventListener eventListener = currentHandlingEventListeners.pop();
                eventListener.postEvent(this, event);
            }
        }
        currentEventHandles.pop();
        return triggerEvent;
    }
    
    private void checkTurnEndHand(){
        Player currentPlayer = getCurrentPlayer();
        if(currentPlayer.getHand().size() <= gameMode.getMaximumHandCards(currentPlayer)){
            triggerEvent(new TurnEndEvent(currentPlayer));
        }
    }
    
    public void nextTurn(){
        currentPlayerIndex = ((currentPlayerIndex == 0)?1:0);
        startTurn();
    }
    
    private void startTurn(){
        Player currentPlayer = getCurrentPlayer();
        triggerEvent(new TurnStartEvent(currentPlayer));
    }
    
    public void addEventListener(EventListener eventListener){
        if(!eventListeners.contains(eventListener)){
            eventListeners.add(eventListener);
        }
    }
    
    public void removeEventListener(EventListener eventListener){
        for(LinkedList<EventListener> currentHandlingEventListeners : currentEventHandles){
            currentHandlingEventListeners.remove(eventListener);
        }
        eventListeners.remove(eventListener);
    }
    
    public void onGameOver(){
        isRunning = false;
    }

    public boolean isRunning(){
        return isRunning;
    }
    
    @Override
    public void write(BitOutputStream outputStream){
        outputStream.writeBits(cards.size(), 10);
        for(int cardID : cards.keySet()){
            Card card = cards.get(cardID);
            outputStream.writeBits(cardID, 10);
            outputStream.writeBits(CardTypeManager.getType(card), 10);
            card.write(outputStream);
        }
        players[0].write(outputStream);
        players[1].write(outputStream);
        outputStream.writeBits(currentPlayerIndex, 1);
        outputStream.writeBoolean(isRunning);
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException{
        boolean tmp_IsServer = isServer;
        int cardsSize = inputStream.readBits(10);
        if(tmp_IsServer){
            isServer = false;
            cards.clear();
        }
        int maximumID = -1;
        for(int i=0;i<cardsSize;i++){
            int cardID = inputStream.readBits(10);
            int cardType = inputStream.readBits(10);
            Card card = getCard(cardID);
            if(card == null){
                if(tmp_IsServer){
                    card = cardPool.getCard(cardType, cardID);
                }
                else{
                    card = CardTypeManager.createCard(cardType);
                }
                card.setGame(this);
                card.setID(cardID);
                cards.put(cardID, card);
            }
            card.read(inputStream);
            if(cardID > maximumID){
                maximumID = cardID;
            }
        }
        players[0].read(inputStream);
        players[1].read(inputStream);
        currentPlayerIndex = inputStream.readBits(1);
        isRunning = inputStream.readBoolean();
        isServer = tmp_IsServer;
    }
}
