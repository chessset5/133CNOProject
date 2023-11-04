package com.mycompany.a2.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandHelpInformation extends Command {
    // private static String commandName = "Command_Help_Information";
    private static String commandName = "Help Information";

    public CommandHelpInformation() {
        super(commandName);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Dialog.show(commandName,
                "Accelerate: a\n" +
                        "Break: b\n" +
                        "Left Turn: l\n" +
                        "Right Turn: r\n" +
                        "Set Food Consumption: c\n" +
                        "Collide with Food Station: f\n" +
                        "Collide with Spider: g\n" +
                        "Tick: t\n",
                "close", null);
    }
}
