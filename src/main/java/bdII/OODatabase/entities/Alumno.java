package bdII.OODatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Alumno {

    private String nombre;
    private Integer edad;
    private List<MateriaCursada> materias = new ArrayList<>();

    public Double promedio(){
        return materias.stream()
                .mapToInt(MateriaCursada::getNota)
                .average().orElse(Double.NaN);
    }

    public Double promedioSinAplazos(){
        return materias.stream()
                .mapToInt(MateriaCursada::getNota)
                .filter(value -> value < 4)
                .average()
                .orElse(Double.NaN);
    }

    public Integer cantidadDeMaterias(){
        return materias.size();
    }

    public void addMateria(MateriaCursada materiaCursada){
        this.materias.add(materiaCursada);
    }


}
