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

        Forest forest1 = new Forest(1,1000,asList(elf1));
        Forest forest2 = new Forest(2,10,asList(elf2));

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

    public void removeForest(Forest forest) {
        forests.remove(forest.getId());
    }
}
