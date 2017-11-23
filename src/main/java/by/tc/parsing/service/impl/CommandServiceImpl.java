package by.tc.parsing.service.impl;

import by.tc.parsing.dao.exception.DAOException;
import by.tc.parsing.dao.impl.CommandDaoDirector;
import by.tc.parsing.dao.CommandXmlParser;
import by.tc.parsing.dao.ParserType;
import by.tc.parsing.entity.Command;
import by.tc.parsing.service.CommandService;
import by.tc.parsing.service.exception.ServiceException;

import java.util.Map;

public class CommandServiceImpl implements CommandService {

    @Override
    public Map<String, Command> getCommands(int start, int number, ParserType type) throws ServiceException {
        CommandXmlParser parser = CommandDaoDirector.getCommand(type);
        try {
            return parser.parsePart(start,number);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<String, Command> getAllCommands(ParserType type) throws ServiceException {
        CommandXmlParser parser = CommandDaoDirector.getCommand(type);
        try {
            return parser.parse();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
