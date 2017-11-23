package by.tc.parsing.dao;

import by.tc.parsing.dao.exception.DAOException;
import by.tc.parsing.entity.Command;

import java.util.Map;

public interface CommandXmlParser {
    Map <String, Command> parse() throws DAOException;
    Map <String,Command> parsePart(int from, int number) throws DAOException;
}
