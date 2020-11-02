package bdII.OODatabase.configuration;

import bdII.OODatabase.repository.Db4oRepository;
import com.db4o.ObjectContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OODatabaseConfiguration {

    @Bean
    public ObjectContainer objectContainer(){
        return Db4oRepository.getConnection();
    }

}
