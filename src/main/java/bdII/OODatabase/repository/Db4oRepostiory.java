package bdII.OODatabase.repository;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
@Slf4j
public class Db4oRepostiory {

    private static final String DB4OFILENAME = "src/main/resources/db4oFile.db4o";

    public ObjectContainer getConnection(){
        new File(DB4OFILENAME).delete();
         return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
    }



}
