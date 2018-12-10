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
            HashMap<String, String> shoppingcart = new HashMap<>();

            if (session.getAttribute("shoppingcart") != null) {
                shoppingcart = (HashMap<Fiets, Integer>) session.getAttribute("shoppingcart");
            }
            int aantalFietsen = shoppingcart.get()
            shoppingcart.put(fiets, aantal);
            session.setAttribute("shoppingcart", shoppingcart);
            response.sendRedirect("Controller?action=ProductOverview");
            return "productoverview.jsp";
        }else {
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("Controller?action=ProductOverview");
            view.forward(request, response);
            //response.sendRedirect("Controller?action=ProductOverview");
            return "productoverview.jsp";
        }
    }
}
