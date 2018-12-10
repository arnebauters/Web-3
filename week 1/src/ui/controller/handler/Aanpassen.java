package ui.controller.handler;

import domain.model.Fiets;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Aanpassen extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Fiets fiets = new Fiets();
        int productId = Integer.parseInt(request.getParameter("productId"));
        fiets = getService().getFiets(productId);
        request.setAttribute("fiets", fiets);
        RequestDispatcher view = request.getRequestDispatcher("update.jsp");
        view.forward(request, response);
        return "update.jsp";
    }
}
