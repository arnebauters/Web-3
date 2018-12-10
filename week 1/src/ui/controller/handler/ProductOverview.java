package ui.controller.handler;

import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ProductOverview extends RequestHandler {
public ProductOverview(){
}
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", getService().getProducts());
        request.setAttribute("duurste", getService().getDuurste());
        RequestDispatcher view = request.getRequestDispatcher("productoverview.jsp");
        view.forward(request, response);
        return "productoverview.jsp";
    }
}
