package pl.darczuk.studia.java.entities;

import lombok.*;

import java.lang.reflect.Array;
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

    @OneToMany(mappedBy = "forest", cascade=CascadeType.ALL, orphanRemoval=true)
//    @JoinTable(name = "forests_elfs",
//            joinColumns = {
//                    @JoinColumn(name = "elfs", referencedColumnName = "id")},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "forests", referencedColumnName = "id")})
    private List<Elf> elfs;

    public Forest(int id, int numberTrees) {
        this.setId(id);
        this.setNumberTrees(numberTrees);
    }

    public String removeElf(Elf elf) {
        elfs.remove(elf);
        return "index?faces-redirect=true";
    }

    public void saveElf(Elf elf, int forestId) {
        elfs.add(elf);
//        if (elf.getId() == null) {
//            if(comando.isEmpty() == false) {
//                elf.setId(comando.lastKey() + 1);
//            } else
//                elf.setId(1);
//        }

//        if (this.id == forestId) {
//            comando.put(elf.getId(), elf);
//        } else {
//            elf.setId(comando.lastKey() + 1);
//            comando.put(elf.getId(), elf);
//        }

    }

    public List<Elf> getElfs() { return elfs; }

    public Elf findElf(int id) { return elfs.get(id); }
}
