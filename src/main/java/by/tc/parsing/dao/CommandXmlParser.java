package by.tc.parsing.dao;

import by.tc.parsing.entity.Command;

import java.util.Map;

public interface CommandXmlParser {
    Map <String, Command> parse();
    Map <String,Command> parsePart(int from, int number);
}
