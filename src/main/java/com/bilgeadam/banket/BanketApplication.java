package com.bilgeadam.banket;

import com.bilgeadam.banket.entity.User;
import com.bilgeadam.banket.entity.enums.ERole;
import com.bilgeadam.banket.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class BanketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanketApplication.class, args);
	}

	// Volkan: mail,password ileride environment variable'dan alınacak.
	@Bean
	public CommandLineRunner runner(UserService userService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				if (!userService.existsByEmail("admin@bilgeadambanket.com")) {
					userService.save(User.builder()
							.email("admin@bilgeadambanket.com")
							.password("Bilgeadam.123")
							.name("Admin")
							.surname("Admin")
							.roles(List.of(ERole.ADMIN))
							.build());
				}
			}
		};
	}

}
