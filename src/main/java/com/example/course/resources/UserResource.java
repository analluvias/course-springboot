// essa classe faz parte dos controladores

package com.example.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.Users;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	 public ResponseEntity<Users> findAll(){
		 
		Users u = new Users(1l, "Maria", "maria@gmail.com", "99999999", "12345");
		return ResponseEntity.ok().body(u);
		
		
	 }

}
