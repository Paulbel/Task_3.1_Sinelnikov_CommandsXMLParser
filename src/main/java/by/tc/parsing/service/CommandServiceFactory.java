package by.tc.parsing.service;

import by.tc.parsing.service.impl.CommandServiceImpl;

public final class CommandServiceFactory {
    private static CommandServiceFactory instance = new CommandServiceFactory();
    private CommandService commandService = new CommandServiceImpl();

    public static CommandServiceFactory getInstance() {
        return instance;
    }

    public CommandService getCommandService() {
        return commandService;
    }

    private CommandServiceFactory(){
    }
}
