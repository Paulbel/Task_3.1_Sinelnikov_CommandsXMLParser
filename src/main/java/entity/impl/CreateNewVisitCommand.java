package entity.impl;

import entity.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateNewVisitCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public String toString() {
        return "CreateNewVisitCommand";
    }
}
