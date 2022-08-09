package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.Usuario;
import com.example.course.repositories.UserRepository;

@Service //registra a classe como um Serviço/componente do spring e permite a injeção de dependencias
public class UserService {
	
	@Autowired //injeção de dependencias
	private UserRepository repository;
	
	//repassa a chamada da camada de serviço para o repositório
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		
		return obj.get(); //retorna o objeto do tipo user que estiver dentro do optional
	}
}
