package ui.controller.handler;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckPassword extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person;
        String password = request.getParameter("password");
        person = getService().getPerson(request.getParameter("person"));
        String error;
        String correct;
        if (!person.isCorrectPassword(password)) {
            error = "The password is not Correct";
            request.setAttribute("error", error);
            request.setAttribute("person", person.getUserid());
        } else {
            correct = "Correct password";
            request.setAttribute("correct", correct);
            request.setAttribute("person", person.getUserid());
        }
        RequestDispatcher view = request.getRequestDispatcher("password.jsp");
        view.forward(request, response);
        return "password.jsp";
    }
}
