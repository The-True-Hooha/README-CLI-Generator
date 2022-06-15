package com.github.TheTrueHooha;

import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import static picocli.CommandLine.Model.UsageMessageSpec.SECTION_KEY_COMMAND_LIST;

@Command(name = "README-CLI-Generator",
        description = "generate readme templates for your projects",
        subcommands = {GenerateHelp.class})
public class READMECLIGeneratorCommand implements Runnable {

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(READMECLIGeneratorCommand.class, args);
        CommandLine commandLine = new CommandLine(new READMECLIGeneratorCommand());
        commandLine.getHelpSectionMap().put(SECTION_KEY_COMMAND_LIST, new GenerateHelp());
        commandLine.usage(System.out);
    }

    public void run() {
    }
}
