package ui.controller.handler;

import domain.model.Fiets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class BasketOverview extends ProductOverview {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int totalPrice = 0;
        HashMap<Fiets, Integer> shoppingcart = (HashMap<Fiets, Integer>)session.getAttribute("shoppingcart");
        if(shoppingcart != null){
            request.setAttribute("size",shoppingcart.values().size());
            session.setAttribute("cart", shoppingcart);
            /*for (Fiets fiets: shoppingcart.values()){
                totalPrice+= fiets.getPrijs();
            }*/
        }
        request.setAttribute("TotalPrice", totalPrice);
        RequestDispatcher view = request.getRequestDispatcher("winkelMandje.jsp");
        view.forward(request, response);
        return "winkelMandje.jsp";
    }
}

