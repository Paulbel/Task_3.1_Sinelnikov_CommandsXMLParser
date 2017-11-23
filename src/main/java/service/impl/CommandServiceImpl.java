package service.impl;

import dao.impl.CommandDaoDirector;
import dao.CommandXmlParser;
import dao.ParserType;
import entity.Command;
import entity.impl.*;
import service.CommandService;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandServiceImpl implements CommandService {

    @Override
    public Map<String, Command> getCommands(int start, int number, ParserType type) {
        CommandXmlParser parser = CommandDaoDirector.getCommand(type);
        return parser.parsePart(start,number);
    }

    @Override
    public Map<String, Command> getAllCommands(ParserType type) {
        CommandXmlParser parser = CommandDaoDirector.getCommand(type);
        return parser.parse();
    }


}
