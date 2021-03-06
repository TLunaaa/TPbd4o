package bdII.OODatabase.repositories.Alumno;

import bdII.OODatabase.entities.Alumno;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AlumnoRepository {

    Optional<Alumno> findByNameQBE(Alumno alumno);

    Optional<Alumno> findByNameNQ(String nombre);

    Optional<Alumno> findByNameSODA(String nombre);

    List<Alumno> findAllByPromBiggerThan(Integer nota);

    void save(Alumno alumno);

}
