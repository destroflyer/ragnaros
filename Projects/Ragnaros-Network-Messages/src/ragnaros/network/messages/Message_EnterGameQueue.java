/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network.messages;

/**
 *
 * @author Carl
 */
public class Message_EnterGameQueue{

    public Message_EnterGameQueue(){
        
    }

    public Message_EnterGameQueue(byte[] deckData){
        this.deckData = deckData;
    }
    private byte[] deckData;

    public byte[] getDeckData(){
        return deckData;
    }
}
