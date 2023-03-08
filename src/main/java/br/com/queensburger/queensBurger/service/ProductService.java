package br.com.queensburger.queensBurger.service;

import br.com.queensburger.queensBurger.model.Product;

public interface ProductService {
  
  Product include(Product product);
  Product update(Integer id, Product product) throws duplicatedProductException;
  Product findById(Integer id);

}
