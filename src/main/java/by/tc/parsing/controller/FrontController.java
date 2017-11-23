package by.tc.parsing.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(ControllerConstant.COMMAND_PARAMETER_NAME);
        ControllerCommand command = ControllerCommandDirector.getCommand(commandName);
        command.execute(request,response);
        System.out.println(request);
    }
}
