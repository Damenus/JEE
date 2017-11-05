package pl.darczuk.studia.java.enterprise.entities;

import lombok.*;
import pl.darczuk.studia.java.enterprise.entities.validators.Name;

import javax.persistence.*;

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
        @NamedQuery(name = Elf.REINFORCEMENT, query = "UPDATE Elf e SET e.numberArrows = e.numberArrows + :numberBow")
})
public class Elf {

    public static final String FIND_ALL = "Elf.findAll";
    public static final String REINFORCEMENT = "Elf.reinforcement";

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

    public Elf(String name, int numberArrows, Bow typeBow, Forest forest) {
        this.name = name;
        this.numberArrows = numberArrows;
        this.typeBow = typeBow;
        this.forest = forest;
    }
}
