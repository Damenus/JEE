package pl.darczuk.studia.java.entities;

import lombok.*;

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
@NamedQuery(name = Elf.FIND_ALL, query = "SELECT b FROM Elf b")
public class Elf {

    public static final String FIND_ALL = "Book.findAll";

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int numberArrows;

    @Enumerated(EnumType.STRING)
    private Bow typeBow;
}
