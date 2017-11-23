package by.tc.parsing.controller.command;

import by.tc.parsing.controller.ControllerCommand;
import by.tc.parsing.controller.ViewContext;
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

public class ChooseParserCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {
/*        String type = request.getParameter("parserType");
        ParserType parserType = ParserType.valueOf(type);
        HttpSession session = request.getSession();
        session.setAttribute("parserType",parserType);
        CommandServiceFactory factory = CommandServiceFactory.getInstance();
        CommandService commandService = factory.getCommandService();
        Map <String, Command> map = commandService.getAllCommands(parserType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("table.jsp");

        request.setAttribute("commands",map);
        request.setAttribute("perPage",map.size());
        request.setAttribute("start",0);
        request.setAttribute("count",map.size());
        dispatcher.forward(request,response);*/

        String type = request.getParameter("parserType");
        ParserType parserType = ParserType.valueOf(type);
        HttpSession session = request.getSession();
        session.setAttribute("parserType",parserType);

        CommandServiceFactory factory = CommandServiceFactory.getInstance();
        CommandService commandService = factory.getCommandService();
        Map <String, Command> map = commandService.getAllCommands(parserType);

        ViewContext viewContext = new ViewContext();
        viewContext.setMap(map);
        viewContext.setLess(false);
        viewContext.setMore(false);
        viewContext.setOnPage(map.size());
        viewContext.setStartEntryIndex(1);
        viewContext.setEndEntryIndex(map.size());
        session.setAttribute("viewContext",viewContext);
        RequestDispatcher dispatcher = request.getRequestDispatcher("table.jsp");

        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
