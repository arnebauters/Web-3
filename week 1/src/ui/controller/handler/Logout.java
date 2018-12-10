package ui.controller.handler;

import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
            HttpSession session = request.getSession();
            session.invalidate();

            response.sendRedirect("Controller?action=Home");
            return "index.jsp";
    }
}
