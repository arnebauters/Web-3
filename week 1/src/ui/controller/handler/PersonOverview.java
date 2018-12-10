package ui.controller.handler;

import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String value = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SortingMethod")) {
                    value = cookie.getValue();
                    request.setAttribute("lijst", getService().getPersonsSorted(value));
                    request.setAttribute("sorteer", value);
                }
            }
        }
        if (value == null){
            request.setAttribute("lijst", getService().getPersons());
        }
        RequestDispatcher view = request.getRequestDispatcher("personoverview.jsp");
        view.forward(request, response);
        return "personoverview.jsp";
    }
}
