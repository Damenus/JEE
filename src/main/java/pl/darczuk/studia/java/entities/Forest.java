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

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int numberTrees;

    @OneToMany(mappedBy = "forest", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Elf> elfs;

}
