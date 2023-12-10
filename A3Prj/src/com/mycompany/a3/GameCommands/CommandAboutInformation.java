package com.mycompany.a3.GameCommands;

import com.codename1.ui.Dialog;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandAboutInformation extends Command {
    // private static String commandName = "Command_About_Information";
    private static String commandName = "About Information";

    public CommandAboutInformation() {
        super(commandName);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Dialog.show(commandName,
                "Aaron Shackelford\n" +
                        "CSC 133: Object-Oriented Computer Graphics Programming – Fall 2023\n" +
                        "A2Prj\n",
                "close",
                null);
    }

}
