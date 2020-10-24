package bdII.OODatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Alumno {

    private String nombre;
    private Integer edad;
    private List<MateriaCursada> materias;

    public float promedio(){

    }

    public float promedioSinAplazos(){

    }

    public Integer cantidadDeMaterias(){

    }



}
