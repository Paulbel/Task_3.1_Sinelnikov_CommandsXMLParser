package by.tc.parsing.controller.command;

import by.tc.parsing.controller.ControllerCommand;
import by.tc.parsing.controller.ViewContext;
import by.tc.parsing.dao.ParserType;
import by.tc.parsing.entity.Command;
import by.tc.parsing.service.CommandService;
import by.tc.parsing.service.CommandServiceFactory;
import org.xml.sax.InputSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ChangeEntryNumberCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ParserType type = (ParserType) session.getAttribute("parserType");
        ViewContext viewContext= (ViewContext) session.getAttribute("viewContext");

        int onPage = Integer.valueOf(request.getParameter("onPage"));




        CommandServiceFactory factory = CommandServiceFactory.getInstance();
        CommandService commandService = factory.getCommandService();
        Map <String,Command> map = commandService.getCommands(viewContext.getStartEntryIndex(),onPage+1,type);
        int startEntryIndex = viewContext.getStartEntryIndex();
        int endEntryIndex = viewContext.getEndEntryIndex();

        if(map.size()<=onPage){

            viewContext.setOnPage(map.size());
        }else {
            viewContext.setOnPage(map.size()-1);
        }


        System.out.println(viewContext.getOnPage());
/*        int number
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
        }*/
    }
}
