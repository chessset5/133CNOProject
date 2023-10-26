package com.mycompany.a2.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.CheckBox;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class CommandSoundOn extends Command {
    private static String commandName = "Command_Sound_On";

    private GameWorld gameWorld;

    public CommandSoundOn(GameWorld newGameWorld) {
        super(commandName);
        this.gameWorld = newGameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // is selected will return a boolean true or false
        // if true sound on, if false sound off.
        gameWorld.setSound(((CheckBox) event.getComponent()).isSelected());
    }
}
