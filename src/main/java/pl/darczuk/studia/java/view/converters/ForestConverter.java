package pl.darczuk.studia.java.view.converters;

import pl.darczuk.studia.java.ForestService;
import pl.darczuk.studia.java.entities.Elf;

import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import pl.darczuk.studia.java.ForestService;
import pl.darczuk.studia.java.entities.Elf;
import pl.darczuk.studia.java.entities.Forest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@RequestScoped
public class ForestConverter implements Converter {

    @ManagedProperty("#{forestService}")
    private ForestService forestService;

    public void setForestService(ForestService forestService) { this.forestService = forestService; }


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if ("---".equals(s)) {
            return null;
        }
        return forestService.findForest(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return "---";
        }
        return ((Forest) o).getId() + "";
    }
}
