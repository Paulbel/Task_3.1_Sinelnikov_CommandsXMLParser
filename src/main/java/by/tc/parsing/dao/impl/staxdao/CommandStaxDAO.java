package by.tc.parsing.dao.impl.staxdao;

import by.tc.parsing.dao.CommandXmlParser;
import by.tc.parsing.dao.DAOConstant;
import by.tc.parsing.dao.exception.DAOException;
import by.tc.parsing.entity.Command;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandStaxDAO implements CommandXmlParser {
    private Map<String, Command> commands;

    public Map<String, Command> getCommands() {
        return commands;
    }

    private String name;
    private Command command;

    public Map<String, Command> parse() throws DAOException {
        try {
            process(createReader());
            return this.commands;
        } catch (XMLStreamException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Map<String, Command> parsePart(int from, int number) throws DAOException {
        try {
            processPart(createReader(), from, number);
            return this.commands;
        } catch (XMLStreamException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
    }

    private XMLStreamReader createReader() throws DAOException, XMLStreamException {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            ClassLoader classLoader = getClass().getClassLoader();
            URL sourceURL = classLoader.getResource(DAOConstant.FILE_NAME);
            assert sourceURL != null;
            String path = sourceURL.getPath();
            InputStream inputStream = new FileInputStream(path);
            return inputFactory.createXMLStreamReader(inputStream);
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        }

    }

    private void process(XMLStreamReader reader) throws XMLStreamException, ClassNotFoundException, IllegalAccessException, InstantiationException, DAOException {
        String elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case (DAOConstant.COMMANDS):
                            commands = new LinkedHashMap<>();
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    assert elementName != null;
                    switch (elementName) {
                        case DAOConstant.COMMAND_NAME:
                            this.name = text;
                            break;
                        case DAOConstant.CLASS_NAME:
                            this.command = createCommandByName(text);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case DAOConstant.COMMAND:
                            commands.put(name, command);
                            break;
                    }

            }
        }
    }

    private void processPart(XMLStreamReader reader, int from, int number) throws XMLStreamException, ClassNotFoundException, IllegalAccessException, InstantiationException, DAOException {
        String elementName = null;
        int parsedNumber = 0;
        int index = 0;
        while (reader.hasNext() && parsedNumber < number) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case (DAOConstant.COMMANDS):
                            commands = new LinkedHashMap<>();
                            break;
                        case (DAOConstant.COMMAND):
                            index++;
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    assert elementName != null;
                    switch (elementName) {
                        case DAOConstant.COMMAND_NAME:
                            this.name = text;
                            break;
                        case DAOConstant.CLASS_NAME:
                            this.command = createCommandByName(text);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case DAOConstant.COMMAND:
                            if (index > from) {
                                commands.put(name, command);
                                number--;
                            }
                            break;
                    }
            }
        }
    }

    private Command createCommandByName(String name) throws DAOException {
        try {
            Class c = Class.forName(DAOConstant.COMMAND_CLASS_PATH + String.valueOf(name));
            Object obj = c.newInstance();
            Command command = (Command) obj;
            return command;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new DAOException();
        }
    }

}
