package pl.darczuk.studia.java.enterprise.web.converters;

import pl.darczuk.studia.java.enterprise.forest.ForestService;
import pl.darczuk.studia.java.enterprise.entities.Forest;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@RequestScoped
public class ForestConverter implements Converter {

    @EJB
    private ForestService forestService;

    public void setForestService(ForestService forestService) { this.forestService = forestService; }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }

        Forest forest = forestService.findForest(Integer.valueOf(s));

        if (forest == null) {
            facesContext.getExternalContext().setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
            facesContext.responseComplete();
        }

        return forest;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }
        Forest forest = (Forest) o;
        return forest.getId() != null ? Integer.toString(forest.getId()) : null;
    }
}
