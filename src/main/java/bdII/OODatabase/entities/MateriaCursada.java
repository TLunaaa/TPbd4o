package bdII.OODatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class MateriaCursada {

    private String nombre;
    private float nota;
    private LocalDate fecha;
}
