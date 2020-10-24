package bdII.OODatabase.repository;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import org.springframework.stereotype.Repository;

@Repository
public class db4oRepostiory {

    private static final String DB4OFILENAME = "src/main/resources/db4oFile";

    public db4oRepostiory(){
        // accessDb4o
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded
                .newConfiguration(), DB4OFILENAME);
        try {
            // do something with db4o
        } finally {
            db.close();
        }
    }

}
