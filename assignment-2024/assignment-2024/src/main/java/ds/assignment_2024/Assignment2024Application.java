package ds.assignment_2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Assignment2024Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment2024Application.class, args);
    }


}
