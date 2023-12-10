package com.mycompany.a3.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CommandRightTurn extends Command {
    // private static String commandName = "Command_Right_Turn";
    private static String commandName = "Right Turn";

    private GameWorld gameWorld;

    public CommandRightTurn(GameWorld newGameWorld) {
        super(commandName);
        this.gameWorld = newGameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gameWorld.right();
    }

}
