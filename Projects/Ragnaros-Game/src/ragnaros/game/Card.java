/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import java.io.IOException;
import java.util.ArrayList;
import ragnaros.core.Util;
import ragnaros.network.*;

/**
 *
 * @author Carl
 */
public class Card implements EventListener, BitSerializable{

    public Card(){
        description = new Description(getClass().getSimpleName());
    }
    protected Game game;
    private int id = -1;
    protected boolean isCollectible = true;
    protected Player owner;
    protected CardPosition cardPosition = new CardPosition();
    protected Description description;
    protected String flavorText;
    protected Mana[] manaTypes = new Mana[]{Mana.CUSTOM};
    private ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
    private ArrayList<ActiveBuff> activeBuffs = new ArrayList<ActiveBuff>();

    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        return true;
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        
    }

    public void setGame(Game game){
        if(game != this.game){
            this.game = game;
            game.getGameMode().prepareCard(this);
            game.registerCard(this);
            game.addEventListener(this);
            for(Mechanic mechanic : mechanics){
                game.addEventListener(mechanic);
            }
        }
    }

    public Game getGame(){
        return game;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public Player getOwner(){
        return owner;
    }

    public void setOwner(Player owner){
        this.owner = owner;
        setGame(owner.getGame());
    }

    public void setCardPosition(CardPosition cardPosition){
        this.cardPosition = cardPosition;
    }

    public CardPosition getCardPosition(){
        return cardPosition;
    }

    public Description getDescription(){
        return description;
    }

    public String getFlavorText(){
        return flavorText;
    }

    public boolean isCollectible(){
        return isCollectible;
    }

    public Mana[] getManaTypes(){
        return manaTypes;
    }

    public void addMechanics(Mechanic... mechanics){
        for(Mechanic mechanic : mechanics){
            if(!hasMechanic(mechanic.getClass())){
                mechanic.setTarget(this);
                if(game != null){
                    game.addEventListener(mechanic);
                }
                this.mechanics.add(mechanic);
            }
        }
    }

    public void removeMechanics(Class<? extends Mechanic>... mechanicsClasses){
        for(Class<? extends Mechanic> mechanicClass : mechanicsClasses){
            Mechanic mechanic = getMechanic(mechanicClass);
            if(mechanic != null){
                game.removeEventListener(mechanic);
                mechanics.remove(mechanic);
            }
        }
    }
    
    public boolean hasMechanic(Class mechanicClass){
        return (getMechanic(mechanicClass) != null);
    }
    
    public Mechanic getMechanic(Class mechanicClass){
        for(Mechanic mechanic : mechanics){
            if(mechanicClass.isAssignableFrom(mechanic.getClass())){
                return mechanic;
            }
        }
        return null;
    }

    public ArrayList<Mechanic> getMechanics(){
        return mechanics;
    }
    
    public void addBuff(Buff buff){
        ActiveBuff activeBuff = new ActiveBuff(this, buff);
        game.addEventListener(activeBuff);
        activeBuffs.add(activeBuff);
        buff.onAdded(this);
    }
    
    public void removeBuff(Buff buff){
        ActiveBuff activeBuff = getActiveBuff(buff);
        if(activeBuff != null){
            game.removeEventListener(activeBuff);
            activeBuffs.remove(activeBuff);
            activeBuff.getBuff().onRemoved(this);
        }
    }
    
    public void removeBuffs(){
        ActiveBuff[] activeBuffsArray = activeBuffs.toArray(new ActiveBuff[activeBuffs.size()]);
        for(ActiveBuff activeBuff : activeBuffsArray){
            game.removeEventListener(activeBuff);
        }
        activeBuffs.clear();
        for(ActiveBuff activeBuff : activeBuffsArray){
            activeBuff.getBuff().onRemoved(this);
        }
    }
    
    public boolean hasBuff(Buff buff){
        return (getActiveBuff(buff) != null);
    }
    
    private ActiveBuff getActiveBuff(Buff buff){
        for(ActiveBuff activeBuff : activeBuffs){
            if(activeBuff.getBuff() == buff){
                return activeBuff;
            }
        }
        return null;
    }

    public ArrayList<ActiveBuff> getActiveBuffs(){
        return activeBuffs;
    }
    
    @Override
    public Card clone(){
        Card card = CardTypeManager.createCard(getClass());
        byte[] data = NetworkUtil.serialize(this);
        NetworkUtil.unserialize(card, data);
        return card;
    }

    @Override
    public void write(BitOutputStream outputStream){
        outputStream.writeBits(id, 10);
        if(owner != null){
            outputStream.writeBoolean(true);
            outputStream.writeBits(owner.getIndex(), 1);
        }
        else{
            outputStream.writeBoolean(false);
        }
        for(Class mechanicsClass : Mechanic.DYNAMIC_MECHANICS_CLASSES){
            outputStream.writeBoolean(getMechanic(mechanicsClass) != null);
        }
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException{
        id = inputStream.readBits(10);
        boolean hasOwner = inputStream.readBoolean();
        if(hasOwner){
            int ownerIndex = inputStream.readBits(1);
            owner = game.getPlayer(ownerIndex);
        }
        else{
            owner = null;
        }
        for(Class mechanicClass : Mechanic.DYNAMIC_MECHANICS_CLASSES){
            Mechanic mechanic = getMechanic(mechanicClass);
            boolean hasMechanic = (mechanic != null);
            boolean shouldHaveMechanic = inputStream.readBoolean();
            if(shouldHaveMechanic != hasMechanic){
                if(shouldHaveMechanic){
                    addMechanics((Mechanic) Util.createObjectByClass(mechanicClass));
                }
                else{
                    removeMechanics(mechanicClass);
                }
            }
        }
    }
}
