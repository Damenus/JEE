package pl.darczuk.studia.java.enterprise.view;

import pl.darczuk.studia.java.enterprise.ForestService;
import pl.darczuk.studia.java.enterprise.entities.Elf;
import pl.darczuk.studia.java.enterprise.entities.Forest;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class ListForests implements Serializable {

    @EJB
    private ForestService forestService;

    private List<Forest> forests;

    public List<Forest> getForests() {
        if (forests == null) {
            forests = forestService.findAllForest();
        }
        return forests;
    }

    public String removeForest(Forest forest) {
        forestService.removeForest(forest);
        forests.remove(forest);
        return "index?faces-redirect=true";
    }

    public String removeElf(Elf elf) {
        forestService.removeElf(elf);
        //forests.remove(elf);
        return "index?faces-redirect=true";
    }

    public String reinforcement(int numberBow) {
        forestService.reinforcement(numberBow);
        return "index?faces-redirect=true";
    }

}
