package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

@Service //registra a classe como um Serviço/componente do spring e permite a injeção de dependencias
public class UserService {
	
	@Autowired //injeção de dependencias
	private UserRepository repository;
	
	//repassa a chamada da camada de serviço para o repositório
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		
		//tenta dar um get pelo id, se não conseguir vai lançar a exceção ResourceNotFoundException mandando o id que deu erro ao buscar
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj); //chama o repositório para fazer a operação de post de um usuário
	}
	
	
	public void delete(Long id) {
		try {
			repository.deleteById(id); //chama o reporitório para fazer a deleção por id de um usuário no bd
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	
	public User update(Long id, User obj) { //vai receber id do usuário e um obj User com seus dados atualizados
		//deixando o objeto em que vou mexer monitorado pelo JPA
		User entity = repository.getReferenceById(id);
		
		//chamando método que vai fazer o update dos dados, enviando a entidade monitorada e o obj recebido
		updateData(entity, obj);
		
		return repository.save(entity); // chama o repositório para fazer a atualização por id de um usuario no bd
		
	}

	private void updateData(User entity, User obj) {
		//nesse caso eu só vou permitir que atualize nome, email e senha
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
