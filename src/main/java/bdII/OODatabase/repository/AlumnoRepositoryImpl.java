package bdII.OODatabase.repository;

import bdII.OODatabase.entities.Alumno;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class AlumnoRepositoryImpl implements AlumnoRepository{

    private final ObjectContainer db;

    public AlumnoRepositoryImpl(ObjectContainer objectContainer) {
        this.db = objectContainer;
    }

    @Override
    public Optional<Alumno> findByNameQBE(Alumno alumno) {
        log.info("Query by example (QBE):");
        ObjectSet<Alumno> qbe = db.queryByExample(alumno);
        return qbe.isEmpty() ? Optional.empty() : Optional.ofNullable(qbe.get(0));
    }

    @Override
    public Optional<Alumno> findByNameNQ(String nombre) {
        log.info("Native Query (NQ):");
        ObjectSet<Alumno> nq = db.query(new Predicate<Alumno>() {
            @Override
            public boolean match(Alumno alumno) {
                return alumno.getNombre().contains(nombre);
            }
        });
        return nq.isEmpty() ? Optional.empty() : Optional.ofNullable(nq.get(0));
    }

    @Override
    public Optional<Alumno> findByNameSODA(String nombre) {
        log.info("SODA query API (SODA):");
        Query query = db.query();
        query.constrain(Alumno.class);
        query.descend("nombre").constrain(nombre);
        ObjectSet<Alumno> soda = query.execute();
        return soda.isEmpty() ? Optional.empty() : Optional.ofNullable(soda.get(0));
    }

    @Override
    public List<Alumno> findAllByPromBiggerThan(Integer nota) {
        return db.query(new Predicate<Alumno>() {
            @Override
            public boolean match(Alumno alumno) {
                return alumno.promedio() >= nota.doubleValue();
            }
        });
    }

    @Override
    public void save(Alumno alumno) {
        db.store(alumno);
    }
}
