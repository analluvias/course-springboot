// essa classe faz parte dos controladores

package com.example.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.User;
import com.example.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping  //informando que é uma requisição do tipo get
	 public ResponseEntity<List <User>> findAll(){ //retorna uma lista de usuarios
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	 }
	
	@GetMapping(value = "/{id}") //informando que é uma requisição do tipo get e que aceita um id dentro da url
	public ResponseEntity<User> findById(@PathVariable Long id){ //retorna um unico usuario
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj); //retorna o usuario + .ok() - msgpara indicar que teve sucesso + .body(obj) - no corpo da requisição vai estar o obj retornado
		
	}
	
	

}
