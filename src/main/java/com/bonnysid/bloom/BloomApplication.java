package com.bonnysid.bloom;

import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.respos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class BloomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloomApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository userRepository) {
//		return args -> {
//			userRepository.save(new User(
//					"bonnysid",
//					"bonnysidworker@gmail.com",
//					"$2y$12$MHs1DVr0P1M0PPHdYMl5G.l6JOIRaZm1LzAS6zZ7v23JN2lfh091K "));
//			userRepository.save(new User(
//					"test1",
//					"test1@gmail.com",
//					"$2y$12$irLYjUPZoOdn4XM5cui3VuRM7qgda4dWLtM7moHdHy2RFyksZgkeu "));
//			userRepository.save(new User(
//					"test2",
//					"test2@gmail.com",
//					"$2y$12$irLYjUPZoOdn4XM5cui3VuRM7qgda4dWLtM7moHdHy2RFyksZgkeu "));
//			userRepository.save(new User(
//					"test3",
//					"test3@gmail.com",
//					"$2y$12$irLYjUPZoOdn4XM5cui3VuRM7qgda4dWLtM7moHdHy2RFyksZgkeu "));
//			userRepository.save(new User(
//					"test4",
//					"test4@gmail.com",
//					"$$2y$12$irLYjUPZoOdn4XM5cui3VuRM7qgda4dWLtM7moHdHy2RFyksZgkeu "));
//		};
//	}
}
