package by.tc.parsing.controller;

import by.tc.parsing.controller.command.ChangeEntryNumberCommand;
import by.tc.parsing.controller.command.ChooseParserCommand;
import by.tc.parsing.controller.command.ChangeEntriesCommand;

import java.util.HashMap;
import java.util.Map;

public final class ControllerCommandDirector {
    private static Map<String, ControllerCommand> map = new HashMap<>();

    static {
        map.put("changeEntries", new ChangeEntriesCommand());
        map.put("chooseParser", new ChooseParserCommand());
        map.put("changeEntryNumber", new ChangeEntryNumberCommand());

    }

    public static ControllerCommand getCommand(String commandName){
        return map.get(commandName);
    }

    private ControllerCommandDirector(){

    }

}
