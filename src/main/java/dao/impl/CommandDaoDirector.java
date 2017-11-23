package dao.impl;

import dao.*;
import dao.impl.domdao.CommandDomDAO;
import dao.impl.saxdao.CommandSaxDAO;
import dao.impl.staxdao.CommandStaxDAO;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandDaoDirector {
    private static Map<ParserType, CommandXmlParser> map = new LinkedHashMap<>();

    static {
        map.put(ParserType.DOM, new CommandDomDAO());
        map.put(ParserType.SAX, new CommandSaxDAO());
        map.put(ParserType.STAX, new CommandStaxDAO());

    }

    public static CommandXmlParser getCommand(ParserType type){
        return map.get(type);
    }

    private CommandDaoDirector(){

    }

}
