package io.evenement.evenement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EvenementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvenementApplication.class, args);
	}

}
