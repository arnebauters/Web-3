package ui.controller.handler;

import domain.model.Fiets;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class Checkout extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("name") == null){
            RequestDispatcher view = request.getRequestDispatcher("Controller?action=Home");
            view.forward(request, response);
            return "index.jsp";
        }
        HashMap<Integer, Fiets> shoppingcart = new HashMap<>();
        if (session.getAttribute("shoppingcart") != null) {
            shoppingcart = (HashMap<Integer, Fiets>) session.getAttribute("shoppingcart");
        }
        shoppingcart.clear();
        session.setAttribute("shoppingcart", shoppingcart);
        RequestDispatcher view = request.getRequestDispatcher("checkout.jsp");
        view.forward(request, response);
        return "checkout.jsp";
    }
}
