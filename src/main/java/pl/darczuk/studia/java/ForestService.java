package pl.darczuk.studia.java;

import pl.darczuk.studia.java.entities.Elf;
import pl.darczuk.studia.java.entities.Forest;
import pl.darczuk.studia.java.entities.Bow;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.*;

@ManagedBean
@ApplicationScoped
public class ForestService implements Serializable {

    private final SortedMap<Integer, Forest> forests;

    public ForestService() {
        Elf elf1 = new Elf(1,"Rosh",13, Bow.EPIC);
        Elf elf2 = new Elf(1,"Lula",11, Bow.EPIC);

        Forest forest1 = new Forest(1,1000);
        Forest forest2 = new Forest(2,10);

        forest1.saveElf(elf1, forest1.getId());
        forest2.saveElf(elf2, forest2.getId());

        forests = new TreeMap<>();
        forests.put(forest1.getId(), forest1);
        forests.put(forest2.getId(), forest2);
    }

    private List<Elf> asList(Elf... elfs) {
        List<Elf> list = new ArrayList<>();
        list.addAll(Arrays.asList(elfs));
        return list;
    }

    public List<Forest> findAllForest() {
        return new ArrayList<>(forests.values());
    }

    public Forest findForest(int forestId) { return forests.get(forestId); }

    public void saveForest(Forest forest) {
        if (forest.getId() == 0) {
            forest.setId(forests.lastKey() + 1);
        }
        forests.put(forest.getId(), forest);
    }

    public void saveElf(Elf elf, int oldForestId, int forestId) {
        if (forestId == oldForestId)
            findForest(forestId).saveElf(elf, forestId);
        else {
            Elf celf = new Elf(elf.getId(), elf.getName(), elf.getNumberArrows(), elf.getTypeBow());
            findForest(forestId).saveElf(celf, oldForestId);
            findForest(oldForestId).removeElf(elf);
        }
    }

    public String removeForest(Forest forest) {
        forests.remove(forest.getId());
        return "index?faces-redirect=true";
    }
}
