package bdII.OODatabase.service;

import bdII.OODatabase.entities.Alumno;
import bdII.OODatabase.repository.AlumnoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public void queryAlumno(Alumno alumno){
        Optional<Alumno> alumnoRes = alumnoRepository.findByNameQBE(alumno);
        if(alumnoRes.isEmpty()){
            log.info("Alumno not found by QBE, retrying with NQ");
            alumnoRes = alumnoRepository.findByNameNQ(alumno.getNombre());
        }
        alumnoRes.ifPresent(value -> log.info(String.valueOf(value)));
    }

    public void create(Alumno alumno){
        alumnoRepository.save(alumno);
        log.info("Alumno: {} created",alumno.getNombre());
    }

    public void update(Alumno alumno){
        alumnoRepository.save(alumno);
        log.info("Alumno: {} updated",alumno.getNombre());
    }

    public void findAllByPromBiggerThan(Integer nota){
        List<Alumno> alumnoList = alumnoRepository.findAllByPromBiggerThan(nota);
        if(alumnoList != null && !alumnoList.isEmpty()){
            log.info("Alumnos con promedio mayor que {}:",nota);
            alumnoList.forEach(alumno -> log.info(alumno.toString()));
        }
    }

}
