package by.tc.parsing.dao.impl.saxdao;


import by.tc.parsing.dao.AbstractCommandXmlParser;
import by.tc.parsing.dao.DAOConstant;
import by.tc.parsing.dao.exception.DAOException;
import by.tc.parsing.entity.Command;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class CommandSaxDAO extends AbstractCommandXmlParser {
    private Map<String, Command> commands;

    @Override
    public Map<String, Command> parse() {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            CommandSaxHandler handler = new CommandSaxHandler();
            reader.setContentHandler(handler);
            ClassLoader classLoader = getClass().getClassLoader();
            URL sourceURL = classLoader.getResource(DAOConstant.FILE_NAME);
            assert sourceURL != null;
            String path = sourceURL.getPath();
            reader.parse(path);
            this.commands = handler.getCommands();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return commands;
    }


}
