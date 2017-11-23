package by.tc.parsing.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerCommand {
    void execute(HttpServletRequest request, HttpServletResponse response);
}

