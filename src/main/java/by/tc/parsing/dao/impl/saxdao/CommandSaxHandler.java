package by.tc.parsing.dao.impl.saxdao;

import by.tc.parsing.dao.DAOConstant;
import by.tc.parsing.entity.Command;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandSaxHandler extends DefaultHandler {
    private Map<String, Command> commands = new LinkedHashMap<>();
    private StringBuilder text;
    private Command command;
    private String commandName;

    public Map<String, Command> getCommands() {
        return commands;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case DAOConstant.CLASS_NAME:
                try {
                    Class c = Class.forName(DAOConstant.COMMAND_CLASS_PATH+String.valueOf(text));
                    Object obj = c.newInstance();
                    command = (Command) obj;
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            case DAOConstant.COMMAND_NAME:
                commandName = String.valueOf(text);
                break;
            case DAOConstant.COMMAND:
                commands.put(commandName, command);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }




}

