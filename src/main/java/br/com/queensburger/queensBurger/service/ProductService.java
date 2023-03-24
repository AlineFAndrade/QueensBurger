package br.com.queensburger.queensBurger.service;

import java.util.List;

import br.com.queensburger.queensBurger.model.Product;

public interface ProductService {
  
  Product include(Product product);
  Product update(Integer id, Product product) throws duplicatedProductException;
  Product findById(Integer id);
  List<Product> getAll();
  void delete(int id);
}
