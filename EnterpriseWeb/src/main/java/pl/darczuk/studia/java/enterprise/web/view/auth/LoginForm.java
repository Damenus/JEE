package pl.darczuk.studia.java.enterprise.web.view.auth;

import lombok.Getter;
import lombok.Setter;
import pl.darczuk.studia.java.enterprise.web.view.JSFUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ManagedBean
@ViewScoped
public class LoginForm {
    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    String password;

    public String login() throws IOException {
        try {
            HttpServletRequest request = JSFUtils.getRequest();
            request.login(login, password);
            return "/index.xhtml?faces-redirect=true";

        } catch (ServletException ex) {
            JSFUtils.addMessage("login-form", "Login failed");
            return null;
        }
    }

}
