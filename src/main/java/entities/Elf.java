package entities;

import lombok.*;

@ToString(of = "imie")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Elf {
    private int id;

    private String imie;

    private int liczbaStrzal;

    private Luk typLuku;
}
