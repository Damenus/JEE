package pl.darczuk.studia.java.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.darczuk.studia.java.ForestService;
import pl.darczuk.studia.java.entities.Forest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@ViewScoped
@ManagedBean
@Log
public class EditForest implements Serializable {
    @ManagedProperty("#{forestService}")
    private ForestService forestService;

    @Getter
    @Setter
    private int forestId;

    @Getter
    @Setter
    private Forest forest;

    public void setForestService(ForestService forestService) {
        this.forestService = forestService;
    }

    public void init() {
        if (forest == null && forestId != 0) {
            forest = forestService.findForest(forestId);
        } else if (forest == null && forestId == 0) {
            forest = new Forest();
        }
        if (forest == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public String saveForest() {
        forestService.saveForest(forest);
        return "index?faces-redirect=true";
    }

}
