/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network.messages;

import ragnaros.network.messages.objects.UpdateFile;

/**
 *
 * @author Carl
 */
public class Message_UpdateFiles{
    
    public Message_UpdateFiles(){
        
    }
    
    public Message_UpdateFiles(UpdateFile[] updateFiles){
        this.updateFiles = updateFiles;
    }
    private UpdateFile[] updateFiles;

    public UpdateFile[] getUpdateFiles(){
        return updateFiles;
    }
}
