package com.mycompany.a2.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class CommandExit extends Command {
    private static String commandName = "Command_Exit";

    public CommandExit(GameWorld newGameWorld) {
        super(commandName);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        boolean exitConfirm = Dialog.show("Exit Mode", "Do you wish to exit?", "Yes", "No");
        if (exitConfirm == true) {
            Display.getInstance().exitApplication();
        }
    }
}
