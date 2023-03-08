package br.com.queensburger.queensBurger.service;

import br.com.queensburger.queensBurger.model.User;

public interface UserService {
  
  User include(User user);
  User update(Integer id, User user) throws duplicatedProductException;
  User findByEmail(String email);

}
