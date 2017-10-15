package pl.darczuk.studia.java.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Forest {
    private int id;

    private int numberTrees;

    private List<Elf> comando = new ArrayList<>();

    public void removeElf(Elf elf) {
        comando.remove(elf);
    }
}
