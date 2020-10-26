package bdII.OODatabase;

import bdII.OODatabase.entities.Alumno;
import bdII.OODatabase.entities.Facultad;
import bdII.OODatabase.entities.MateriaCursada;
import bdII.OODatabase.repository.Db4oRepostiory;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@Slf4j
public class OoDatabaseApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(OoDatabaseApplication.class, args);
		Facultad facultad = ctx.getBean(Facultad.class);
		Db4oRepostiory db4oRepostiory = ctx.getBean(Db4oRepostiory.class);
		ObjectContainer db = db4oRepostiory.getConnection();

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

		facultad.setAlumnos(List.of(alumno1));
		db.store(alumno1);
		log.info("Alumno stored");

		log.info("Fetching data:");
		ObjectSet result = db.queryByExample(alumno1);
		log.info(String.valueOf(result));

		db.close();
	}

}
