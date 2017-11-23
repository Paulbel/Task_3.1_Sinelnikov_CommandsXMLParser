package by.tc.parsing.controller.command;

import by.tc.parsing.controller.ControllerCommand;
import by.tc.parsing.dao.ParserType;
import by.tc.parsing.entity.Command;
import by.tc.parsing.service.CommandService;
import by.tc.parsing.service.CommandServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ChangeEntriesCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ParserType type = (ParserType) session.getAttribute("parserType");
        CommandServiceFactory factory = CommandServiceFactory.getInstance();
        CommandService commandService = factory.getCommandService();
        int start = Integer.valueOf(request.getParameter("start"));
        int number = Integer.valueOf(request.getParameter("perPage"));
        Map<String, Command> map = commandService.getCommands(start, number + 1, type);
        RequestDispatcher dispatcher = request.getRequestDispatcher("table.jsp");
        request.setAttribute("commands", map);
        request.setAttribute("count", map.size());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
