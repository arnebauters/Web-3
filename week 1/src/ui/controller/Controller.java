package ui.controller;

import domain.db.*;
import domain.model.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private PersonDbInMemory db = new PersonDbInMemory();
    private FietsDB productdb = new FietsDB();

    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "home";
        if (request.getParameter("action") != null) {
            command = request.getParameter("action");
        }

        String destination;

        switch (command) {
            case "home":
                destination = "index.jsp";
                break;

            case "overview":
                destination = overview(request, response);
                break;
            case "signUp":
                destination = "signUp.jsp";
                break;

            case "adduser":
                destination = adduser(request, response);
                break;

            case "productoverview":
                destination = productoverview(request, response);
                break;
            case "formproduct":
                destination = "addproduct.jsp";
                break;
            case "addproduct":
                destination = addproduct(request, response);
                break;
            case "aanpassen":
                destination = aanpassen(request, response);
                break;
            case "updateconfirmed":
                destination = updateConfirmed(request, response);
                break;
            case "delete":
                destination = delete(request, response);
                break;
            case "deleteconfirmed":
                destination = deleteConfirmed(request, response);
                break;
            default:
                destination = "index.jsp";
        }

        request.getRequestDispatcher(destination).forward(request, response);

    }

    private String deleteConfirmed(HttpServletRequest request, HttpServletResponse response) {
        if(!request.getParameter("fiets").trim().isEmpty()) {
            if (request.getParameter("confirm").equals("Nee")) {
                return productoverview(request, response);
            }
            String naam = request.getParameter("fiets");
            productdb.verwijder(naam);
            return productoverview(request, response);
        }else{
            if(request.getParameter("confirm").equals("Nee")){
                return overview(request, response);
            }
            String personid = request.getParameter("person");
            db.delete(personid);
            return overview(request, response);
        }
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("fiets").equals("none")) {
            Person person = new Person();
            person = db.get(request.getParameter("person"));
            request.setAttribute("person", person);
        }else{
            Fiets fiets = new Fiets();
            fiets = productdb.getFiets(request.getParameter("fiets"));
            request.setAttribute("fiets", fiets);
        }
        return "deleteconfirmation.jsp";
    }

    private String updateConfirmed(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Fiets fiets = new Fiets();
        fiets = productdb.getFiets(request.getParameter("naam"));
        Fiets klone = new Fiets();

        getName(klone, request, errors);
        getMerk(klone, request, errors);
        getPrijs(klone, request, errors);
        if (errors.size() == 0) {
            fiets.setNaam(klone.getNaam());
            fiets.setMerk(klone.getMerk());
            fiets.setPrijs(klone.getPrijs());
            return productoverview(request, response);
        } else {
            request.setAttribute("fiets", fiets);
            request.setAttribute("errors", errors);
            return "update.jsp";
        }
    }

    private String aanpassen(HttpServletRequest request, HttpServletResponse response) {
        Fiets fiets = new Fiets();
        fiets = productdb.getFiets(request.getParameter("naam"));
        request.setAttribute("fiets", fiets);
        return "update.jsp";
    }


    private String addproduct(HttpServletRequest request, HttpServletResponse response) {
        Fiets fiets = new Fiets();
        ArrayList<String> errors = new ArrayList<>();

        getName(fiets, request, errors);
        getMerk(fiets, request, errors);
        getPrijs(fiets, request, errors);

        if (errors.size() == 0) {
            try {
                productdb.voegFietsToe(fiets);
                return productoverview(request, response);
            } catch (DbException e) {
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "addproduct.jsp";
            }
        } else {
            request.setAttribute("errors", errors);
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

    private String productoverview(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("products", productdb.fietsen());
        return "productoverview.jsp";
    }

    private String adduser(HttpServletRequest request, HttpServletResponse response) {
        Person persoon = new Person();
        ArrayList<String> errors = new ArrayList<>();

        getUserid(persoon, request, errors);
        getEmail(persoon, request, errors);
        getFirstName(persoon, request, errors);
        getLastName(persoon, request, errors);
        getPassword(persoon, request, errors);

        if (errors.size() == 0) {
            try {
                db.add(persoon);
                return overview(request, response);
            } catch (DbException e) {
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "signUp.jsp";
            }
        } else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }

    private void getLastName(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            persoon.setLastName(lastName);
            request.setAttribute("lastName", "has-succes");
            request.setAttribute("waardelastName", lastName);
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("lastName", "has-error");
        }
    }

    private void getPassword(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String pass = request.getParameter("password");
        try {
            persoon.setPassword(pass);
            request.setAttribute("password", "has-succes");
            request.setAttribute("waardepassword", pass);
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("password", "has-error");
        }
    }

    private void getFirstName(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            persoon.setFirstName(firstName);
            request.setAttribute("firstName", "has-succes");
            request.setAttribute("waardefirstName", firstName);
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("firstName", "has-error");
        }
    }

    private void getEmail(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            persoon.setEmail(email);
            request.setAttribute("email", "has-succes");
            request.setAttribute("waardeemail", email);
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("email", "has-error");
        }
    }

    private void getUserid(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String userid = request.getParameter("userid");
        try {
            persoon.setUserid(userid);
            request.setAttribute("userid", "has-succes");
            request.setAttribute("waardeuserid", userid);
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("userid", "has-error");
        }
    }

    private String overview(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("lijst", db.getAll());
        return "personoverview.jsp";
    }
}
