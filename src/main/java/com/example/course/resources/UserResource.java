// essa classe faz parte dos controladores

package com.example.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value = "/{id}") //informando que é uma requisição do tipo get e que aceita um id dentro da url: localhost:8080/users/1
	public ResponseEntity<User> findById(@PathVariable Long id){ //retorna um unico usuario
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj); //retorna o usuario + .ok() - msgUserService.javapara indicar que teve sucesso + .body(obj) - no corpo da requisição vai estar o obj retornado
		
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){ // @RequestBody  Para dizer que o obj vai chegar no modo json e vai ser desserializado para o tipo User
		obj = service.insert(obj);
		
		//vamos enviar como resposta um código 201, para isso usamos a mensagem de created, que recebe uma URI:
		
		//criando a uri que pegará o id do obj enviado no request atual e construirá algo como: /users/id_criado_agora
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		//retornando a mensagem de criado (com a uri criada agora) e no body terá o objeto criado
		return ResponseEntity.created(uri).body(obj);
		
		/* A URI CRIADA ESTARÁ NA MENSAGEM DE CRIADO DENTRO DO HEADERS - LOCATION */
	}
	
	@DeleteMapping(value = "/{id}") //informando que é uma requisição do tipo Delete e que aceita um id dentro da url: localhost:8080/users/1
	public ResponseEntity<Void> delete(@PathVariable Long id){ //@PathVariable informa que essa variável chega pela url
	
		/* ResponseEntity<Void> usamos para quando a nossa requisição dá um retorno vazio, ou seja, não vai retornar o user */
		
		service.delete(id); //chamando o service, que por sua vez vai chamar o repositório (que efetivamente fará a deleção)
		
		//esse retorna vai enviar a mensagem 204: que informa que não há mensagem de retorno
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}") //informando que é uma requisição do tipo PUT (update) e que aceita um id dentro da url: localhost:8080/users/1
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){ //@PathVariable informa que essa variável chega pela url
																					 //@RequestBody informa que vai receber um json com os pedidos de update
		
		obj = service.update(id, obj); //chamo o service que , que por sua vez vai chamar o repositório (que efetivamente fará a atualização)
		
		return ResponseEntity.ok().body(obj); 
		
	}
}
