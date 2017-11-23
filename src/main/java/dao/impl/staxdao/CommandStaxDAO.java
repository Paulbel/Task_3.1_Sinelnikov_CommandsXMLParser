package dao.impl.staxdao;

import dao.CommandXmlParser;
import dao.ParserConstant;
import entity.Command;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandStaxDAO implements CommandXmlParser {
    private Map<String, Command> commands;

    public Map<String, Command> getCommands() {
        return commands;
    }

    private String name;
    private Command command;

    public Map<String, Command> parse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream("G:/apache-tomcat-8.5.23/webapps/ROOT/WEB-INF/classes/commands.xml");
            //InputStream inputStream = new FileInputStream("src/main/resources/commands.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            process(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return commands;
    }

    @Override
    public Map<String, Command> parsePart(int from, int number) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream("G:/apache-tomcat-8.5.23/webapps/ROOT/WEB-INF/classes/commands.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            processPart(reader, from, number);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return commands;
    }

    private void process(XMLStreamReader reader) throws XMLStreamException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case ("commands"):
                            commands = new LinkedHashMap<>();
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case ParserConstant.COMMAND_NAME:
                            this.name = text;
                            break;
                        case ParserConstant.CLASS_NAME:
                            this.command = createCommandByName(text);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case "command":
                            commands.put(name, command);
                            break;
                    }

            }
        }
    }

    private void processPart(XMLStreamReader reader, int from, int number) throws XMLStreamException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String elementName = null;
        int parsedNumber = 0;
        int index = 0;
        while (reader.hasNext() && parsedNumber < number) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case ("commands"):
                            commands = new LinkedHashMap<>();
                            break;
                        case ("command"):
                            index++;
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case ParserConstant.COMMAND_NAME:
                            this.name = text;
                            break;
                        case ParserConstant.CLASS_NAME:
                            this.command = createCommandByName(text);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case "command":
                            if (index > from) {
                                commands.put(name, command);
                                number--;
                            }
                            break;
                    }

            }
        }
    }

    Command createCommandByName(String name) {

        Command command =null;
        try {
            Class c = Class.forName("entity.impl." + String.valueOf(name));
            Object obj = c.newInstance();
            command = (Command)obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return command;
    }

}
