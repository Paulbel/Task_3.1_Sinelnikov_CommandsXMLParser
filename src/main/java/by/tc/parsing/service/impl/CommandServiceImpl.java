package by.tc.parsing.service.impl;

import by.tc.parsing.dao.impl.CommandDaoDirector;
import by.tc.parsing.dao.CommandXmlParser;
import by.tc.parsing.dao.ParserType;
import by.tc.parsing.entity.Command;
import by.tc.parsing.service.CommandService;

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
