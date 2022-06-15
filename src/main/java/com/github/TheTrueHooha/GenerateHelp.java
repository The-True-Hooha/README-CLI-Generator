package com.github.TheTrueHooha;


import picocli.CommandLine;
import static picocli.CommandLine.*;
import static picocli.CommandLine.Help.*;
import static picocli.CommandLine.Help.Column.*;
import static picocli.CommandLine.Model.*;

@Command(name = "HELP",
        description = "shows the help command line")
public class GenerateHelp implements IHelpSectionRenderer {

    @Override
    public String render(Help help) {
        CommandSpec commandSpec = help.commandSpec();
        if (commandSpec.subcommands().isEmpty()) {
            return "";
        }
        //prepares the layout into two columns
        //the left column overflows, and the right column wraps if the texts are too long
        TextTable textTable = TextTable.forColumns(help.colorScheme(),
               new Column(20, 2, Overflow.WRAP),
                new Column(commandSpec.usageMessage().width() - 5, 2, Overflow.WRAP));
        textTable.setAdjustLineBreaksForWideCJKCharacters(commandSpec.usageMessage().adjustLineBreaksForWideCJKCharacters());

        for (CommandLine subCommand : commandSpec.subcommands().values()){
            addHierarchy(subCommand, textTable, "");
        }
        return textTable.toString();
    }

    private void addHierarchy(CommandLine commandLine, TextTable textTable, String stringIndent) {

        //creates a comma separated list of commands
        String commandName = commandLine.getCommandSpec().name().toString();

        //sets the command name to display
        commandName = commandName.substring(0, commandName.length());

        //creates the command description that is taken from the header or description
        String description = commandDescription(commandLine.getCommandSpec().usageMessage());

        //adds a line for the command to the layout
        textTable.addRowValues(stringIndent + commandName, description);
    }

    private String commandDescription(UsageMessageSpec usageMessage) {
        if (usageMessage.header().length > 0) {
            //returns usageMessage at index 0
            return usageMessage.header()[0];
        }
        if (usageMessage.description().length > 0) {
            return usageMessage.description()[0];
        }
        return "";
    }
}
