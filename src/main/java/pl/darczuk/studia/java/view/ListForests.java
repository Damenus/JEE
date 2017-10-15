package pl.darczuk.studia.java.view;

import pl.darczuk.studia.java.ForestService;
import pl.darczuk.studia.java.entities.Forest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class ListForests implements Serializable {

    @ManagedProperty("#{forestService}")
    private ForestService forestService;

    public void setForestService(ForestService forestService) {this.forestService = forestService; }

    private List<Forest> forests;

    public List<Forest> getForests() {
        if (forests == null) {
            forests = forestService.findAllForest();
        }
        return forests;
    }
}
