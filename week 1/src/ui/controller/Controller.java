package ui.controller;

import domain.db.*;
import domain.model.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private ShopService db;
    private ControllerFactory controllerFactory = new ControllerFactory();

    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();

        Properties properties = new Properties();
        properties.setProperty("user", context.getInitParameter("user"));
        properties.setProperty("password", context.getInitParameter("password"));
        properties.setProperty("ssl", context.getInitParameter("ssl"));
        properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
        properties.setProperty("sslmode", context.getInitParameter("sslmode"));
        properties.setProperty("currentSchema", context.getInitParameter("currentSchema"));
        properties.setProperty("url", context.getInitParameter("url"));
        db = new ShopService(properties);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String destination = "index.jsp";
         if(action != null){
             RequestHandler handler;
             handler = controllerFactory.getController(action, db);
             try {
                 destination = handler.handleRequest(request, response);
             }catch (NotAuthorizedException e){
                 request.setAttribute("notAuthorized", "You have insufficient rights to have a look at the requested page");
                    request.getRequestDispatcher("index.jsp").forward(request,response);
             }
         }
         request.setAttribute("action", action);

    }


}
