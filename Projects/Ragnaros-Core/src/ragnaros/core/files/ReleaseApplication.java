/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.core.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Carl
 */
public class ReleaseApplication{
    
    private static final String DIRECTORY_DEVELOPER_PROJECTS = "../";
    private static final String DIRECTORY_RELEASE_SERVER_APPLICATION = "C:/Users/Carl/Desktop/Webspace/root/ragnaros";
    private static final String[] REMOVED_DIRECTORY_NAMES = new String[]{
        
    };
    private static final String[] REMOVED_FILES_ENDINGS = new String[]{
        "README.txt"
    };
    
    public static void main(String[] args){
        synchronize("Server Application [Executable]", "/Ragnaros-Server/dist/Ragnaros-Server.jar", "/server.jar");
        synchronize("Server Application [Libraries]", "/Ragnaros-Server/dist/lib", "/lib");
        synchronize("Client Application [Download] [Executable]", "/Ragnaros-Client/dist/Ragnaros-Client.jar", "/Ragnaros/Ragnaros.jar");
        synchronize("Client Application [Download] [Libraries]", "/Ragnaros-Client/dist/lib", "/Ragnaros/lib");
        synchronize("Client Application [Update] [Executable]", "/Ragnaros-Client/dist/Ragnaros-Client.jar", "/update/Ragnaros.jar");
        synchronize("Client Application [Update] [Libraries]", "/Ragnaros-Client/dist/lib", "/update/lib");
        synchronize("Client Application [Assets]", "/assets", "/update/assets");
        synchronize("Client Application [Test]", "/Ragnaros-Server/workspace/test", "/update/test");
        synchronize("Client Application [Decks]", "/Ragnaros-Server/workspace/decks/basic", "/update/decks/basic");
    }
    
    private static void synchronize(String title, String developerDirectory, String releaseDirectory){
        System.out.println("Synchronizing " + title + "...");
        cleanup(DIRECTORY_DEVELOPER_PROJECTS + developerDirectory, DIRECTORY_RELEASE_SERVER_APPLICATION + releaseDirectory, "");
        copy(DIRECTORY_DEVELOPER_PROJECTS + developerDirectory, DIRECTORY_RELEASE_SERVER_APPLICATION + releaseDirectory, "");
    }
    
    private static void cleanup(String developerDirectory, String releaseDirectory, String filePath){
        File releasedFile = new File(releaseDirectory + filePath);
        File developerFile = new File(developerDirectory + filePath);
        boolean existsDeveloperFile = developerFile.exists();
        String[] subFileNames = releasedFile.list();
        boolean shouldBeReleased;
        if(subFileNames != null){
            for(String subFileName : subFileNames){
                cleanup(developerDirectory, releaseDirectory, filePath + "/" + subFileName);
            }
            shouldBeReleased = shouldBeReleased_Directory(developerFile);
        }
        else{
            shouldBeReleased = shouldBeReleased_File(developerFile);
        }
        if((!existsDeveloperFile) || (!shouldBeReleased)){
            if(releasedFile.delete()){
                System.out.println("Deleted file: " + filePath);
            }
            else{
                System.err.println("Couldn't delete file: " + filePath);
            }
        }
    }
    
    private static void copy(String developerDirectory, String releaseDirectory, String filePath){
        boolean copyFile;
        File developerFile = new File(developerDirectory + filePath);
        File releasedFile = new File(releaseDirectory + filePath);
        boolean existsReleasedFile = releasedFile.exists();
        String[] subFileNames = developerFile.list();
        if(subFileNames != null){
            copyFile = shouldBeReleased_Directory(developerFile);
            if(copyFile){
                if(!existsReleasedFile){
                    if(releasedFile.mkdir()){
                        System.out.println("Created directory: " + filePath);
                    }
                    else{
                        System.err.println("Couldn't create directory: " + filePath);
                    }
                }
                for(String subFileName : subFileNames){
                    copy(developerDirectory, releaseDirectory, filePath + "/" + subFileName);
                }
            }
        }
        else{
            copyFile = shouldBeReleased_File(developerFile);
            if(copyFile){
                if(releasedFile.exists()){
                    String developerFile_MD5 = FileManager.getFileChecksum_MD5(developerFile);
                    String releasedFile_MD5 = FileManager.getFileChecksum_MD5(releasedFile);
                    if(developerFile_MD5.equals(releasedFile_MD5)){
                        copyFile = false;
                    }
                    else{
                        releasedFile.delete();
                    }
                }
                if(copyFile){
                    try{
                        Files.copy(developerFile.toPath(), releasedFile.toPath());
                        System.out.println("Copied file: " + filePath);
                    }catch(IOException ex){
                        System.err.println("Error while copying file: " + filePath);
                    }
                }
            }
        }
    }
    
    private static boolean shouldBeReleased_Directory(File directory){
        for(String directoryName : REMOVED_DIRECTORY_NAMES){
            if(directory.getName().equals(directoryName)){
                return false;
            }
        }
        return true;
    }
    
    private static boolean shouldBeReleased_File(File file){
        for(String fileExtension : REMOVED_FILES_ENDINGS){
            if(file.getPath().endsWith(fileExtension)){
                return false;
            }
        }
        return true;
    }
}
