package com.edmarzungo.pedidosnamao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaAuditing
public class PedidosnamaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosnamaoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner runner(RoleRepository roleRepository){
//		return args -> {
//			if (roleRepository.findByName("USER").isEmpty()){
//				Role user = new Role();
//				user.setCreatedDate(LocalDateTime.now());
//				user.setRoleName("USER");
//				roleRepository.save(user);
//			}
//		};
//	}

}
