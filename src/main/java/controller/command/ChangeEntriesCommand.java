package controller.command;

import controller.ControllerCommand;
import dao.ParserType;
import entity.Command;
import service.CommandService;
import service.CommandServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ChangeEntriesCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ParserType type = (ParserType)session.getAttribute("parserType");
        CommandServiceFactory factory = CommandServiceFactory.getInstance();
        CommandService commandService = factory.getCommandService();
        int start = Integer.valueOf(request.getParameter("start"));
        int number = Integer.valueOf(request.getParameter("perPage"));
        Map<String, Command> map = commandService.getCommands(start,number+1,type);
        RequestDispatcher dispatcher = request.getRequestDispatcher("table.jsp");
        request.setAttribute("commands",map);
        request.setAttribute("count",map.size());
        dispatcher.forward(request,response);
    }
}
