package com.example.lab8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.lab8.entities.Compte;
import com.example.lab8.entities.TypeCompte;
import com.example.lab8.repositories.CompteRepository;
import java.util.Date;

@SpringBootApplication
public class Lab8Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab8Application.class, args);
	}

	@Bean
	CommandLineRunner initializeComptes(CompteRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				repository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));
				repository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT));
				repository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));
			}
			repository.findAll().forEach(System.out::println);
		};
	}

}
