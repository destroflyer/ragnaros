/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.server;

import java.io.File;
import java.util.LinkedList;
import ragnaros.core.files.FileManager;
import ragnaros.network.messages.objects.UpdateFile;

/**
 *
 * @author Carl
 */
public class UpdateManager{

    public UpdateManager(){
        updateFilesDirectory = FileManager.getFileContent("./update.ini");
        LinkedList<UpdateFile> filesList = new LinkedList<UpdateFile>();
        File parentDirectory = new File(updateFilesDirectory);
        addUpdateFiles(parentDirectory, parentDirectory, filesList);
        updateFiles = filesList.toArray(new UpdateFile[filesList.size()]);
    }
    private String updateFilesDirectory;
    private UpdateFile[] updateFiles;
    
    private void addUpdateFiles(File parentDirectory, File file, LinkedList<UpdateFile> filesList){
        if(file.isDirectory()){
            if(file != parentDirectory){
                filesList.add(new UpdateFile(prepareUpdateFilePath(file) + "/", null, 0));
            }
            File[] subFiles = file.listFiles();
            for(File subFile : subFiles){
                addUpdateFiles(parentDirectory, subFile, filesList);
            }
        }
        else{
            filesList.add(new UpdateFile(prepareUpdateFilePath(file), FileManager.getFileChecksum_MD5(file), file.length()));
        }
    }
    
    private String prepareUpdateFilePath(File file){
        String filePath = ("./" + file.getPath().substring(updateFilesDirectory.length()));
        filePath = filePath.replace("\\", "/");
        return filePath;
    }

    public String getUpdateFilesDirectory(){
        return updateFilesDirectory;
    }

    public UpdateFile[] getUpdateFiles(){
        return updateFiles;
    }
}
