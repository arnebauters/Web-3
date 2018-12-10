package ui.controller.handler;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Role;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AddUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person persoon = new Person();
        ArrayList<String> errors = new ArrayList<>();

        getUserid(persoon, request, errors);
        getEmail(persoon, request, errors);
        getFirstName(persoon, request, errors);
        getLastName(persoon, request, errors);
        getPassword(persoon, request, errors);
        if (request.getParameter("role") == null){
            persoon.setRole(Role.CUSTOMER);
        }
        else {
            persoon.setRole(Role.valueOf(request.getParameter("role")));
        }

        if (errors.size() == 0) {
            try {
                getService().addPerson(persoon);
                response.sendRedirect("Controller?action=PersonOverview");
                return "personoverview.jsp";
            } catch (DbException e) {
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                response.sendRedirect("signUp.jsp");
                return "signUp.jsp";
            }
        } else {
            request.setAttribute("errors", errors);
            //response.sendRedirect("Controller?action=SignUp");
            RequestDispatcher view = request.getRequestDispatcher("signUp.jsp");
            view.forward(request, response);
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
            persoon.setPasswordHashed(pass);
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
}
