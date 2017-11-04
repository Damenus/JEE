package pl.darczuk.studia.java.enterprise.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.darczuk.studia.java.enterprise.ForestService;
import pl.darczuk.studia.java.enterprise.entities.Bow;
import pl.darczuk.studia.java.enterprise.entities.Elf;
import pl.darczuk.studia.java.enterprise.entities.Forest;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean
@Log
public class EditElf implements Serializable {

    @ManagedProperty("#{forestService}")
    private ForestService forestService;

    @Getter
    @Setter
    private Elf elf;

    @Getter
    @Setter
    private Forest forest;

    private List<SelectItem> forestsAsSelectItem;

    private List<SelectItem> typeBowAsSelectItem;

    public void setForestService(ForestService forestService) {this.forestService = forestService; }

    public void init() {
        if (elf == null) {
            elf = new Elf();
        }
    }

    public List<SelectItem> getForestsAsSelectItem() {
        if (forestsAsSelectItem == null) {
            forestsAsSelectItem = new ArrayList<>();
            for (Forest forest : forestService.findAllForest()) {
                forestsAsSelectItem.add(new SelectItem(forest, String.valueOf(forest.getId())));
            }
        }
        return forestsAsSelectItem;
    }

    public List<SelectItem> getTypeBowAsSelectItem() {
        if (typeBowAsSelectItem == null) {
            typeBowAsSelectItem = new ArrayList<>();
            for (Enum typeBow : Bow.values()) {
                typeBowAsSelectItem.add(new SelectItem(typeBow, typeBow.toString()));
            }
        }
        return typeBowAsSelectItem;
    }

    public String saveElf() {
       forestService.saveElf(elf);
       return "index?faces-redirect=true";
    }

}
