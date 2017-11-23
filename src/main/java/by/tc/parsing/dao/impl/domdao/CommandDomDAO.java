package by.tc.parsing.dao.impl.domdao;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import by.tc.parsing.dao.AbstractCommandXmlDAO;
import by.tc.parsing.dao.ParserConstant;
import by.tc.parsing.entity.Command;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandDomDAO extends AbstractCommandXmlDAO {
    private Map<String, Command> commands;


    @Override
    public Map<String, Command> parse() {
        this.commands = new LinkedHashMap<>();
        DOMParser parser = new DOMParser();
        try {
            parser.parse(new InputSource("G:/apache-tomcat-8.5.23/webapps/ROOT/WEB-INF/classes/commands.xml"));
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();

            NodeList commandNodeList = root.getElementsByTagName(ParserConstant.COMMAND);
            for (int index = 0; index < commandNodeList.getLength(); index++) {
                Element commandElement = (Element) commandNodeList.item(index);
                String commandName = getSingleChild(commandElement, ParserConstant.COMMAND_NAME).getTextContent().trim();
                String className = getSingleChild(commandElement, ParserConstant.CLASS_NAME).getTextContent().trim();
                Class c = Class.forName("by.tc.parsing.entity.impl." + String.valueOf(className));
                Object obj = c.newInstance();
                Command command = (Command) obj;
                this.commands.put(commandName, command);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
