package bdII.OODatabase;

import bdII.OODatabase.entities.Alumno;
import bdII.OODatabase.entities.Facultad;
import bdII.OODatabase.entities.MateriaCursada;
import bdII.OODatabase.service.AlumnoService;
import com.db4o.ObjectContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
@Slf4j
public class OoDatabaseApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(OoDatabaseApplication.class, args);
		ObjectContainer db = ctx.getBean(ObjectContainer.class);
		AlumnoService alumnoService = ctx.getBean(AlumnoService.class);
		Facultad facultad = ctx.getBean(Facultad.class);

		facultad.setNombre("Facultad de Ingenieria");

		MateriaCursada materiaCursada1 = new MateriaCursada();
		materiaCursada1.setNombre("Bases de Datos I");
		materiaCursada1.setNota(10);

		MateriaCursada materiaCursada2 = new MateriaCursada();
		materiaCursada2.setNombre("Ingles");
		materiaCursada2.setNota(4);

		MateriaCursada materiaCursada3 = new MateriaCursada();
		materiaCursada3.setNombre("Analisis Numerico");
		materiaCursada3.setNota(7);

		Alumno alumno1 = new Alumno();
		alumno1.setEdad(22);
		alumno1.setNombre("Tomas Luna");
		alumno1.addMateria(materiaCursada1);
		alumno1.addMateria(materiaCursada2);

		facultad.setAlumnos(List.of(alumno1));

		//Save
		alumnoService.save(alumno1);

		//Query
		alumnoService.queryAlumno(alumno1);

		//Modify and Update
		alumno1.addMateria(materiaCursada3);
		alumnoService.update(alumno1);

		//Query again to verify changes
		alumnoService.queryAlumno(alumno1);

		db.close();
	}

}
