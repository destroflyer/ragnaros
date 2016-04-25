/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import java.util.ArrayList;

/**
 *
 * @author Carl
 */
public class SpellParameterFormat{
    
    private ArrayList<SpellParameterFormat_Entry> entries = new ArrayList<SpellParameterFormat_Entry>();
    
    protected void add(String description, SpellParameterFormat_Entry.Type type, SpellParameterFormat_Entry.Owner targetOwner, CardFilter... cardFilters){
        entries.add(new SpellParameterFormat_Entry(description, type, targetOwner, cardFilters));
    }
    
    public SpellParameterFormat_Entry getEntry(int index){
        return entries.get(index);
    }
    
    public int size(){
        return entries.size();
    }
}
