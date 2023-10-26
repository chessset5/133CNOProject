package com.mycompany.a2.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class CommandCollideWithFlag extends Command {
    private static String commandName = "Command_Collide_With_Flag";

    private GameWorld gameWorld;

    public CommandCollideWithFlag(GameWorld newGameWorld) {
        super(commandName);
        this.gameWorld = newGameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Command flagNumberCommand = new Command("Collide With Flag Num");
        TextField textField = new TextField();
        Dialog.show("Enter Flag to Collide With:", textField, flagNumberCommand);
        try {
            int flagNumberInt = Integer.parseInt(textField.getText().toString());
            if (flagNumberInt < 1 || flagNumberInt > 9) {
                // If flag is under 1 or more than 9
                this.showError();
            } else {
                // Good value
                // Collide with flag Flagnumberint
                gameWorld.collideFlag(flagNumberInt);
            }
        } catch (NumberFormatException exception) {
            // Flag was not a readable integer
            this.showError();
        }
    }

    private void showError() {
        Dialog.show("Error",
                "Bad Entry\n" +
                        "Please enter a number between 1-9 inclusively",
                "okay", null);
    }
}
