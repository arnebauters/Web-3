package ui.controller;

import domain.model.ShopService;

public class ControllerFactory {

    public RequestHandler getController(String key, ShopService model){ return createHandler(key, model);}

    public RequestHandler createHandler(String handlerName, ShopService model){
        RequestHandler handler = null;
        try{
            Class handlerClass = Class.forName("ui.controller.handler." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setService(model);
        }catch (Exception e){
            throw new RuntimeException("The requested page doesn't exist.");
        }
        return handler;
    }
}
