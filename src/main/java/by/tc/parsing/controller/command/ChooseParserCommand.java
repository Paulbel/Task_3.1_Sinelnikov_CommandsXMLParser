package by.tc.parsing.controller.command;

import by.tc.parsing.controller.ControllerCommand;
import by.tc.parsing.controller.ControllerConstant;
import by.tc.parsing.dao.DAOConstant;
import by.tc.parsing.dao.ParserType;
import by.tc.parsing.dao.exception.DAOException;
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

public class ChooseParserCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter(ControllerConstant.PARSER_TYPE);
        ParserType parserType = ParserType.valueOf(type);
        HttpSession session = request.getSession();
        session.setAttribute(ControllerConstant.PARSER_TYPE,parserType);
        CommandServiceFactory factory = CommandServiceFactory.getInstance();
        CommandService commandService = factory.getCommandService();
        Map <String, Command> map = null;
        RequestDispatcher dispatcher;
        try {
            map = commandService.getAllCommands(parserType);
            dispatcher = request.getRequestDispatcher(ControllerConstant.TABLE_PAGE_URI);

            request.setAttribute(ControllerConstant.COMMANDS_ATTRIBUTE_NAME,map);
            request.setAttribute(ControllerConstant.PER_PAGE_ATTRIBUTE_NAME,map.size());
            request.setAttribute(ControllerConstant.START_ATTRIBUTE_NAME,ControllerConstant.FIRST_VIEW_INDEX);
            request.setAttribute(ControllerConstant.COUNT_ATTRIBUTE_NAME,map.size());
        } catch (ServiceException e) {
            dispatcher = request.getRequestDispatcher(ControllerConstant.PROBLEM_PAGE_URI);
            request.setAttribute(ControllerConstant.PROBLEM_ATTRIBUTE, e.getMessage());
            dispatcher.forward(request, response);
        }

        dispatcher.forward(request,response);
    }
}
