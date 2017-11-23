package service;

import dao.ParserType;
import entity.Command;

import java.util.Map;

public interface CommandService {
    Map<String, Command> getCommands(int start, int number, ParserType type);
    Map<String, Command> getAllCommands(ParserType type);
}
