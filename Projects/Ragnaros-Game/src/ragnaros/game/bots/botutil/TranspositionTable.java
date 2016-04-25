/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.bots.botutil;

/**
 *
 * @author Philipp
 */
public class TranspositionTable
{
    private long mask;
    private TranspositionEntry[] entries;

    public TranspositionTable(int size)
    {
        entries = new TranspositionEntry[1 << size];
        mask = entries.length - 1;
        for (int i = 0; i < entries.length; i++)
        {
            entries[i] = new TranspositionEntry();
        }
    }
    
    public TranspositionEntry get(long hash)
    {
        return entries[(int)(hash & mask)];
    }
    
    public void printNumEntries()
    {
        int num = 0;
        for (int i = 0; i < entries.length; i++)
        {
            if(entries[i].hash != 0) num++;
        }
        System.out.println("" + num);
    }
}
