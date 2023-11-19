package com.mycompany.a3.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CommandSetFoodConsumption extends Command {
    // private static String commandName = "Command_Set_Food_Consumption";
    private static String commandName = "Set Food Consumption";
    private static int consumptionRate = 15;

    private GameWorld gameWorld;

    public CommandSetFoodConsumption(GameWorld newGameWorld) {
        super(commandName);
        this.gameWorld = newGameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gameWorld.setFoodConsumptionRate(consumptionRate);
    }
}
