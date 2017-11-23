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
import java.util.Map;

public class ChooseParserCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("parserType");
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
        dispatcher.forward(request,response);
    }
}
