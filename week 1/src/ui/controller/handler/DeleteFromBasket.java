package ui.controller.handler;

import domain.model.Fiets;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class DeleteFromBasket extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("fiets"));
        Fiets fiets = getService().getFiets(id);
        HttpSession session = request.getSession();
       // HashMap<Fiets, Integer> shoppingcart = new HashMap<>();
        HashMap<Integer, Fiets> shoppingcart = new HashMap<>();
        if (session.getAttribute("shoppingcart") != null) {
           // shoppingcart = (HashMap<Fiets, Integer>) session.getAttribute("shoppingcart");
            shoppingcart = (HashMap<Integer, Fiets>) session.getAttribute("shoppingcart");
        }
        shoppingcart.remove(id);

        session.setAttribute("shoppingcart", shoppingcart);

        response.sendRedirect("Controller?action=BasketOverview");
        return "winkelMandje.jsp";
    }
}
