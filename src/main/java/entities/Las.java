package entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Las {
    private int id;

    private int liczbaDrzew;

    private List<Elf> komando = new ArrayList<>();
}
