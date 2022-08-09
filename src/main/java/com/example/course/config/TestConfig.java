package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Category;
import com.example.course.entities.Order;
import com.example.course.entities.Product;
import com.example.course.entities.User;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.CategoryRepository;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.ProductRepository;
import com.example.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{ //interface que permite fazer com que comandos sejam rodados quando a aplicação for iniciada
	
	@Autowired //injeção de dependencia desacoplada
	private UserRepository userRepository; //userRepository eh o objeto que acessa os dados
	
	@Autowired //injeção de dependencia desacoplada
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception { //roda tudo que estiver aqui dentro
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of The Rings", "aaaaaaaaa.", 90.5, "");
		Product p2 = new Product(null, "Smart tv", "bbbbbbbbb.", 2190.0, "");
		Product p3 = new Product(null, "PC gamer", "ccccccccc.", 1200.0, "");
		Product p4 = new Product(null, "Rails for Dummies", "dddddddd.", 100.99, "");
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); //id eh nulo pois vai ser gerado pelo bd
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); //id eh nulo pois vai ser gerado pelo bd
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
	
	
	

}
