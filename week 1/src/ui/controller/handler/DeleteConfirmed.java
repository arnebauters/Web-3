package ui.controller.handler;

import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteConfirmed extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getParameter("productId").trim().isEmpty()) {
            if (request.getParameter("confirm").equals("Nee")) {
            }
            else {
                int productId = Integer.parseInt(request.getParameter("productId"));
                getService().deleteFiets(productId);
            }
            response.sendRedirect("Controller?action=ProductOverview");
            return "productOverview.jsp";

        } else {
            if (request.getParameter("confirm").equals("Nee")) {

            }
            else {
                String personid = request.getParameter("person");
                getService().deletePerson(personid);
            }
            response.sendRedirect("Controller?action=PersonOverview");
            return "personOverview.jsp";
        }
    }
}
