package pl.darczuk.studia.java.entities;

import lombok.*;
import pl.darczuk.studia.java.entities.validators.Name;

import javax.persistence.*;
import java.awt.print.Book;

@ToString(of = "name")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "elfs")
@NamedQueries({
        @NamedQuery(name = Elf.FIND_ALL, query = "SELECT e FROM Elf e"),
})
public class Elf {

    public static final String FIND_ALL = "Elf.findAll";

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Name
    @Column
    private String name;

    @Column
    private int numberArrows;

    @Enumerated(EnumType.STRING)
    private Bow typeBow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forest_id", nullable = false)
    private Forest forest;
}
