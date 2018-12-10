package ui.controller.handler;

import domain.model.Fiets;
import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("fiets").equals("none")) {
            Person person = new Person();
            person = getService().getPerson(request.getParameter("person"));
            request.setAttribute("person", person);
        } else {
            Fiets fiets = new Fiets();
            int productId = Integer.parseInt(request.getParameter("fiets"));
            fiets = getService().getFiets(productId);
            request.setAttribute("fiets", fiets);
        }
        RequestDispatcher view = request.getRequestDispatcher("deleteconfirmation.jsp");
        view.forward(request, response);
        return "deleteconfirmation.jsp";
    }
}
