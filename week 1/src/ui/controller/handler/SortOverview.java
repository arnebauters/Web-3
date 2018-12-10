package ui.controller.handler;

import ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SortOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String value = request.getParameter("sort");
        Cookie cookie = new Cookie("SortingMethod", value);
        response.addCookie(cookie);
        response.sendRedirect("Controller?action=PersonOverview");
        return "personoverview.jsp";
    }
}
