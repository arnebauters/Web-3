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
import java.util.Map;

public class BasketOverview extends ProductOverview {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int totalPrice = 0;
        int aantalItems = 0;
        HashMap<Integer, Integer> aantalfietsen = (HashMap<Integer, Integer>)session.getAttribute("aantalfietsen");
        HashMap<Integer, Fiets> shoppingcart = (HashMap<Integer, Fiets>)session.getAttribute("shoppingcart");
        if(shoppingcart != null){

            HashMap<Fiets, Integer> cart = new HashMap<>();
            for (Map.Entry<Integer, Fiets> fietsset: shoppingcart.entrySet()){
                for (Map.Entry<Integer, Integer> aantalset: aantalfietsen.entrySet()){
                    if (fietsset.getKey() == aantalset.getKey()){
                        cart.put(fietsset.getValue(), aantalset.getValue());
                    }
                }
            }
            session.setAttribute("cart", cart);
            for (Map.Entry<Fiets, Integer> set: cart.entrySet()){
                totalPrice += set.getKey().getPrijs() * set.getValue();
                aantalItems += set.getValue();
            }
        }
        request.setAttribute("size", aantalItems);
        request.setAttribute("TotalPrice", totalPrice);
        RequestDispatcher view = request.getRequestDispatcher("winkelMandje.jsp");
        view.forward(request, response);
        return "winkelMandje.jsp";
    }
}

