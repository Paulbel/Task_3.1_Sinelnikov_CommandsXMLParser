package by.tc.parsing.service;

import by.tc.parsing.dao.ParserType;
import by.tc.parsing.entity.Command;
import by.tc.parsing.service.exception.ServiceException;

import java.util.Map;

public interface CommandService {
    Map<String, Command> getCommands(int start, int number, ParserType type) throws ServiceException;
    Map<String, Command> getAllCommands(ParserType type) throws ServiceException;
}
