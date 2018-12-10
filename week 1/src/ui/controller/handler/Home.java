package ui.controller.handler;

import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookies = request.getCookies();
        String value = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("quote")) {
                    value = cookie.getValue();
                }
            }
        }

        if (value != null) {
            if (value.equals("Ja")) {
                request.setAttribute("showQuote", value);
                request.setAttribute("checked", value);
            } else {
                request.setAttribute("checked", value);
            }
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
            return "index.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
        return "index.jsp";

    }
}
