package ui.controller.handler;

import domain.model.Fiets;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class AddBasket extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("fiets"));
        ArrayList<String> errors = new ArrayList<>();
        int aantal = 1;
        try {
            aantal = Integer.parseInt(request.getParameter("aantal"));
            if (aantal<=0){
                errors.add("Quantity needs to be bigger then 0");
            }
        }catch (Exception e){
            errors.add("Quantity needs to be a number.");
        }
        if (errors.size() == 0) {
            Fiets fiets = getService().getFiets(id);
            HttpSession session = request.getSession();
            HashMap<Integer, Fiets> shoppingcart = new HashMap<>();
            HashMap<Integer, Integer> aantalFietsen = new HashMap<>();

            if (session.getAttribute("shoppingcart") != null) {
                shoppingcart = (HashMap<Integer, Fiets>) session.getAttribute("shoppingcart");
                aantalFietsen = (HashMap<Integer, Integer>) session.getAttribute("aantalfietsen");
            }
            shoppingcart.put(id, fiets);
            aantalFietsen.put(id, aantal);
            session.setAttribute("shoppingcart", shoppingcart);
            session.setAttribute("aantalfietsen", aantalFietsen);
            response.sendRedirect("Controller?action=ProductOverview");
            return "productoverview.jsp";
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("errors", errors);
            response.sendRedirect("Controller?action=ProductOverview");
            return "productoverview.jsp";
        }
    }
}
