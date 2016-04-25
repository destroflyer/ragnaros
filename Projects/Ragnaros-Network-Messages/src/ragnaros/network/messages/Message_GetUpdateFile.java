/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network.messages;

/**
 *
 * @author Carl
 */
public class Message_GetUpdateFile{
    
    public Message_GetUpdateFile(){
        
    }

    public Message_GetUpdateFile(int index){
        this.index = index;
    }
    private int index;

    public int getIndex(){
        return index;
    }
}
