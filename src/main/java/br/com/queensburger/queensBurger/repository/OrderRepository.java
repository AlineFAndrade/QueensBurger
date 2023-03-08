package br.com.queensburger.queensBurger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.queensburger.queensBurger.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
  Optional<Order> findById(Integer id);
}
