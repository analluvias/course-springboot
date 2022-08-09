package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.Category;
import com.example.course.repositories.CategoryRepository;

@Service //registra a classe como um Serviço/componente do spring e permite a injeção de dependencias
public class CategoryService {
	
	@Autowired //injeção de dependencias
	private CategoryRepository repository;
	
	//repassa a chamada da camada de serviço para o repositório
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		
		return obj.get(); //retorna o objeto do tipo user que estiver dentro do optional
	}
}
