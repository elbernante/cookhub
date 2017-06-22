package com.elbernante.cookhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.elbernante.cookhub.persistence.dao")
@EntityScan("com.elbernante.cookhub.persistence.model")
public class CookhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookhubApplication.class, args);
	}
}
