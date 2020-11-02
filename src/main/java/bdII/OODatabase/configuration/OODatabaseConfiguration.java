package bdII.OODatabase.configuration;

import bdII.OODatabase.repository.Db4oRepository;
import com.db4o.ObjectContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OODatabaseConfiguration {

    @Bean
    public ObjectContainer objectContainer(){
        return Db4oRepository.getConnection();
    }

}
