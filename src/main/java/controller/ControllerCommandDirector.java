package controller;

import controller.command.ChooseParserCommand;
import controller.command.ChangeEntriesCommand;

import java.util.HashMap;
import java.util.Map;

public final class ControllerCommandDirector {
    private static Map<String, ControllerCommand> map = new HashMap<>();

    static {
        map.put("changeEntries", new ChangeEntriesCommand());
        map.put("chooseParser", new ChooseParserCommand());
    }

    public static ControllerCommand getCommand(String commandName){
        return map.get(commandName);
    }

    private ControllerCommandDirector(){

    }

}
