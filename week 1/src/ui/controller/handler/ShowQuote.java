package ui.controller.handler;

import ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowQuote extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String value = request.getParameter("confirm");
        Cookie cookie = new Cookie("quote", value);
        response.addCookie(cookie);
        response.sendRedirect("Controller?action=Home");
        return "index.jsp";

    }
}
