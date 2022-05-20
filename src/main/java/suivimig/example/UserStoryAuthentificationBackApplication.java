package suivimig.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
public class UserStoryAuthentificationBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserStoryAuthentificationBackApplication.class, args);
    }

}
