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
import java.util.Comparator;
import java.util.Collections;

/**
 *
 * @author Carl
 */
public class Cards implements BitSerializable{

    public Cards(){
        
    }
    
    public Cards(CardPosition.Zone zone){
        this.zone = zone;
    }
    private Game game;
    private CardPosition.Zone zone;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private BitSerializable plainTypeSerializer = new BitSerializable(){

        @Override
        public void write(BitOutputStream outputStream){
            outputStream.writeBits(size(), 10);
            for(Card card : cards){
                outputStream.writeBits(CardTypeManager.getType(card), 10);
            }
        }

        @Override
        public void read(BitInputStream inputStream) throws IOException{
            clear();
            int size = inputStream.readBits(10);
            for(int i=0;i<size;i++){
                int type = inputStream.readBits(10);
                Card card = CardTypeManager.createCard(type);
                add(card);
            }
        }
    };

    public CardPosition.Zone getZone(){
        return zone;
    }
    
    public void clear(){
        cards.clear();
    }
    
    public void add(Card card){
        cards.add(card);
        updateCardPositions();
    }
    
    public void add(Card card, int index){
        cards.add(index, card);
        updateCardPositions();
    }
    
    public Card getFirst(){
        return get(0);
    }
    
    public Card getLast(){
        return get(size() - 1);
    }
    
    public Card get(int index){
        if((index >= 0) && (index < size())){
            return cards.get(index);
        }
        return null;
    }
    
    public int indexOf(Card card){
        return cards.indexOf(card);
    }
    
    public boolean contains(Card card){
        return cards.contains(card);
    }
    
    public Card removeLast(){
        int lastIndex = (size() - 1);
        if(lastIndex >= 0){
            return remove(lastIndex);
        }
        return null;
    }
    
    public Card remove(int index){
        Card card = cards.remove(index);
        updateCardPositions();
        return card;
    }
    
    public boolean remove(Card card){
        boolean success = cards.remove(card);
        updateCardPositions();
        return success;
    }
    
    public boolean isEmpty(){
        return (size() == 0);
    }
    
    public int size(){
        return cards.size();
    }
    
    public void shuffle(){
        Collections.shuffle(cards);
        updateCardPositions();
    }
    
    public void sort(Comparator<Card> comparator){
        Collections.sort(cards, comparator);
        updateCardPositions();
    }
    
    private void updateCardPositions(){
        if(zone != null){
            for(int i=0;i<cards.size();i++){
                Card card = cards.get(i);
                card.getCardPosition().setZone(zone);
                card.getCardPosition().setIndex(i);
            }
        }
    }
    
    public int getAmount(CardFilter... cardFilters){
        int amount = 0;
        for(Card card : cards){
            if(isValid(cardFilters, card)){
                amount++;
            }
        }
        return amount;
    }
    
    public Card getFirst(CardFilter... cardFilters){
        for(Card card : cards){
            if(isValid(cardFilters, card)){
                return card;
            }
        }
        return null;
    }
    
    public Card getLast(CardFilter... cardFilters){
        for(int i=(cards.size() - 1);i>=0;i--){
            Card card = cards.get(i);
            if(isValid(cardFilters, card)){
                return card;
            }
        }
        return null;
    }
    
    public Card getRandom(CardFilter... cardFilters){
        ArrayList<Card> randomSortedCards = (ArrayList<Card>) cards.clone();
        Collections.shuffle(randomSortedCards);
        for(Card card : randomSortedCards){
            if(isValid(cardFilters, card)){
                return card;
            }
        }
        return null;
    }
    
    public static boolean isValid(CardFilter[] cardFilters, Card card){
        for(CardFilter cardFilter : cardFilters){
            if(!cardFilter.isValid(card)){
                return false;
            }
        }
        return true;
    }
    
    public boolean containsTribe(MonsterCard.Tribe tribe){
        for(Card card : cards){
            if(card instanceof MonsterCard){
                MonsterCard monsterCard = (MonsterCard) card;
                if(monsterCard.hasTribe(tribe)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void setOwner(Player player){
        game = player.getGame();
        for(Card card : cards){
            card.setOwner(player);
        }
    }

    @Override
    public void write(BitOutputStream outputStream){
        outputStream.writeBits(size(), 10);
        for(Card card : cards){
            outputStream.writeBits(card.getID(), 10);
        }
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException{
        clear();
        int size = inputStream.readBits(10);
        for(int i=0;i<size;i++){
            int id = inputStream.readBits(10);
            Card card = game.getCard(id);
            add(card);
        }
    }

    public BitSerializable getPlainTypeSerializer(){
        return plainTypeSerializer;
    }
}
