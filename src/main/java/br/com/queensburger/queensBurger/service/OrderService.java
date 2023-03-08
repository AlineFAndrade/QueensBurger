package br.com.queensburger.queensBurger.service;

import br.com.queensburger.queensBurger.model.Order;

public interface OrderService {
  
  Order include(Order order);
  Order update(Integer id, Order order) throws duplicatedProductException;
  Order findById(Integer id);

}
