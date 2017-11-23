package by.tc.parsing.controller.command;

import by.tc.parsing.controller.ControllerCommand;
import by.tc.parsing.controller.ControllerConstant;
import by.tc.parsing.dao.ParserType;
import by.tc.parsing.entity.Command;
import by.tc.parsing.service.CommandService;
import by.tc.parsing.service.CommandServiceFactory;
import by.tc.parsing.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ChangeEntriesCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ParserType type = (ParserType)session.getAttribute(ControllerConstant.PARSER_TYPE);
        CommandServiceFactory factory = CommandServiceFactory.getInstance();
        CommandService commandService = factory.getCommandService();

        int start = Integer.valueOf(request.getParameter(ControllerConstant.START_ATTRIBUTE_NAME));
        int number = Integer.valueOf(request.getParameter(ControllerConstant.PER_PAGE_ATTRIBUTE_NAME));

        Map<String, Command> map = null;
        RequestDispatcher dispatcher;
        try {
            map = commandService.getCommands(start,
                    number+ ControllerConstant.ADDITIONAL_ENTRIES_NUMBER,type);
            request.setAttribute(ControllerConstant.COMMANDS_ATTRIBUTE_NAME,map);
            request.setAttribute(ControllerConstant.COUNT_ATTRIBUTE_NAME,map.size());
            dispatcher = request.getRequestDispatcher(ControllerConstant.TABLE_PAGE_URI);
        } catch (ServiceException e) {
            request.setAttribute(ControllerConstant.PROBLEM_ATTRIBUTE, e.getMessage());
            dispatcher = request.getRequestDispatcher(ControllerConstant.PROBLEM_PAGE_URI);
        }
        dispatcher.forward(request,response);
    }
}
