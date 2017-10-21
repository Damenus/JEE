package pl.darczuk.studia.java.entities;

import lombok.*;

import java.util.*;
import javax.persistence.*;

@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="forests")
@NamedQueries({
        @NamedQuery(
                name = Forest.FIND_ALL,
                query = "SELECT f FROM Forest f"
        ),
        @NamedQuery(
                name = Forest.FIND_BY_NAME,
                query = "SELECT f FROM Forest f WHERE f.id = :id"
        ),
        @NamedQuery(
                name = Forest.REMOVE_BY_ID,
                query = "DELETE FROM Forest f WHERE f.id = :id"
        ),
        @NamedQuery(
                name = Forest.INSERT,
                query = "INSERT INTO Forest f WHERE f.id = :id"
        )
})
public class Forest {

    public static final String FIND_ALL = "Forest.findAll";
    public static final String FIND_BY_NAME = "Forest.findByName";
    public static final String REMOVE_BY_ID = "Forest.removeById";
    public static final String INSERT = "Forest.insert";


    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int numberTrees;

    @OneToMany
    private Map<Integer, Elf> comando = new TreeMap<>();

    public Forest(int id, int numberTrees) {
        this.setId(id);
        this.setNumberTrees(numberTrees);
    }

    public String removeElf(Elf elf) {
        comando.remove(elf.getId());
        return "index?faces-redirect=true";
    }

    public void saveElf(Elf elf, int forestId) {
//        if (elf.getId() == 0) {
//            if(comando.isEmpty() == false) {
//                elf.setId(comando.lastKey() + 1);
//            } else
//                elf.setId(1);
//        }
//
//        if (this.id == forestId) {
//            comando.put(elf.getId(), elf);
//        } else {
//            elf.setId(comando.lastKey() + 1);
//            comando.put(elf.getId(), elf);
//        }

    }

    public List<Elf> getElfs() { return new ArrayList<>(comando.values()); }

    public Elf findElf(int id) { return comando.get(id); }
}
