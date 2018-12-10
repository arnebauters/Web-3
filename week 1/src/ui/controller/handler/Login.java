package ui.controller.handler;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("userid")!= null) {
            String userid = request.getParameter("userid").toLowerCase();
            String password = request.getParameter("password");
            Person person = getService().getPerson(userid);
            if (person != null) {
                if (getService().getUserIfAuthenticated(person.getUserid(), password) != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("name", person);
                    response.sendRedirect("Controller?action=Home");
                    return "index.jsp";
                }
                request.setAttribute("waardeuserid", person.getUserid());
            }
        }
        request.setAttribute("error", "username or password is incorrect!");
        RequestDispatcher view = request.getRequestDispatcher("Controller?action=Home");
        view.forward(request, response);
        return "index.jsp";
    }
}
