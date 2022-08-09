package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.Order;
import com.example.course.repositories.OrderRepository;

@Service //registra a classe como um Serviço/componente do spring e permite a injeção de dependencias
public class OrderService {
	
	@Autowired //injeção de dependencias
	private OrderRepository repository;
	
	//repassa a chamada da camada de serviço para o repositório
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		
		return obj.get(); //retorna o objeto do tipo user que estiver dentro do optional
	}
}
