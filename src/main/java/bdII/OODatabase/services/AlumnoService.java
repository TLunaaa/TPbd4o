package bdII.OODatabase.services;

import bdII.OODatabase.entities.Alumno;
import bdII.OODatabase.entities.MateriaCursada;
import bdII.OODatabase.repositories.Alumno.AlumnoRepository;
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

    public void updateAlumnoMateria(String materia, Alumno alumno, Integer nota){
        Optional<Alumno> alumnoOptional = alumnoRepository.findByNameQBE(alumno);
        if(alumnoOptional.isPresent()){
            Alumno alumno1 = alumnoOptional.get();
            Optional<MateriaCursada> materiaOptional = alumno.getMaterias().stream()
                    .filter(materiaCursada1 -> materiaCursada1.getNombre().equals(materia))
                    .findFirst();
            if(materiaOptional.isPresent()){
                MateriaCursada materiaCursada = materiaOptional.get();
                materiaCursada.setNota(nota);
                log.info(String.valueOf(materiaCursada));
                alumnoRepository.save(alumno);
            }else{
                log.info("El alumno {} no cursa la materia {}",alumno.getNombre(),materia);
            }
        }else{
            log.info("No se encontro al alumno {}",alumno.getNombre());
        }
    }

}
