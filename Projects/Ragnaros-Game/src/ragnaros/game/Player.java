/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.network.BitSerializable;
import ragnaros.network.BitOutputStream;
import ragnaros.network.BitInputStream;
import java.io.IOException;

/**
 *
 * @author Carl
 */
public class Player implements BitSerializable{

    public Player(){
        
    }
    private Game game;
    private int lifepoints;
    private Deck deck = new Deck();
    private Hand hand = new Hand();
    private Cards lands = new Cards(CardPosition.Zone.LAND);
    private Cards monsters = new Cards(CardPosition.Zone.MONSTER);
    private Graveyard graveyard = new Graveyard();
    private ManaAmount availableMana = new ManaAmount();
    private boolean hasAddedLandThisTurn;
    
    public int getIndex(){
        return game.getIndex(this);
    }

    public void setLifepoints(int lifepoints){
        this.lifepoints = lifepoints;
    }

    public int getLifepoints(){
        return lifepoints;
    }
    
    public boolean canAddLand(){
        return (!hasAddedLandThisTurn);
    }
    
    public boolean canCast(Spell spell){
        return canPay(spell.getCost(), spell.getCaster());
    }
    
    public boolean canPay(Cost cost, Card card){
        return (hasEnoughMana(cost.getMana()) && (lifepoints > cost.getLifepoints()) && (!(cost.isTap() && (((FieldCard) card).isTapped()))));
    }
    
    public boolean hasEnoughMana(ManaAmount manaAmount){
        return availableMana.contains(manaAmount);
    }

    public void setGame(Game game){
        this.game = game;
        deck.setOwner(this);
        hand.setOwner(this);
        lands.setOwner(this);
        monsters.setOwner(this);
        graveyard.setOwner(this);
    }

    public Game getGame(){
        return game;
    }
    
    public Player getEnemy(){
        return game.getEnemy(this);
    }

    public Deck getDeck(){
        return deck;
    }

    public Hand getHand(){
        return hand;
    }

    public Cards getLands(){
        return lands;
    }

    public Cards getMonsters(){
        return monsters;
    }

    public Graveyard getGraveyard(){
        return graveyard;
    }
    
    public Card getCard(CardPosition cardPosition){
        Cards zoneCards = null;
        switch(cardPosition.getZone()){
            case DECK:      zoneCards = deck; break;
            case HAND:      zoneCards = hand; break;
            case LAND:      zoneCards = lands; break;
            case MONSTER:   zoneCards = monsters; break;
            case GRAVEYARD: zoneCards = graveyard; break;
        }
        return zoneCards.get(cardPosition.getIndex());
    }

    public ManaAmount getAvailableMana(){
        return availableMana;
    }

    public void setHasAddedLandThisTurn(boolean hasAddedLandThisTurn){
        this.hasAddedLandThisTurn = hasAddedLandThisTurn;
    }

    public boolean hasAddedLandThisTurn(){
        return hasAddedLandThisTurn;
    }

    @Override
    public void write(BitOutputStream outputStream){
        outputStream.writeBits(lifepoints, 8);
        deck.write(outputStream);
        hand.write(outputStream);
        lands.write(outputStream);
        monsters.write(outputStream);
        graveyard.write(outputStream);
        availableMana.write(outputStream);
        outputStream.writeBoolean(hasAddedLandThisTurn);
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException{
        lifepoints = inputStream.readBits(8);
        deck.read(inputStream);
        hand.read(inputStream);
        lands.read(inputStream);
        monsters.read(inputStream);
        graveyard.read(inputStream);
        availableMana.read(inputStream);
        hasAddedLandThisTurn = inputStream.readBoolean();
    }
}
