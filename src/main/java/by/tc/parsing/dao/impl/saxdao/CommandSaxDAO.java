package by.tc.parsing.dao.impl.saxdao;


import by.tc.parsing.dao.AbstractCommandXmlDAO;
import by.tc.parsing.entity.Command;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Map;

public class CommandSaxDAO extends AbstractCommandXmlDAO {
    private Map<String, Command> commands;

    @Override
    public Map<String,Command> parse() {
                try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            CommandSaxHandler handler = new CommandSaxHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource("G:/apache-tomcat-8.5.23/webapps/ROOT/WEB-INF/classes/commands.xml"));
            this.commands = handler.getCommands();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return commands;
    }


}
