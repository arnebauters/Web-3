package ui.controller.handler;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Fiets;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AddProduct extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Fiets fiets = new Fiets();
        ArrayList<String> errors = new ArrayList<>();

        getName(fiets, request, errors);
        getMerk(fiets, request, errors);
        getPrijs(fiets, request, errors);

        if (errors.size() == 0) {
            try {
                getService().addProduct(fiets);
                response.sendRedirect("Controller?action=ProductOverview");
                return "productoverview.jsp";
            } catch (DbException e) {
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                response.sendRedirect("addproduct.jsp");
                return "addproduct.jsp";
            }
        } else {
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("addproduct.jsp");
            view.forward(request, response);
            //response.sendRedirect("addproduct.jsp");
            return "addproduct.jsp";
        }
    }
    private void getPrijs(Fiets fiets, HttpServletRequest request, ArrayList<String> errors) {
        String prijsString = request.getParameter("Price");
        double prijs = 0;
        try {
            prijs = Double.parseDouble(prijsString);
            fiets.setPrijs(prijs);
            request.setAttribute("prijs", "has-succes");
            request.setAttribute("waardeprijs", prijs);
        } catch (NumberFormatException e) {
            errors.add("Give a number for the price.");
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("prijs", "has-error");
        }
    }

    private void getMerk(Fiets fiets, HttpServletRequest request, ArrayList<String> errors) {
        String merk = request.getParameter("Brand");
        try {
            fiets.setMerk(merk);
            request.setAttribute("merk", "has-succes");
            request.setAttribute("waardemerk", merk);
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("merk", "has-error");
        }
    }

    private void getName(Fiets fiets, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("Name");
        try {
            fiets.setNaam(naam);
            request.setAttribute("naam", "has-succes");
            request.setAttribute("waardenaam", naam);
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("naam", "has-error");
        }
    }
}
