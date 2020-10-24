package bdII.OODatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class Facultad {

    private String nombre;
    private List<Alumno> alumnos;
    private List<String> materias;

    public void addAlumno(@NotNull Alumno alumno){
        this.alumnos.add(alumno);
    }

    public void addMateria(@NotNull String materia){
        this.materias.add(materia);
    }

    public float promedioPorMateria(String materia){
    }


}
