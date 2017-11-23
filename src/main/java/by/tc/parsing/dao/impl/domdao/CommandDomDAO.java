package by.tc.parsing.dao.impl.domdao;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import by.tc.parsing.dao.AbstractCommandXmlParser;
import by.tc.parsing.dao.DAOConstant;
import by.tc.parsing.entity.Command;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandDomDAO extends AbstractCommandXmlParser {
    private Map<String, Command> commands;


    @Override
    public Map<String, Command> parse() {
        this.commands = new LinkedHashMap<>();
        DOMParser parser = new DOMParser();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL sourceURL = classLoader.getResource("commands.xml");
            assert sourceURL != null;
            String path = sourceURL.getPath();
            parser.parse(path);
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();

            NodeList commandNodeList = root.getElementsByTagName(DAOConstant.COMMAND);
            for (int index = 0; index < commandNodeList.getLength(); index++) {
                Element commandElement = (Element) commandNodeList.item(index);
                String commandName = getSingleChild(commandElement, DAOConstant.COMMAND_NAME).getTextContent().trim();
                String className = getSingleChild(commandElement, DAOConstant.CLASS_NAME).getTextContent().trim();

                Class c = Class.forName(DAOConstant.COMMAND_CLASS_PATH + String.valueOf(className));
                Object obj = c.newInstance();
                Command command = (Command) obj;

                this.commands.put(commandName, command);
            }
        } catch (SAXException | IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this.commands;
    }



    private Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }

}
