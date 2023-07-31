package tn.esprit.asi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oracle.jdbc.pool.OracleDataSource;
import javax.sql.DataSource;

@Configuration
public class OracleConfiguration {
	
	@Bean
    public DataSource dataSource() throws Exception {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521/orcl");
        dataSource.setUser("sco_def");
        dataSource.setPassword("sco_def");
        return dataSource;
    }

}
