package bdII.OODatabase.repositories;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import java.io.File;

public class Db4oRepository {

    private static final String DB4OFILENAME = "src/main/resources/db4oFile.db4o";

    public static ObjectContainer getConnection(){
        new File(DB4OFILENAME).delete();
         return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
    }



}
