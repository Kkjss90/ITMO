package org.example.commands;

public interface CommandWithArguments extends Command{
    void getCommandArguments(String[] arguments);
}
