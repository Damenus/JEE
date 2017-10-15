package pl.darczuk.studia.java.entities;

import lombok.*;

@ToString(of = "name")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Elf {
    private int id;

    private String name;

    private int numberArrows;

    private Bow typeBow;
}
