package by.tc.parsing.entity.impl;

import by.tc.parsing.entity.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public String toString() {
        return "RegistrationCommand";
    }
}
