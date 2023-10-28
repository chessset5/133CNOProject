package com.mycompany.a2.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class CommandCollideWithSpider extends Command {
    private static String commandName = "Command_Collide_With_Spider";

    private GameWorld gameWorld;

    public CommandCollideWithSpider(GameWorld newGameWorld) {
        super(commandName);
        this.gameWorld = newGameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gameWorld.collideSpider();
    }
}
