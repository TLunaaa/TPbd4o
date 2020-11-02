package bdII.OODatabase.repository;

import bdII.OODatabase.entities.Alumno;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AlumnoRepositoryImpl implements AlumnoRepository{

    private final ObjectContainer db;

    public AlumnoRepositoryImpl(ObjectContainer objectContainer) {
        this.db = objectContainer;
    }

    @Override
    public Alumno findAlumnoByNameQBE(Alumno alumno) {
        log.info("Query by example (QBE):");
        ObjectSet<Alumno> qbe = db.queryByExample(alumno);
        return qbe.isEmpty() ? null : qbe.get(0);
    }

    @Override
    public Alumno findAlumnoByNameNQ(String nombre) {
        log.info("Native Query (NQ):");
        ObjectSet<Alumno> nq = db.query(new Predicate<Alumno>() {
            @Override
            public boolean match(Alumno alumno) {
                return alumno.getNombre().contains(nombre);
            }
        });
        return nq.isEmpty() ? null : nq.get(0);
    }

    @Override
    public Alumno findAlumnoByNameSOAP(String nombre) {
        log.info("SODA query API (SODA):");
        Query query = db.query();
        query.constrain(Alumno.class);
        query.descend("nombre").constrain(nombre);
        ObjectSet<Alumno> soda = query.execute();
        return soda.get(0);
    }

    @Override
    public void save(Alumno alumno) {
        db.store(alumno);
        log.info("Alumno stored");
    }
}
