/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network;

import ragnaros.game.*;
import ragnaros.game.commands.*;
import ragnaros.game.monsters.spells.parameter.*;
import ragnaros.network.messages.*;
import ragnaros.network.messages.objects.*;
import com.esotericsoftware.kryo.Kryo;

/**

 @author Carl
 */
public class MessageSerializer{
    
    public static final int BUFFER_SIZE_WRITE = 10000000;
    public static final int BUFFER_SIZE_OBJECT = 10000000;
    public static final int FILE_PART_SIZE = 1000;
    
    public static void registerClasses(Kryo kryo){
        Class[] classes = new Class[]{
            byte[].class,
            int[].class,
            //Register updater classes first
            Message_GameUpdate.class,
            Message_GetUpdateFile.class,
            Message_GetUpdateFiles.class,
            Message_UpdateFilePart.class,
            Message_UpdateFiles.class,
                UpdateFile.class,
                UpdateFile[].class,
            Message_ClientAccepted.class,
            Message_ClientInitialized.class,
            Message_Command.class,
                Command.class,
                    AttackMonsterCommand.class,
                    AttackPlayerCommand.class,
                    CastFieldCardCommand.class,
                    CastFieldCardSpellCommand.class,
                        CardPosition.class,
                            CardPosition.Zone.class,
                        SpellParameter.class,
                        SpellParameter[].class,
                            HandCardParameter.class,
                            LandParameter.class,
                            MonsterParameter.class,
                    CastSpellCommand.class,
                    EndTurnCommand.class,
                    ThrowingOffCommand.class,
            Message_EnterGameQueue.class,
            Message_GameOver.class,
        };
        for(Class classToRegister : classes){
            kryo.register(classToRegister);
        }
    }
}
