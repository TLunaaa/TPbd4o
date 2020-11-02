package bdII.OODatabase.service;

import bdII.OODatabase.entities.Alumno;
import bdII.OODatabase.repository.AlumnoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public void queryAlumno(Alumno alumno){
        Alumno alumnoRes = alumnoRepository.findAlumnoByNameQBE(alumno);
        if(alumnoRes == null){
            log.info("Alumno not found by QBE, trying by NQ");
            alumnoRes = alumnoRepository.findAlumnoByNameNQ(alumno.getNombre());
        }
        log.info(String.valueOf(alumnoRes));
    }

    public void save(Alumno alumno){
        alumnoRepository.save(alumno);
    }

    public void update(Alumno alumno){
        alumnoRepository.save(alumno);
    }

}
