package pl.darczuk.studia.java.enterprise.web.view.auth;

import pl.darczuk.studia.java.enterprise.entities.User;
import pl.darczuk.studia.java.enterprise.users.UserService;
import pl.darczuk.studia.java.enterprise.web.view.JSFUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@ManagedBean
@SessionScoped
public class AuthContext {

    @EJB
    UserService userService;

    private ExternalContext getExternalContext() { return FacesContext.getCurrentInstance().getExternalContext(); }

    public Principal getUserPrincipal() {return getExternalContext().getUserPrincipal(); }

    public boolean isUserInRole(String role) { return getExternalContext().isUserInRole(role); }

    public boolean isAuthenticated() { return getUserPrincipal() != null; }

    public String getUserLogin() { return getUserPrincipal().getName(); }

    public User getCurrentUser() { return userService.findCurrentUser(); }

    public String logout() throws ServletException {
        HttpServletRequest request = JSFUtils.getRequest();
        request.logout();
        request.getSession().invalidate();
        return "/index.xhtml";
    }
}
