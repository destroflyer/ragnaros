/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network.messages;

/**
 *
 * @author Carl
 */
public class Message_GameUpdate{
    
    public Message_GameUpdate(){
        
    }
    
    public Message_GameUpdate(byte[] data){
        this.data = data;
    }
    private byte[] data;

    public byte[] getData(){
        return data;
    }
}
