/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.server;

/**
 *
 * @author Carl
 */
public class GameQueuePlayer{

    public GameQueuePlayer(int connectionID, byte[] deck){
        this.connectionID = connectionID;
        this.deck = deck;
    }
    private int connectionID;
    private byte[] deck;

    public int getConnectionID(){
        return connectionID;
    }

    public byte[] getDeck(){
        return deck;
    }
}
