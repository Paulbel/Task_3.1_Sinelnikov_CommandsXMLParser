package by.tc.parsing.controller;

import by.tc.parsing.controller.command.ChooseParserCommand;
import by.tc.parsing.controller.command.ChangeEntriesCommand;

import java.util.HashMap;
import java.util.Map;

public final class ControllerCommandDirector {
    private static Map<String, ControllerCommand> map = new HashMap<>();

    static {
        map.put(ControllerConstant.CHANGE_ENTRIES_COMMAND, new ChangeEntriesCommand());
        map.put(ControllerConstant.CHOOSE_PARSER_COMMAND, new ChooseParserCommand());
    }

    public static ControllerCommand getCommand(String commandName){
        return map.get(commandName);
    }

    private ControllerCommandDirector(){

    }

}
