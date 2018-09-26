package ui.controller;

import domain.db.DbException;
import domain.db.PersonDbInMemory;
import domain.model.DomainException;
import domain.model.Person;

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
        if(request.getParameter("action")!= null) {
            command= request.getParameter("action");
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
                destination = sign(request, response);
                break;

            case "add":
                destination = add(request, response);
                break;

            default:
                destination = "index.jsp";
        }

        request.getRequestDispatcher(destination).forward(request, response);

    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        Person persoon = new Person();
        ArrayList<String> errors = new ArrayList<>();

        getUserid(persoon, request, errors);
        getEmail(persoon, request, errors);
        getFirstName(persoon, request, errors);
        getLastName(persoon, request, errors);
        getPassword(persoon, request, errors);

        if(errors.size() == 0){
            try {
                db.add(persoon);
                return overview(request, response);
            }
            catch (DbException e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return sign(request, response);
            }
        }
        else{
            request.setAttribute("errors", errors);
            return sign(request, response);
        }
    }

    private void getLastName(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try{
            persoon.setLastName(lastName);
            request.setAttribute("lastName", "has-succes");
            request.setAttribute("waardelastName", lastName);
        }
        catch (DomainException e){
            errors.add(e.getMessage());
            request.setAttribute("lastName", "has-error");
        }
    }

    private void getPassword(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String pass = request.getParameter("password");
        try{
            persoon.setPassword(pass);
            request.setAttribute("password", "has-succes");
            request.setAttribute("waardepassword", pass);
        }
        catch (DomainException e){
            errors.add(e.getMessage());
            request.setAttribute("password", "has-error");
        }
    }

    private void getFirstName(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try{
            persoon.setFirstName(firstName);
            request.setAttribute("firstName", "has-succes");
            request.setAttribute("waardefirstName", firstName);
        }
        catch (DomainException e){
            errors.add(e.getMessage());
            request.setAttribute("firstName", "has-error");
        }
    }

    private void getEmail(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try{
            persoon.setEmail(email);
            request.setAttribute("email", "has-succes");
            request.setAttribute("waardeemail", email);
        }
        catch (DomainException e){
            errors.add(e.getMessage());
            request.setAttribute("email", "has-error");
        }
    }

    private void getUserid(Person persoon, HttpServletRequest request, ArrayList<String> errors) {
        String userid = request.getParameter("userid");
        try{
            persoon.setUserid(userid);
            request.setAttribute("userid", "has-succes");
            request.setAttribute("waardeuserid", userid);
        }
        catch (DomainException e){
            errors.add(e.getMessage());
            request.setAttribute("userid", "has-error");
        }
    }

    private String sign(HttpServletRequest request, HttpServletResponse response) {

        return "signUp.jsp";
    }

    private String overview(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("lijst", db.getAll());
        return "personoverview.jsp";
    }
}
