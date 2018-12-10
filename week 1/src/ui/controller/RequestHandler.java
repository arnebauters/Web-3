package ui.controller;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class RequestHandler {
    private ShopService service;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public void setService(ShopService service) {
        this.service = service;
    }

    public ShopService getService() {
        return service;
    }

}
