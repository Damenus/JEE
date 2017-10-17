package pl.darczuk.studia.java.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Forest {
    private int id;

    private int numberTrees;

    private SortedMap<Integer, Elf> comando = new TreeMap<>();

    public Forest(int id, int numberTrees) {
        this.setId(id);
        this.setNumberTrees(numberTrees);
    }

    public String removeElf(Elf elf) {
        comando.remove(elf.getId());
        return "index?faces-redirect=true";
    }

    public void saveElf(Elf elf, int forestId) {
        if (elf.getId() == 0) {
            if(comando.isEmpty() == false) {
                elf.setId(comando.lastKey() + 1);
            } else
                elf.setId(1);
        }

        if (this.id == forestId) {
            comando.put(elf.getId(), elf);
        } else {
            elf.setId(comando.lastKey() + 1);
            comando.put(elf.getId(), elf);
        }

    }

    public List<Elf> getElfs() { return new ArrayList<>(comando.values()); }

    public Elf findElf(int id) { return comando.get(id); }
}
