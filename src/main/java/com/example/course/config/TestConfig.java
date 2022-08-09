package com.example.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Usuario;
import com.example.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{ //interface que permite fazer com que comandos sejam rodados quando a aplicação for iniciada
	
	@Autowired //injeção de dependencia desacoplada
	private UserRepository userRepository; //userRepository eh o objeto que acessa os dados

	@Override
	public void run(String... args) throws Exception { //roda tudo que estiver aqui dentro
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); //id eh nulo pois vai ser gerado pelo bd
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); //id eh nulo pois vai ser gerado pelo bd
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
	

}
