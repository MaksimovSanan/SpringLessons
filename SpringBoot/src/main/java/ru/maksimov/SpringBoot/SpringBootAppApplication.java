package ru.maksimov.SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAppApplication {

	public static void main(String[] args) {
		System.out.println("HELLO!");
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

}
