package com.github.TheTrueHooha;

import picocli.CommandLine.*;

@Command(name = "generate",
        description = "generate readme file based on programming language.",
        mixinStandardHelpOptions = true)
public class GenerateCommand implements Runnable{

    @Option(names = {"-r", "--readme"}, description = "generate associated readme")
    private String readme;

    @Option(names = {"-t", "--tags"}, description = "specific language framework")
    private String tags;

    @Override
    public void run() {
        //String response =
    }
}
