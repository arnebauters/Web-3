package ui.controller.handler;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Password extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person;
        person = getService().getPerson(request.getParameter("person"));
        request.setAttribute("person", person.getUserid());
        RequestDispatcher view = request.getRequestDispatcher("password.jsp");
        view.forward(request, response);
        return "password.jsp";
    }
}
