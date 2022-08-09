package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.course.entities.User;

//interface que permitirá acesso a tabela de usuários
@Repository // cadastra o repositório como componente do spring
public interface UserRepository extends JpaRepository<User, Long>{

}
