package ui.controller.handler;

import domain.model.Role;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN};
        Utilitys.checkRole(request, roles);
        RequestDispatcher view = request.getRequestDispatcher("addproduct.jsp");
        view.forward(request, response);
        return "addproduct.jsp";
    }
}
