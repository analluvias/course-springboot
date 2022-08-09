package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.Product;
import com.example.course.repositories.ProductRepository;

@Service //registra a classe como um Serviço/componente do spring e permite a injeção de dependencias
public class ProductService {
	
	@Autowired //injeção de dependencias
	private ProductRepository repository;
	
	//repassa a chamada da camada de serviço para o repositório
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		
		return obj.get(); //retorna o objeto do tipo user que estiver dentro do optional
	}
}
