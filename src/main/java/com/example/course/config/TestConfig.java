package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Order;
import com.example.course.entities.User;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{ //interface que permite fazer com que comandos sejam rodados quando a aplicação for iniciada
	
	@Autowired //injeção de dependencia desacoplada
	private UserRepository userRepository; //userRepository eh o objeto que acessa os dados
	
	@Autowired //injeção de dependencia desacoplada
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception { //roda tudo que estiver aqui dentro
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); //id eh nulo pois vai ser gerado pelo bd
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); //id eh nulo pois vai ser gerado pelo bd
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
	
	
	

}
