package bdII.OODatabase.repository;

import bdII.OODatabase.entities.Alumno;

public interface AlumnoRepository {

    Alumno findAlumnoByNameQBE(Alumno alumno);

    Alumno findAlumnoByNameNQ(String nombre);

    Alumno findAlumnoByNameSOAP(String nombre);

    void save(Alumno alumno);

}
