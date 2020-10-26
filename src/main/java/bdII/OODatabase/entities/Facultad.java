package bdII.OODatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Component
public class Facultad {

    private String nombre;
    private List<Alumno> alumnos;
    private List<String> materias;

    public void addAlumno(@NotNull Alumno alumno){
        this.alumnos.add(alumno);
    }

    public void addMateria(@NotNull @NotEmpty String materia){
        this.materias.add(materia);
    }

    public Double promedioPorMateria(@NotNull @NotEmpty String materia) throws Exception {
        if(!materias.contains(materia)){
            throw new Exception("No se encontro la materia solicitada");
        }
        return alumnos.stream().map(Alumno::getMaterias)
                .flatMap(Collection::stream)
                .filter(materiaCursada -> materiaCursada.getNombre().equals(materia))
                .mapToInt(MateriaCursada::getNota)
                .average()
                .orElse(Double.NaN);
    }


}
