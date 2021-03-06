package by.tc.parsing.dao;

import by.tc.parsing.dao.exception.DAOException;
import by.tc.parsing.entity.Command;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractCommandXmlParser implements CommandXmlParser {
    @Override
    public Map<String, Command> parsePart(int from, int number) throws DAOException {
        Map<String, Command> commandMap = this.parse();
        Map<String, Command> resultMap = new LinkedHashMap<>();
        int index = 0;
        for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
            if (index >= from && index < from + number) {
                resultMap.put(entry.getKey(), entry.getValue());
            }
            index++;
        }
        return resultMap;
    }
}
