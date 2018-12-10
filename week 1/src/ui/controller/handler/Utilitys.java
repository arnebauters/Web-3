package ui.controller.handler;

import domain.model.Person;
import domain.model.Role;
import ui.controller.NotAuthorizedException;

import javax.servlet.http.HttpServletRequest;

public class Utilitys {
    public static void checkRole(HttpServletRequest request, Role[] roles){
        boolean found = false;
        Person person = (Person) request.getSession().getAttribute("name");
        if (person != null)
            for (Role rol: roles){
                if (person.getRole().equals(rol))
                found = true;
            }
            if (!found)
                throw new NotAuthorizedException();
    }
}
