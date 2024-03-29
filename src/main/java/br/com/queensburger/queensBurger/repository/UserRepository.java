package br.com.queensburger.queensBurger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.queensburger.queensBurger.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
  Optional<User> findByEmail(String email);
//	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
